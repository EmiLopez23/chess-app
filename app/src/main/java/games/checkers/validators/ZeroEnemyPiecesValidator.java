package games.checkers.validators;

import common.Board;
import common.Coordinate;
import common.Piece;
import common.enums.Color;
import common.validators.MovementValidator;

import java.util.List;
import java.util.Map;

public class ZeroEnemyPiecesValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Color currentPieceColor = currentBoard.getPiece(to).getColor();
        for (Map.Entry<Coordinate, Piece> entry : currentBoard.getBoard().entrySet()) {
            Piece piece = entry.getValue();
            if (piece != null && piece.getColor() != currentPieceColor) {
                return false;
            }
        }
        return true;

    }
}
