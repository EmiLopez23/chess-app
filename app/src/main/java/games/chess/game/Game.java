package games.chess.game;

import common.*;
import games.chess.mover.Mover;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Board> boards;

    private final Mover mover;

    private final List<Player> players;

    private final TurnManager turnManager;

    private final MovementValidator checkValidator;

    private final MovementValidator winValidator;



    public Game(List<Board> boards, Mover mover, List<Player> players, TurnManager turnManager, MovementValidator checkValidator, MovementValidator winValidator) {
        this.boards = boards;
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
        if(!newBoardResult.isValid()) {
            return new GameResponse<>(this, newBoardResult.getMessage());
        }
        List<Board> newBoards = new ArrayList<>(this.boards);
        newBoards.add(newBoardResult.getBoard());
        Game newGame = new Game(newBoards, mover, players, new TurnManager(nextPlayer), checkValidator, winValidator);
        if(!checkValidator.isValid(newGame.getBoards(), movement.getFrom(), movement.getTo())){
            return new GameResponse<>(this, "Check");
        }
        if(winValidator.isValid(newGame.getBoards(), movement.getFrom(), movement.getTo())){
            return new GameResponse<>(new Game(null, null, null, new TurnManager(nextPlayer), null, null), "Game Over");
        }
        return new GameResponse<>(newGame, null);


    }

    public Player getNextPlayer(){
        return turnManager.nextPlayer();
    }

}
