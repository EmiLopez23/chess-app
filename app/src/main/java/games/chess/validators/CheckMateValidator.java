package games.chess.validators;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;
import common.enums.Color;
import common.validators.MovementValidator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
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
        Color currentTurn = getCurrentTurn(movement, currentBoard);
        Map<Coordinate, Piece> pieces = currentBoard.getPieces();

        return cantAvoidCheck(boardHistory, currentBoard, pieces, currentTurn);
    }

    @NotNull
    private Color getCurrentTurn(Movement movement, Board currentBoard) {
        return currentBoard.getPieces().get(movement.to()).color() == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    private boolean cantAvoidCheck(List<Board> boardHistory, Board currentBoard, Map<Coordinate, Piece> pieces, Color currentTurn) {
        for (int row = 1; row < currentBoard.getRowSize(); row++) {
            for (int col = 1; col < currentBoard.getColumnSize(); col++) {
                Coordinate currentPosition = new Coordinate(col, row); //current standing position
                for (Map.Entry<Coordinate, Piece> piece : pieces.entrySet()) {
                    Piece currentPiece = piece.getValue(); //current piece of the loop
                    Coordinate currentPiecePosition = piece.getKey(); //current coordinate of the loop
                    if(!canMove(boardHistory, currentTurn, currentPiece, currentPiecePosition, currentPosition)) {
                        continue;
                    }
                    List<Board> newHistory = addNewHistory(boardHistory, currentBoard, currentPiecePosition, currentPosition, currentPiece);
                    if (!checkValidator.isValid(newHistory, new Movement(currentPiecePosition, currentPosition))) return false;
                }
            }

        }
        return true;
    }

    private boolean canMove(List<Board> boardHistory, Color currentTurn, Piece currentPiece, Coordinate currentPiecePosition, Coordinate currentPosition) {
        return !isOthersTurn(currentTurn, currentPiece) && currentPiece.validator().isValid(boardHistory, new Movement(currentPiecePosition, currentPosition));
    }

    @NotNull
    private List<Board> addNewHistory(List<Board> boardHistory, Board currentBoard, Coordinate currentPiecePosition, Coordinate currentPosition, Piece currentPiece) {
        Map<Coordinate, Piece> newPieces = new HashMap<>(currentBoard.getPieces());
        newPieces.remove(currentPiecePosition);
        newPieces.put(currentPosition, currentPiece); //remove the piece from the actual position and move it to destination
        List<Board> newHistory = new ArrayList<>(boardHistory);
        Board newBoard = new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newPieces);
        newHistory.add(newBoard);
        return newHistory;
    }

    private boolean isOthersTurn(Color currentTurn, Piece currentPiece) {
        return currentPiece.color() != currentTurn;
    }
}


