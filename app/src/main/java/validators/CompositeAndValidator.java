package validators;

import board.Board;
import board.Coordinate;

public class CompositeAndValidator implements MovementValidator{
    private final MovementValidator[] validators;

    public CompositeAndValidator(MovementValidator... validators) {
        this.validators = validators;
    }
    @Override
    public boolean isValid(Board board, Coordinate from, Coordinate to) {
        for (MovementValidator validator : validators){
            if (!validator.isValid(board, from,to)) return false;
        }
        return true;
    }
}
