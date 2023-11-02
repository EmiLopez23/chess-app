package games.chess.validators;
import common.*;
import games.chess.enums.Color;
import games.chess.mover.Mover;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckMateValidator implements MovementValidator {
    private MovementValidator checkValidator;
    private Mover mover;
    public CheckMateValidator(MovementValidator checkValidator, Mover mover) {
        this.checkValidator = checkValidator;
        this.mover = mover;
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
                    Board newBoard = currentBoard.copy();
                    MoveResult<Board, String> moveResult = mover.move(new ArrayList<>(List.of(newBoard)), piece.getKey(), currentPosition, new Player(currentTurn));
                    if (moveResult.isValid()) {
                        List<Board> newHistory = new ArrayList<>(boardHistory);
                        newHistory.add(moveResult.getBoard());
                        if (checkValidator.isValid(newHistory, piece.getKey(), currentPosition)) return false;
                    }
                }
            }
            
        }
        return true;
    }
}


