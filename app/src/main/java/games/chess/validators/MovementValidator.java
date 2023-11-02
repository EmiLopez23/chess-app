package games.chess.validators;

import games.chess.board.Board;
import games.chess.board.Coordinate;

import java.util.List;

public interface MovementValidator {
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to);
}
