package games.chess.validators;

import common.Board;
import common.Coordinate;
import common.validators.MovementValidator;
import common.Piece;
import common.enums.Color;
import common.enums.PieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckValidator implements MovementValidator {
    public Coordinate getKingCoordinate(Board board, Color color) {
        for(Map.Entry<Coordinate, Piece> entry : board.getBoard().entrySet()){
            Piece value = entry.getValue();
            if (value.getColor() == color && value.getPieceType() == PieceType.KING) return entry.getKey();
        }
        return null;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPiece(to);
        Color enemyColor = currentPiece.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        Coordinate kingCoordinate = getKingCoordinate(currentBoard, currentPiece.getColor());
        for (Map.Entry<Coordinate, Piece> piece : currentBoard.getBoard().entrySet()) {
            if (piece.getValue().getColor() == enemyColor && piece.getValue().getValidator().isValid(new ArrayList<>(List.of(currentBoard)),piece.getKey(),kingCoordinate)) return false;
        }
        return true;
    }
}
