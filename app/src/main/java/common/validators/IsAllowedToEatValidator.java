package common.validators;

import common.Board;
import common.Movement;
import common.Piece;
import common.validators.MovementValidator;

import java.util.List;

public class IsAllowedToEatValidator implements MovementValidator { //if chess piece is allowed to eat
    private final boolean possible;

    public IsAllowedToEatValidator(boolean possible) {
        this.possible = possible;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPiece(movement.from());
        Piece targetPiece = currentBoard.getPiece(movement.to());
        if (targetPiece == null) {
            return !possible;
        }
        return possible && currentPiece.color() != targetPiece.color();

    }
}
