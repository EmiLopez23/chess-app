package common.validators;

import common.Board;
import common.Movement;
import common.Piece;

import java.util.List;

public class NoSelfEatingValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        Piece targetPiece = board.getPiece(movement.to());
        if (targetPiece == null) return true;
        return board.getPiece(movement.from()).color() != targetPiece.color();
    }
}
