package games.chess.validators;

import common.Board;
import common.Movement;
import common.Piece;
import common.validators.MovementValidator;

import java.util.List;
import java.util.Objects;

public class HasEatenValidator implements MovementValidator {
    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board pastBoard = boardHistory.get(boardHistory.size()-2);
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Piece targetPiece = pastBoard.getPiece(movement.to());
        Piece currentPieceDestiny = currentBoard.getPiece(movement.to());
        if( targetPiece == null) return false;
        return !Objects.equals(currentPieceDestiny.id(), targetPiece.id());
    }
}
