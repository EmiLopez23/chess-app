package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class OutOfBoundsValidator implements MovementValidator {

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        return movement.to().column() >= 1 && movement.to().column() <= board.getColumnSize() && movement.to().row() >= 1 && movement.to().row() <= board.getRowSize();
    }
}
