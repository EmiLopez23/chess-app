package games.checkers.validators;

import common.Board;
import common.Coordinate;
import common.validators.MovementValidator;

import java.util.List;

public class DiagonalValidator implements MovementValidator {

    private boolean forward;

    public DiagonalValidator(boolean forward) {
        this.forward = forward;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        if(from.column() == to.column() || from.row() == to.row()) return false;
        if(Math.abs(from.column() - to.column()) != Math.abs(from.row() - to.row())) return false;
        int directionY = from.row() < to.row() ? 1 : - 1;
        int directionX = from.column() < to.column() ? 1 : - 1;
        if (!forward && directionY == 1) return false;
        if (forward && directionY == -1) return false;
        return true;
    }
}
