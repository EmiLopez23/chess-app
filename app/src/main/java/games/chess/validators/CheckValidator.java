package games.chess.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.enums.Color;
import common.enums.PieceType;
import common.validators.MovementValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckValidator implements MovementValidator {
    public Coordinate getKingCoordinate(Board board, Color color) {
        for (Map.Entry<Coordinate, Piece> entry : board.getPieces().entrySet()) {
            Piece value = entry.getValue();
            if (value.color() == color && value.pieceType() == PieceType.KING) return entry.getKey();
        }
        return null;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPiece(movement.to());
        Coordinate kingCoordinate = getKingCoordinate(currentBoard, currentPiece.color());
        for (Map.Entry<Coordinate, Piece> piece : currentBoard.getPieces().entrySet()) {
            if (piece.getValue().color() != currentPiece.color() && piece.getValue().validator().isValid(new ArrayList<>(List.of(currentBoard)), new Movement(piece.getKey(), kingCoordinate)))
                return true;
        }
        return false;
    }
}
