package validators;

import board.Board;
import board.Coordinate;
import board.Piece;

import java.util.Map;

public class VerticalValidator implements MovementValidator{
    private final boolean forward;

    public VerticalValidator(boolean forward){
        this.forward = forward;
    }

    @Override
    public boolean isValid(Board board, Coordinate from, Coordinate to) {
        if(from.column() != to.column()) return false;
        if(board.getRowSize() < to.row() || board.getColumnSize() < to.column()) return false;
        int direction = (from.row() < to.row()) ? 1 : - 1;
        if(forward && direction == -1) return false;
        Map<Coordinate, Piece> pieces = board.getBoard();
        Coordinate current = new Coordinate(from.column(), from.row() + direction);
        while(!current.equals(to)) {
            if(pieces.containsKey(current)) return false;
            if (current.row() < 1 || current.row() > board.getRowSize()) return false;
            current = new Coordinate(current.column(), current.row() + direction);
        }
        return true;
    }
}
