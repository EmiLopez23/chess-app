package common.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;

import java.util.List;

public class FirstMoveValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        if (boardHistory.size() == 1) return true; //if boardList is size 1, return true
        Piece currentPiece = boardHistory.get(boardHistory.size() - 1).getPiece(movement.from());
        Coordinate initialCoordinate = boardHistory.get(0).getCoordinate(currentPiece);
        for (Board board : boardHistory) {
            if (board.getCoordinate(currentPiece) != initialCoordinate) return false; //if the current piece moved from its initial position, return false
        }
        return true;
    }
}
