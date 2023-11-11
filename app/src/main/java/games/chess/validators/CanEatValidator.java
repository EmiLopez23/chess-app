package games.chess.validators;

import common.Board;
import common.Coordinate;
import common.validators.MovementValidator;
import common.Piece;

import java.util.List;

public class CanEatValidator implements MovementValidator {
     private final boolean possible;
    public CanEatValidator(boolean possible){
        this.possible = possible;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = currentBoard.getPiece(from);
        Piece targetPiece = currentBoard.getPiece(to);
        if(targetPiece == null){
            return !possible;
        };

        return possible && currentPiece.getColor() != targetPiece.getColor();

    }
}
