package game;

import board.Board;
import board.Movement;
import board.Player;
import board.TurnManager;
import mover.MoveResult;
import mover.Mover;

import java.util.List;

public class Game {
    private final Board board;
    private final List<Movement> movementHistory;

    private final Mover mover;

    private final List<Player> players;

    private final TurnManager turnManager;

    public Game(Board board, List<Movement> movementHistory, Mover mover, List<Player> players, TurnManager turnManager) {
        this.board = board;
        this.movementHistory = movementHistory;
        this.mover = mover;
        this.players = players;
        this.turnManager = turnManager;
    }

    public Board getBoard() {
        return this.board;
    }

    public List<Movement> getMovementHistory() {
        return this.movementHistory;
    }

    public Mover getMover() {
        return this.mover;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public TurnManager getTurnManager() {
        return this.turnManager;
    }

    public MoveResult<Game, Boolean> play(Movement movement){
        MoveResult<Board,Boolean> newBoardResult = mover.move(board, movement.getFrom(), movement.getTo(), turnManager.getCurrentPlayer());
        if(newBoardResult.isValid()){
            movementHistory.add(movement);
        }
        Player nextPlayer = turnManager.getCurrentPlayer().equals(players.get(0)) ? players.get(1) : players.get(0);
        return new MoveResult<>(new Game(newBoardResult.getObject(), movementHistory, mover, players, new TurnManager(nextPlayer)), newBoardResult.isValid());

    }

}
