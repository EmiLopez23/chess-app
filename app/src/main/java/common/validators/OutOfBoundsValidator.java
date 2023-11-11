package common.validators;

import common.Board;
import common.Coordinate;
import common.validators.MovementValidator;

import java.util.List;

public class OutOfBoundsValidator implements MovementValidator {

    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        return to.column() >= 1 && to.column() <= board.getColumnSize() && to.row() >= 1 && to.row() <= board.getRowSize();
    }
}
