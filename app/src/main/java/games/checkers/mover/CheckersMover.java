package games.checkers.mover;

import common.*;
import common.enums.GameState;
import common.mover.Mover;
import common.validators.MovementValidator;
import games.checkers.validators.IsForcedToEatValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckersMover implements Mover {

    private final MovementValidator isForcedToEat;

    public CheckersMover(boolean isForcedToEat) {
        this.isForcedToEat =  new IsForcedToEatValidator(isForcedToEat);
    }

    @Override
    public MoveResponse move(Game game, Movement move) {
        Board currentBoard = game.getBoard();
        Piece currentPiece = currentBoard.getPieces().get(move.from());
        if (!currentPiece.validator().isValid(game.history(), move)) {
            return new MoveResponse(game, "Invalid movement", GameState.INVALID_MOVE);
        }

        if (isForcedToEat.isValid(game.history(), move)) {
            return new MoveResponse(game, "You are forced to eat", GameState.INVALID_MOVE);
        }

        Board newBoardObject = executeMove(currentBoard, currentPiece, move);
        Game newGame = createNewGame(game, newBoardObject, game.getNextPlayer());

        return new MoveResponse(newGame, null, GameState.KEEP_PLAYING);
    }

    private Game createNewGame(Game game, Board newBoard, Player nextPlayer) {
        List<Board> newBoards = new ArrayList<>(game.history());
        newBoards.add(newBoard);
        return new Game(newBoards, game.mover(), game.players(), new TurnManager(nextPlayer), game.winValidator());
    }

    private Board executeMove(Board currentBoard, Piece currentPiece, Movement move) {
        Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getPieces());
        newPieces.remove(move.from());
        if (move.rowDifference() > 1) {
            Coordinate pieceInBetween = new Coordinate((move.from().column() + move.to().column()) / 2, (move.from().row() + move.to().row()) / 2);
            newPieces.remove(pieceInBetween);
        }
        newPieces.put(move.to(), currentPiece);
        return new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
    }
}
