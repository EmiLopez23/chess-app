package games.checkers.game;

import common.*;
import common.validators.MovementValidator;
import games.checkers.mover.Mover;
import games.chess.game.ChessGame;

import java.util.ArrayList;
import java.util.List;

public class CheckersGame{
    private final List<Board> boards;

    private final Mover mover;

    private final List<Player> players;

    private final TurnManager turnManager;

    private final MovementValidator winValidator;



    public CheckersGame(List<Board> boards, Mover mover, List<Player> players, TurnManager turnManager, MovementValidator winValidator) {
        this.boards = boards;
        this.mover = mover;
        this.players = players;
        this.turnManager = turnManager;
        this.winValidator = winValidator;
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

    public GameResponse<CheckersGame, String> play(Movement movement){
        MoveResult<Board, String> moveResult = mover.move(boards, movement.getFrom(), movement.getTo(), turnManager.getCurrentPlayer());
        Player nextPlayer = getNextPlayer();
        if(!moveResult.isValid()) {
            return new GameResponse<>(this, moveResult.getMessage());
        }
        List<Board> newHistory = new ArrayList<>(this.boards);
        newHistory.add(moveResult.getBoard());
        CheckersGame newGame = new CheckersGame(newHistory, mover, players, new TurnManager(nextPlayer), winValidator);
        if(winValidator.isValid(newGame.getBoards(), movement.getFrom(), movement.getTo())){
            return new GameResponse<>(new CheckersGame(null, null, null, new TurnManager(nextPlayer), null), "Game Over");
        }
        return new GameResponse<>(newGame, null);
    }

    public Player getNextPlayer(){
        return turnManager.nextPlayer();
    }
}
