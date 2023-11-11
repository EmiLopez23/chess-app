package games.checkers.mover;

import common.*;

import java.util.*;

public class Mover implements SimpleMover {
    @Override
    public MoveResult<Board, String> move(List<Board> history, Coordinate from, Coordinate to, Player currentPlayer) {
        Board board = history.get(history.size() - 1);
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
        if(currentPiece.getValidator().isValid(history, from, to)) {
            Map<Coordinate, Piece> newBoard = new HashMap<>(board.getBoard());
            if(isForcedToEat(board, from)) {
                return new MoveResult<>(board, "You are forced to eat");
            }
            tryEat(board,newBoard,currentPiece, from, to);

            newBoard.put(to, currentPiece);
            newBoard.remove(from);
            return new MoveResult<>(new Board(board.getRowSize(), board.getColumnSize(), newBoard), null);
        }
        return new MoveResult<>(board, "Invalid movement");
    }

    private void tryEat(Board board,Map<Coordinate, Piece> pieces, Piece currentPiece, Coordinate from, Coordinate to) {
        if(Math.abs(from.row() - to.row()) > 1){
            Coordinate pieceInBetween = new Coordinate((from.column() + to.column()) / 2, (from.row() + to.row()) / 2);
            pieces.remove(pieceInBetween);
        }
    }

    private boolean isForcedToEat(Board board, Coordinate from){
        Piece currentPiece = board.getBoard().get(from);
        List<String> piecesThatCanEat = new ArrayList<>();
        for (Map.Entry<Coordinate, Piece> entry : board.getBoard().entrySet()) {
            Coordinate coordinate = entry.getKey();
            Piece piece = entry.getValue();
            if(piece.getColor() == currentPiece.getColor()) {
                List<Coordinate> possibleMoves = getPossibleMoves(coordinate);
                for (Coordinate possibleMove : possibleMoves) {
                    if (piece.getValidator().isValid(List.of(board), coordinate, possibleMove)) {
                        piecesThatCanEat.add(piece.getId());
                    }
                }
            }
        }
        if(!piecesThatCanEat.isEmpty()) {
            return !piecesThatCanEat.contains(currentPiece.getId());
        }
        return false;
    }

    private List<Coordinate> getPossibleMoves(Coordinate current){
        List<Coordinate> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() + 2));
        possibleMoves.add(new Coordinate(current.column() + 2, current.row() - 2));
        possibleMoves.add(new Coordinate(current.column() - 2, current.row() - 2));
        return possibleMoves;
    }
}
