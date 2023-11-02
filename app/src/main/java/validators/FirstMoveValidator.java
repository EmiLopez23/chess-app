package validators;

import board.Board;
import board.Coordinate;
import board.Piece;

import java.util.List;
import java.util.Objects;

public class FirstMoveValidator implements MovementValidator{
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        if (boardHistory.size() == 1) return true;
        Piece currentPiece = boardHistory.get(boardHistory.size() - 1).getPiece(from);
        Coordinate initialCoordinate = boardHistory.get(0).getCoordinate(currentPiece);
        for (Board board : boardHistory){
            if (board.getCoordinate(currentPiece) != initialCoordinate) return false;
        }
        return true;}
}
