package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class LimitedMoveValidator implements MovementValidator {
    private final int limit;

    public LimitedMoveValidator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        return movement.colDifference() <= limit && movement.rowDifference() <= limit;
    }
}
