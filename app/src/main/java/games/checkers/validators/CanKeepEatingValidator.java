package games.checkers.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.MovementValidator;

import java.util.List;

public class CanKeepEatingValidator implements MovementValidator {

    List<Coordinate> possibleAttacks;

    public CanKeepEatingValidator(List<Coordinate> possibleAttacks) {
        this.possibleAttacks = possibleAttacks;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        if (!hasEaten(boardHistory.get(boardHistory.size() - 2), movement)) return false;
        Piece currentPiece = currentBoard.getPieces().get(movement.to());
        List<Coordinate> possibleMoves = getPossibleMoves(movement.to());
        for (Coordinate possibleMove : possibleMoves) {
            if (currentPiece.validator().isValid(boardHistory, new Movement(movement.to(), possibleMove))) {
                return true;
            }
        }
        return false;
    }

    public List<Coordinate> getPossibleMoves(Coordinate current) {
        return possibleAttacks.stream().map(current::add).toList();
    }

    public boolean hasEaten(Board board, Movement movement) {
        if(movement.colDifference() == 1 && movement.rowDifference() == 1) return false; // if move was just one square return false
        Coordinate pieceInBetween = new Coordinate((movement.from().column() + movement.to().column()) / 2, (movement.from().row() + movement.to().row()) / 2);
        return board.getPiece(pieceInBetween) != null;
    }
}
