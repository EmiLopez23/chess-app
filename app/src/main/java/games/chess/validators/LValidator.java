package games.chess.validators;

import common.Board;
import common.Coordinate;
import common.validators.MovementValidator;

import java.util.List;

public class LValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        if(board.getPiece(from) == null) return false;
        if (from.column() == to.column() && from.row() == to.row()) return false;
        if(Math.abs(from.column() - to.column()) == 2 && Math.abs(from.row() - to.row()) == 1) return true;
        return Math.abs(from.column() - to.column()) == 1 && Math.abs(from.row() - to.row()) == 2;
    }
}
