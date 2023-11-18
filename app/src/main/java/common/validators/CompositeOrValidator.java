package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class CompositeOrValidator implements MovementValidator {
    private final MovementValidator[] validators;

    public CompositeOrValidator(MovementValidator... validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        for (MovementValidator validator : validators) {
            if (validator.isValid(boardHistory, movement)) return true;
        }
        return false;
    }
}
