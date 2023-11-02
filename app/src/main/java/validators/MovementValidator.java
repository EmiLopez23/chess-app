package validators;

import board.Board;
import board.Coordinate;

import java.util.List;

public interface MovementValidator {
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to);
}
