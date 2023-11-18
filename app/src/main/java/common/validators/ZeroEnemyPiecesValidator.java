package common.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.enums.Color;

import java.util.List;
import java.util.Map;

public class ZeroEnemyPiecesValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Color currentPieceColor = currentBoard.getPiece(movement.to()).getColor();
        for (Map.Entry<Coordinate, Piece> entry : currentBoard.getPieces().entrySet()) {
            Piece piece = entry.getValue();
            if (piece != null && piece.getColor() != currentPieceColor) {
                return false;
            }
        }
        return true;

    }
}
