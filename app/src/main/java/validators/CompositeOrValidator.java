package validators;

import board.Board;
import board.Coordinate;

public class CompositeOrValidator implements MovementValidator{
    private final MovementValidator[] validators;

    public CompositeOrValidator(MovementValidator... validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(Board board, Coordinate from, Coordinate to) {
        for (MovementValidator validator : validators){
            if (validator.isValid(board, from,to)) return true;
        }
        return false;
    }
}
