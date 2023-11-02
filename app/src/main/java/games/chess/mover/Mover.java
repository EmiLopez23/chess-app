package games.chess.mover;

import common.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mover implements SimpleMover {
    @Override
    public MoveResult<Board,String> move(List<Board> boardHistory, Coordinate from, Coordinate to, Player currentPlayer) {
        Board board = boardHistory.get(boardHistory.size() - 1);
        Piece currentPiece = board.getBoard().get(from);
        if(currentPiece == null) {
            return new MoveResult<>(board, "No piece in that coordinate");
        }
        if(currentPlayer.getColor() != currentPiece.getColor()) {
            return new MoveResult<>(board, "Not your turn");
        }
        if(from.equals(to)) {
            return new MoveResult<>(board, "You can't move to the same place");
        }
        if(currentPiece.getValidator().isValid(boardHistory, from, to)) {
                Map<Coordinate, Piece> newBoard = new HashMap<>(board.getBoard());
                newBoard.put(to, currentPiece);
                newBoard.remove(from);
                return new MoveResult<>(new Board(board.getRowSize(), board.getColumnSize(), newBoard), null);
        }
        return new MoveResult<>(board, "Invalid movement");
    }

}