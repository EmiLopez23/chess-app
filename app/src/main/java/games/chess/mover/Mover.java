package games.chess.mover;

import common.*;
import common.Game;
import games.chess.validators.CheckValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mover implements SimpleMover {

    CheckValidator checkValidator = new CheckValidator();
    @Override
    public MoveResponse move(Game game, Coordinate from, Coordinate to) {
        Board currentBoard = game.getBoard();
        Piece currentPiece = currentBoard.getBoard().get(from);
        if(currentPiece.getValidator().isValid(game.getBoards(), from, to)) {
            Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getBoard());
            newPieces.put(to, currentPiece);
            newPieces.remove(from);
            Board newBoardObject = new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
            Game newGame = getNewGame(game, newBoardObject);
            if(!checkValidator.isValid(newGame.getBoards(), from, to)){
            return new MoveResponse(game, "Check");
            }
            return new MoveResponse(newGame, null);
        }
        return new MoveResponse(game, "Invalid movement");

    }

    private Game getNewGame(Game game, Board newBoard){
        List<Board> newBoards = new ArrayList<>(game.getBoards());
        newBoards.add(newBoard);
        return new Game(newBoards, game.getMover(), game.getPlayers(), new TurnManager(game.getTurnManager().nextPlayer()), game.getWinValidator());
    }

}