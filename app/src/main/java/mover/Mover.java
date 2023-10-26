package mover;

import board.Board;
import board.Coordinate;
import board.Piece;
import board.Player;
import game.Game;
import validators.MovementValidator;

import java.util.Map;

public class Mover {
    public MoveResult<Board,Boolean> move(Board board, Coordinate from, Coordinate to, Player currentPlayer) {
        if(from.equals(to)) {
            return new MoveResult<>(board, false);
        }
        Piece currentPiece = board.getBoard().get(from);
        if(currentPiece == null) {
            return new MoveResult<>(board, false);
        }
        if(currentPlayer.getColor() != currentPiece.getColor()) {
            return new MoveResult<>(board, false);
        }
        Map<Coordinate, Piece> newBoard = board.getBoard();
        if(currentPiece.getValidator().isValid(board, from, to)) {
                newBoard.put(to, currentPiece);
                newBoard.remove(from);
                return new MoveResult<>(new Board(board.getRowSize(), board.getColumnSize(), newBoard), true);
        }
        return new MoveResult<>(board, false);
    }

}