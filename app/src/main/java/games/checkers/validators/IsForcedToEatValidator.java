package games.checkers.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.MovementValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsForcedToEatValidator implements MovementValidator {
    private boolean forced;

    public IsForcedToEatValidator(boolean forced) {
        this.forced = forced;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        if (!forced) return false;
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPieces().get(movement.from());
        Map<Coordinate, Coordinate> piecesThatCanEat = getPiecesThatCanEat(currentBoard, currentPiece);
        if (!piecesThatCanEat.isEmpty()) {
            if (piecesThatCanEat.containsKey(movement.from())) {
                return !piecesThatCanEat.get(movement.from()).equals(movement.to());
            }
            return true;
        }
        return false;
    }

    public List<Coordinate> getPossibleMoves(Coordinate current) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() - 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() - 2));
        return possibleMoves;
    }

    private Map<Coordinate, Coordinate> getPiecesThatCanEat(Board board, Piece currentPiece) {
        Map<Coordinate, Coordinate> piecesThatCanEat = new HashMap<>();
        for (Map.Entry<Coordinate, Piece> entry : board.getPieces().entrySet()) {
            Coordinate coordinate = entry.getKey();
            Piece piece = entry.getValue();
            if (piece.color() == currentPiece.color()) {
                List<Coordinate> possibleMoves = getPossibleMoves(coordinate);
                for (Coordinate possibleMove : possibleMoves) {
                    if (piece.validator().isValid(List.of(board), new Movement(coordinate, possibleMove))) {
                        piecesThatCanEat.put(coordinate, possibleMove);
                    }
                }
            }
        }
        return piecesThatCanEat;
    }
}
