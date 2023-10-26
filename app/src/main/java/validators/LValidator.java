package validators;

import board.Board;
import board.Coordinate;

public class LValidator implements MovementValidator{
    @Override
    public boolean isValid(Board board, Coordinate from, Coordinate to) {
        if(board.getPiece(from) == null) return false;
        if (board.getColumnSize() < to.column() || board.getRowSize() < to.row()) return false;
        if (from.column() == to.column() && from.row() == to.row()) return false;
        if(Math.abs(from.column() - to.column()) == 2 && Math.abs(from.row() - to.row()) == 1) return true;
        return Math.abs(from.column() - to.column()) == 1 && Math.abs(from.row() - to.row()) == 2;
    }
}
