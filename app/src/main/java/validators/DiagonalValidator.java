package validators;

import board.Board;
import board.Coordinate;
import board.Piece;

import java.util.Map;

public class DiagonalValidator implements MovementValidator{
    private final boolean forward;

    public DiagonalValidator(boolean forward){
        this.forward = forward;
    }
    @Override
    public boolean isValid(Board board, Coordinate from, Coordinate to) {
        if(from.column()== to.column() || from.row() == to.row()) return false;
        if(board.getRowSize() < to.row() || board.getColumnSize() < to.column()) return false;
        Coordinate current = from;
        int directionX = from.row() < to.row() ? 1 : - 1;
        int directionY = from.row() < to.row() ? 1 : - 1;
        if (forward && directionY == -1) return false;
        Map<Coordinate, Piece> pieces = board.getBoard();
        while(!current.equals(to)) {
            if(pieces.containsKey(current)) return false;
            current = new Coordinate(current.row() + directionY, current.column() + directionX);
        }
        return true;
    }
}
