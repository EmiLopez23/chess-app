package games.chess.validators;

import games.chess.board.Board;
import games.chess.board.Coordinate;
import games.chess.board.Piece;

import java.util.List;
import java.util.Map;

public class HorizontalValidator implements MovementValidator{
    private final boolean right;

    public HorizontalValidator(boolean right) {
        this.right = right;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        if(from.row() != to.row()) return false;
        Board board = boardHistory.get(boardHistory.size() - 1);
        int direction = from.column() < to.column() ? 1 : - 1;
        if(right && direction == -1) return false;
        Coordinate current = new Coordinate(from.column() + direction, from.row());;
        Map<Coordinate, Piece> pieces = board.getBoard();
        while(!current.equals(to)) {
            if(pieces.containsKey(current)) return false;
            if (current.column() < 1 || current.column() > board.getColumnSize()) return false;
            current = new Coordinate(current.column() + direction , current.row());
        }
        return true;
    }
}
