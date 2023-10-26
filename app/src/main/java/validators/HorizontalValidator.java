package validators;

import board.Board;
import board.Coordinate;
import board.Piece;

import java.util.Map;

public class HorizontalValidator implements MovementValidator{
    private final boolean right;

    public HorizontalValidator(boolean right) {
        this.right = right;
    }
    @Override
    public boolean isValid(Board board, Coordinate from, Coordinate to) {
        if(from.column() != to.column()) return false;
        if(board.getRowSize() < to.row() || board.getColumnSize() < to.column()) return false;
        int direction = from.row() < to.row() ? 1 : - 1;
        if(right && direction == -1) return false;
        Coordinate current = from;
        Map<Coordinate, Piece> pieces = board.getBoard();
        while(!current.equals(to)) {
            if(pieces.containsKey(current)) return false;
            current = new Coordinate(current.row() + direction , current.column());
        }
        return true;
    }
}
