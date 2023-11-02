package validators;

import board.Board;
import board.Coordinate;

import java.util.List;

public class LimitedMoveValidator implements MovementValidator{
    private int limit;

    public LimitedMoveValidator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        int columnDifference = Math.abs(from.column() - to.column());
        int rowDifference = Math.abs(from.row() - to.row());
        return columnDifference <= limit && rowDifference <= limit;
    }
}
