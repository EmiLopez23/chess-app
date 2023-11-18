package common;

import common.enums.GameState;
import common.mover.Mover;
import common.validators.MovementValidator;

import java.util.List;

public record Game(List<Board> history, Mover mover, List<Player> players, TurnManager turnManager, MovementValidator winValidator) {
    public Board getBoard() {
        return this.history.get(this.history.size() - 1);
    }

    public MoveResponse play(Movement movement) {
        MoveResponse moveResult = mover.move(this, movement.from(), movement.to());
        if (!moveResult.isValid()) { //first check if the move is valid to then check if it is game over
            return moveResult;
        }
        if (winValidator.isValid(moveResult.game().history(), movement)) {
            return new MoveResponse(this, "Game Over", GameState.GAME_OVER);
        }
        return moveResult;
    }

    public Player getNextPlayer() {
        return turnManager.nextPlayer();
    }

    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }
}
