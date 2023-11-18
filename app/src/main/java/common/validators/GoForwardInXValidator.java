package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class GoForwardInXValidator implements MovementValidator{
    private final boolean forward;

    public GoForwardInXValidator(boolean forward) {
        this.forward = forward;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        return (forward && movement.directionX() == 1) || (!forward && movement.directionX() == -1);
    }
}
