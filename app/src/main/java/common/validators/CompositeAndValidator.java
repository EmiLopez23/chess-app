package common.validators;

import common.Board;
import common.Coordinate;
import common.validators.MovementValidator;

import java.util.List;

public class CompositeAndValidator implements MovementValidator {
    private final MovementValidator[] validators;

    public CompositeAndValidator(MovementValidator... validators) {
        this.validators = validators;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        for (MovementValidator validator : validators){
            if (!validator.isValid(boardHistory, from,to)) return false;
        }
        return true;
    }
}
