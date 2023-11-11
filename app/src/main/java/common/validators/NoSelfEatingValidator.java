package common.validators;

import common.Board;
import common.Coordinate;
import common.validators.MovementValidator;
import common.Piece;

import java.util.List;

public class NoSelfEatingValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        if(board.getPiece(from) == null) return false;
        Piece targetPiece = board.getPiece(to);
        if(targetPiece == null) return true;
        return board.getPiece(from).getColor() != targetPiece.getColor();
    }
}
