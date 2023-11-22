package games.checkers.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.validators.MovementValidator;

import java.util.List;

public class CanKeepEatingValidator implements MovementValidator {

    List<Coordinate> possibleAttacks;

    MovementValidator hasEaten;

    public CanKeepEatingValidator(List<Coordinate> possibleAttacks, MovementValidator hasEaten) {
        this.possibleAttacks = possibleAttacks;
        this.hasEaten = hasEaten;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        if (!hasEaten.isValid(boardHistory, movement)) return false;
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
}
