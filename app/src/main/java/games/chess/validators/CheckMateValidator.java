package games.chess.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.enums.Color;
import common.validators.MovementValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckMateValidator implements MovementValidator {
    private final MovementValidator checkValidator;

    public CheckMateValidator(MovementValidator checkValidator) {
        this.checkValidator = checkValidator;
    }

    @Override
    public boolean isValid(List<Board> boardHistory, Movement movement) {
        Board currentBoard = boardHistory.get(boardHistory.size() - 1);
        Color currentTurn = currentBoard.getPieces().get(movement.to()).color() == Color.WHITE ? Color.BLACK : Color.WHITE;
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();

        return cantAvoidCheck(boardHistory, currentBoard, pieces, currentTurn);
    }

    private boolean cantAvoidCheck(List<Board> boardHistory, Board currentBoard, Map<Coordinate, Piece> pieces, Color currentTurn) {
        for (int row = 1; row < currentBoard.getRowSize(); row++) {
            for (int col = 1; col < currentBoard.getColumnSize(); col++) {
                Coordinate currentPosition = new Coordinate(col, row); //current standing position
                for (Map.Entry<Coordinate, Piece> piece : pieces.entrySet()) {
                    Piece currentPiece = piece.getValue(); //current piece of the loop
                    Coordinate currentPiecePosition = piece.getKey(); //current coordinate of the loop
                    Board newBoard = currentBoard.copy();
                    if(isOthersTurn(currentTurn, currentPiece)) continue; //if piece is not of current turn player, pass to next iteration
                    if (!currentPiece.validator().isValid(boardHistory, new Movement(currentPiecePosition, currentPosition))) continue; //if piece move is not valid pass to next iteration
                    newBoard.getPieces().remove(currentPiecePosition);
                    newBoard.getPieces().put(currentPosition, currentPiece); //remove the piece from the actual position and move it to destination
                    List<Board> newHistory = new ArrayList<>(boardHistory);
                    newHistory.add(newBoard);
                    if (!checkValidator.isValid(newHistory, new Movement(currentPiecePosition, currentPosition))) return false;
                }
            }

        }
        return true;
    }

    private static boolean isOthersTurn(Color currentTurn, Piece currentPiece) {
        return currentPiece.color() != currentTurn;
    }
}


