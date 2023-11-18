package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class GoForwardInYValidator implements MovementValidator{
    private final boolean forward;

    public GoForwardInYValidator(boolean forward) {
        this.forward = forward;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        return (forward == (movement.directionY() == 1));
    }
}
