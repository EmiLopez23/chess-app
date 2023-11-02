package games.chess.validators;

import games.chess.board.Board;
import games.chess.board.Coordinate;
import games.chess.board.Piece;

import java.util.List;

public class NoSelfEatingValidator implements MovementValidator{
    @Override
    public boolean isValid(List<Board> boardHistory, Coordinate from, Coordinate to) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        if(board.getPiece(from) == null) return false;
        Piece targetPiece = board.getPiece(to);
        if(targetPiece == null) return true;
        return board.getPiece(from).getColor() != targetPiece.getColor();
    }
}
