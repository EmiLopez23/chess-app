package games.chess.mover;

import common.*;
import common.enums.GameState;
import common.mover.Mover;
import games.chess.validators.CheckValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessMover implements Mover {

    private final CheckValidator checkValidator;

    public ChessMover (){
        this.checkValidator = new CheckValidator();
    }
    @Override
    public MoveResponse move(Game game, Movement move) {
        Board currentBoard = game.getBoard();
        Piece currentPiece = currentBoard.getPieces().get(move.from());
        if(!currentPiece.validator().isValid(game.history(), move)) { return new MoveResponse(game, "Invalid movement", GameState.INVALID_MOVE);}

        Board newBoardObject = executeMove(currentBoard, currentPiece, move);
        Game newGame = getNewGame(game, newBoardObject);

        if(checkValidator.isValid(newGame.history(), move)){
            return new MoveResponse(game, "Check", GameState.INVALID_MOVE);
        }

        return new MoveResponse(newGame, null, GameState.KEEP_PLAYING);
    }

    private Game getNewGame(Game game, Board newBoard) {
        List<Board> newBoards = new ArrayList<>(game.history());
        newBoards.add(newBoard);
        return new Game(newBoards, game.mover(), game.players(), new TurnManager(game.turnManager().nextPlayer()), game.winValidator());
    }

    private Board executeMove(Board currentBoard, Piece currentPiece, Movement movement) {
        Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getPieces());
        newPieces.remove(movement.from());
        newPieces.put(movement.to(), currentPiece);
        return new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
    }

}