package common.validators;

import common.Board;
import common.Movement;

import java.util.List;

public class EmptySquareValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        return board.getPiece(movement.to()) == null;
    }
}
