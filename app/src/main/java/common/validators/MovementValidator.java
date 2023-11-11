package common.validators;

import common.Board;
import common.Coordinate;

import java.util.List;

public interface MovementValidator {
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to);
}
