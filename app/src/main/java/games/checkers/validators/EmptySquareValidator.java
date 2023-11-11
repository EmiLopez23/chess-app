package games.checkers.validators;

import common.Board;
import common.Coordinate;
import common.Piece;
import common.validators.MovementValidator;

import java.util.List;

public class EmptySquareValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        return board.getPiece(to) == null;
    }
}
