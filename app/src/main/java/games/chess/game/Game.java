package games.chess.game;

import games.chess.board.Board;
import games.chess.board.Movement;
import games.chess.board.Player;
import games.chess.board.TurnManager;
import games.chess.mover.MoveResult;
import games.chess.mover.Mover;
import games.chess.validators.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Board> boards;
    private final List<Movement> movementHistory;

    private final Mover mover;

    private final List<Player> players;

    private final TurnManager turnManager;

    private final MovementValidator checkValidator;

    private final MovementValidator winValidator;



    public Game(List<Board> boards, List<Movement> movementHistory, Mover mover, List<Player> players, TurnManager turnManager, MovementValidator checkValidator, MovementValidator winValidator) {
        this.boards = boards;
        this.movementHistory = movementHistory;
        this.mover = mover;
        this.players = players;
        this.turnManager = turnManager;
        this.checkValidator = checkValidator;
        this.winValidator = winValidator;
    }

    public MovementValidator getCheckValidator() {
        return checkValidator;
    }

    public MovementValidator getWinValidator() {
        return this.winValidator;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public Board getBoard() {
        return this.boards.get(this.boards.size() - 1);
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

    public GameResponse<Game, String> play(Movement movement){
        MoveResult<Board,String> newBoardResult = mover.move(boards, movement.getFrom(), movement.getTo(), turnManager.getCurrentPlayer());
        Player nextPlayer = getNextPlayer();
        if(newBoardResult.isValid()){
            List<Movement> newMovement = new ArrayList<>(this.movementHistory);
            newMovement.add(movement);
            List<Board> newBoards = new ArrayList<>(this.boards);
            newBoards.add(newBoardResult.getBoard());
            return new GameResponse<>(new Game(newBoards, newMovement , mover, players, new TurnManager(nextPlayer), checkValidator, winValidator), null);
        }
        return new GameResponse<>(this, newBoardResult.getMessage());

    }

    public Player getNextPlayer(){
        return turnManager.getCurrentPlayer().equals(players.get(0)) ? players.get(1) : players.get(0);
    }

}
