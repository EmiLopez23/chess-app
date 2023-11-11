package games.checkers.validators;

import common.Board;
import common.Coordinate;
import common.Piece;
import common.validators.MovementValidator;

import java.util.List;

public class PieceInBetweenValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        if(Math.abs(from.column() - to.column()) < 2 && Math.abs(from.row() - to.row()) < 2) return false;
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPiece(from);
        int directionX = from.column() < to.column() ? 1 : -1;
        int directionY = from.row() < to.row() ? 1 : -1;
        Coordinate current = new Coordinate(from.column() + directionX, from.row() + directionY);
        while (!current.equals(to)){
            Piece piece = currentBoard.getPiece(current);
            if( piece != null && piece.getColor() != currentPiece.getColor()) return true;
            current = new Coordinate(current.column() + directionX, current.row() + directionY);
        }
        return false;
    }
}
