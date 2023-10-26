package validators;

import board.Board;
import board.Coordinate;

public interface MovementValidator {
    public boolean isValid(Board board, Coordinate from, Coordinate to);
}
