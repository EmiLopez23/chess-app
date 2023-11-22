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
    private final boolean forced;

    private final List<Coordinate> possibleAttacks;

    public IsForcedToEatValidator(boolean forced, List<Coordinate> possibleAttacks) {
        this.forced = forced;
        this.possibleAttacks = possibleAttacks;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        if (!forced) return false;
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPieces().get(movement.from());
        Map<Coordinate, Coordinate> piecesThatCanEat = getPiecesThatCanEat(currentBoard, currentPiece);
        if (piecesThatCanEat.isEmpty()) {
            return false;
        }
        if (!piecesThatCanEat.containsKey(movement.from())) {
            return true;
        }
        return !piecesThatCanEat.get(movement.from()).equals(movement.to());
    }

    public List<Coordinate> getPossibleMoves(Coordinate current) {
        return possibleAttacks.stream().map(current::add).toList();
    }

    private Map<Coordinate, Coordinate> getPiecesThatCanEat(Board board, Piece currentPiece) {
        Map<Coordinate, Coordinate> piecesThatCanEat = new HashMap<>();
        for (Map.Entry<Coordinate, Piece> entry : board.getPieces().entrySet()) {
            Coordinate coordinate = entry.getKey();
            Piece piece = entry.getValue();
            if (piece.color() != currentPiece.color()) {continue;}
            List<Coordinate> possibleMoves = getPossibleMoves(coordinate);
            for (Coordinate possibleMove : possibleMoves) {
                if (piece.validator().isValid(List.of(board), new Movement(coordinate, possibleMove))) {
                    piecesThatCanEat.put(coordinate, possibleMove);
                }
            }
        }
        return piecesThatCanEat;
    }
}
