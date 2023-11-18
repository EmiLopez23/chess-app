package common.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;

import java.util.List;

public class AllowEnemyPieceInBetweenValidator implements MovementValidator {
    private final boolean allowMove; //should the piece be allowed to move to the destination if there is a piece in between?

    public AllowEnemyPieceInBetweenValidator(boolean allowMove) {
        this.allowMove = allowMove;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        if(Math.abs(movement.from().row() - movement.to().row()) <= 1 && Math.abs(movement.from().column() - movement.to().column()) <= 1) return true;

        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPiece(movement.from());
        Coordinate current = movement.from().add(movement.direction());

        while (!current.equals(movement.to())) {
            Piece piece = currentBoard.getPiece(current);
            if (isOutOfBounds(currentBoard, current)) return false; //if the piece is out of bounds, return false
            if ( piece != null ) {
                if (piece.getColor() == currentPiece.getColor()) return false; //if there is a piece of the same color, return false (not allowed to move
                if (piece.getColor() != currentPiece.getColor()) return allowMove;
            }
            current = current.add(movement.direction());
        }
        return true;
    }

    private boolean isOutOfBounds(Board board, Coordinate current){
        return current.row() > board.getRowSize() || current.row() < 1 || current.column() > board.getColumnSize() || current.column() < 1;
    }
}
