package games.checkers.mover;

import common.*;
import common.enums.Color;
import common.enums.GameState;
import common.enums.PieceType;
import common.mover.Mover;
import common.validators.MovementValidator;
import games.checkers.factory.PieceFactory;
import games.checkers.validators.CanKeepEatingValidator;
import games.checkers.validators.IsForcedToEatValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckersMover implements Mover {
    PieceFactory pieceFactory = new PieceFactory();

    MovementValidator isForcedToEat = new IsForcedToEatValidator(true);
    MovementValidator canKeepEating = new CanKeepEatingValidator(possibleMoves());

    @Override
    public MoveResponse move(Game game, Movement move) {
        Board currentBoard = game.getBoard();
        Piece currentPiece = currentBoard.getPieces().get(move.from());
        if (currentPiece.getValidator().isValid(game.history(), move)) {
            Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getPieces());
            if (isForcedToEat.isValid(game.history(), move)) {
                return new MoveResponse(game, "You are forced to eat", GameState.INVALID_MOVE);
            }
            tryEat(newPieces, move);
            tryPromotion(currentBoard, newPieces, currentPiece, move.to());
            newPieces.remove(move.from());
            Board newBoardObject = new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
            List<Board> newBoards = getNewBoards(game, newBoardObject);
            if (canKeepEating.isValid(newBoards, move)) {
                return new MoveResponse(getNewGame(game, newBoardObject, game.getCurrentPlayer()), null, GameState.KEEP_PLAYING);
            }
            return new MoveResponse(getNewGame(game, newBoardObject, game.getNextPlayer()), null, GameState.KEEP_PLAYING);
        }
        return new MoveResponse(game, "Invalid movement", GameState.INVALID_MOVE);
    }

    private Game getNewGame(Game game, Board newBoard, Player nextPlayer) {
        List<Board> newBoards = getNewBoards(game, newBoard);
        return new Game(newBoards, game.mover(), game.players(), new TurnManager(nextPlayer), game.winValidator());
    }

    private List<Board> getNewBoards(Game game, Board newBoard) {
        List<Board> newBoards = new ArrayList<>(game.history());
        newBoards.add(newBoard);
        return newBoards;
    }

    private void tryEat(Map<Coordinate, Piece> pieces, Movement move) {
        if (move.rowDifference() > 1) {
            Coordinate pieceInBetween = new Coordinate((move.from().column() + move.to().column()) / 2, (move.from().row() + move.to().row()) / 2);
            pieces.remove(pieceInBetween);
        }
    }

    private void tryPromotion(Board board, Map<Coordinate, Piece> pieces, Piece currentPiece, Coordinate to) {
        String pieceId = currentPiece.getId();
        Color color = currentPiece.getColor();
        PieceType pieceType = currentPiece.getPieceType();
        if (pieceType == PieceType.PAWN && (to.row() == board.getRowSize() || to.row() == 1)) {
            pieces.put(to, pieceFactory.createQueen(pieceId, color));
            return;
        }
        pieces.put(to, currentPiece);
    }

    private List<Coordinate> possibleMoves(){
        return List.of(
                new Coordinate(2,2),
                new Coordinate(2,-2),
                new Coordinate(-2,2),
                new Coordinate(-2,-2)
        );
    }
}
