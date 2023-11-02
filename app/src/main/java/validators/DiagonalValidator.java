package validators;

import board.Board;
import board.Coordinate;
import board.Piece;

import java.util.List;
import java.util.Map;

public class DiagonalValidator implements MovementValidator{
    private final boolean forward;

    public DiagonalValidator(boolean forward){
        this.forward = forward;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        if(Math.abs(from.column() - to.column()) != Math.abs(from.row() - to.row())) return false;
        Board board = boardHistory.get(boardHistory.size() - 1);
        int directionY = from.row() < to.row() ? 1 : - 1;
        int directionX = from.column() < to.column() ? 1 : - 1;
        if (forward && directionY == -1) return false;
        Map<Coordinate, Piece> pieces = board.getBoard();
        Coordinate current = new Coordinate(from.column() + directionX, from.row() + directionY);
        while(!current.equals(to)) {
            if(pieces.containsKey(current)) return false;
            current = new Coordinate(current.column() + directionX, current.row() + directionY);
        }
        return true;
    }
}
