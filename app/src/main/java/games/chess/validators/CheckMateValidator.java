package games.chess.validators;
import common.*;
import common.enums.Color;
import common.validators.MovementValidator;
import games.chess.mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckMateValidator implements MovementValidator {
    private final MovementValidator checkValidator;
    public CheckMateValidator(MovementValidator checkValidator) {
        this.checkValidator = checkValidator;
    }
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board previousBoard = boardHistory.get(boardHistory.size() - 2);
        Color currentTurn = previousBoard.getBoard().get(from).getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Map<Coordinate, Piece> pieces = currentBoard.getBoard();

        for (int row = 1; row < currentBoard.getRowSize(); row++) {
            for (int col = 1; col < currentBoard.getColumnSize(); col++) {
                Coordinate currentPosition = new Coordinate(col, row);
                for (Map.Entry<Coordinate, Piece> piece : pieces.entrySet()) {
                    Piece currentPiece = piece.getValue();
                    Coordinate currentPiecePosition = piece.getKey();
                    Board newBoard = currentBoard.copy();
                    if(currentPiece.getColor() == currentTurn && currentPiece.getValidator().isValid(boardHistory, currentPiecePosition, currentPosition)) {
                        newBoard.getBoard().remove(currentPiecePosition);
                        newBoard.getBoard().put(currentPosition, currentPiece);
                        List<Board> newHistory = new ArrayList<>(boardHistory);
                        newHistory.add(newBoard);
                        if (checkValidator.isValid(newHistory, currentPiecePosition, currentPosition)) return false;
                    }
                }
            }

        }
        return true;
    }
}


