package common;

import common.validators.MovementValidator;
import games.checkers.mover.Mover;

import java.util.List;

public class Game{
    private final List<Board> boards;

    private final SimpleMover mover;

    private final List<Player> players;

    private final TurnManager turnManager;

    private final MovementValidator winValidator;



    public Game(List<Board> boards, SimpleMover mover, List<Player> players, TurnManager turnManager, MovementValidator winValidator) {
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

    public SimpleMover getMover() {
        return this.mover;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public TurnManager getTurnManager() {
        return this.turnManager;
    }

    public MoveResponse play(Movement movement){
        Piece currentPiece = getBoard().getPiece(movement.getFrom());
        if( currentPiece == null) {
            return new MoveResponse(this, "No piece in that coordinate");
        }
        if(getCurrentPlayer().getColor() != currentPiece.getColor()) {
            return new MoveResponse(this, "Not your turn");
        }
        if(movement.getFrom().equals(movement.getTo())) {
            return new MoveResponse(this, "You can't move to the same place");
        }
        MoveResponse moveResult = mover.move(this, movement.getFrom(), movement.getTo());
        if(moveResult.isValid()){
            if (winValidator.isValid(moveResult.getGame().getBoards(), movement.getFrom(), movement.getTo())) {
                return new MoveResponse(new Game(null, null, null, new TurnManager(getNextPlayer()), null), "Game Over");
            }
            return moveResult;
        }
        return moveResult;
    }

    public Player getNextPlayer(){
        return turnManager.nextPlayer();
    }

    public Player getCurrentPlayer(){
        return turnManager.getCurrentPlayer();
    }
}
