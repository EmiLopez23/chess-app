package games.checkers.mover;

import common.*;
import common.enums.Color;
import common.enums.PieceType;
import common.validators.MovementValidator;
import games.checkers.factory.PieceFactory;
import common.Game;
import games.checkers.validators.IsForcedToEatValidator;

import java.util.*;

public class Mover implements SimpleMover {
    PieceFactory pieceFactory = new PieceFactory();

    MovementValidator isForcedToEat = new IsForcedToEatValidator(true);
    @Override
    public MoveResponse move(Game game, Coordinate from, Coordinate to) {
        Board currentBoard = game.getBoard();
        Piece currentPiece = currentBoard.getBoard().get(from);
        if(currentPiece.getValidator().isValid(game.getBoards(), from, to)) {
            Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getBoard());
            if(isForcedToEat.isValid(game.getBoards(), from, to)) {
                return new MoveResponse(game, "You are forced to eat");
            }
            tryEat(newPieces, from, to);
            tryPromotion(currentBoard,newPieces,currentPiece, to);
            newPieces.remove(from);
            Board newBoardObject = new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
            return new MoveResponse(getNewGame(game, newBoardObject), null);
        }
        return new MoveResponse(game, "Invalid movement");
    }

    private Game getNewGame(Game game, Board newBoard){
        List<Board> newBoards = new ArrayList<>(game.getBoards());
        newBoards.add(newBoard);
        return new Game(newBoards, game.getMover(), game.getPlayers(), new TurnManager(game.getTurnManager().nextPlayer()), game.getWinValidator());
    }

    private void tryEat(Map<Coordinate, Piece> pieces, Coordinate from, Coordinate to) {
        if(Math.abs(from.row() - to.row()) > 1){
            Coordinate pieceInBetween = new Coordinate((from.column() + to.column()) / 2, (from.row() + to.row()) / 2);
            pieces.remove(pieceInBetween);
        }
    }

    private void tryPromotion(Board board, Map<Coordinate, Piece> pieces, Piece currentPiece, Coordinate to) {
        String pieceId = currentPiece.getId();
        Color color = currentPiece.getColor();
        PieceType pieceType = currentPiece.getPieceType();
        if( pieceType == PieceType.PAWN && (to.row() == board.getRowSize() || to.row() == 1)) {
            pieces.put(to, pieceFactory.createQueen(pieceId, color));
            return;
        }
        pieces.put(to, currentPiece);
    }
}
