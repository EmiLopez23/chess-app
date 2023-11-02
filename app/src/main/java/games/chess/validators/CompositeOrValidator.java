package games.chess.validators;

import common.Board;
import common.Coordinate;
import common.MovementValidator;

import java.util.List;

public class CompositeOrValidator implements MovementValidator {
    private final MovementValidator[] validators;

    public CompositeOrValidator(MovementValidator... validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        for (MovementValidator validator : validators){
            if (validator.isValid(boardHistory, from,to)) return true;
        }
        return false;
    }
}
