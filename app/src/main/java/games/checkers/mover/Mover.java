package games.checkers.mover;

import common.*;
import common.enums.Color;
import common.enums.PieceType;
import games.checkers.factory.PieceFactory;

import java.util.*;

public class Mover implements SimpleMover {
    PieceFactory pieceFactory = new PieceFactory();
    @Override
    public MoveResult<Board, String> move(List<Board> history, Coordinate from, Coordinate to, Player currentPlayer) {
        Board currentBoard = history.get(history.size() - 1);
        Piece currentPiece = currentBoard.getBoard().get(from);
        if(currentPiece == null) {
            return new MoveResult<>(currentBoard, "No piece in that coordinate");
        }
        if(currentPlayer.getColor() != currentPiece.getColor()) {
            return new MoveResult<>(currentBoard, "Not your turn");
        }
        if(from.equals(to)) {
            return new MoveResult<>(currentBoard, "You can't move to the same place");
        }
        if(currentPiece.getValidator().isValid(history, from, to)) {
            Map<Coordinate, Piece> newBoard = new HashMap<>(currentBoard.getBoard());
            if(isForcedToEat(currentBoard, from, to)) {
                return new MoveResult<>(currentBoard, "You are forced to eat");
            }
            tryEat(currentBoard,newBoard,currentPiece, from, to);
            tryPromotion(currentBoard,newBoard,currentPiece, to);
            newBoard.remove(from);
            return new MoveResult<>(new Board(currentBoard.getRowSize(), currentBoard.getColumnSize(), newBoard), null);
        }
        return new MoveResult<>(currentBoard, "Invalid movement");
    }

    private void tryEat(Board board,Map<Coordinate, Piece> pieces, Piece currentPiece, Coordinate from, Coordinate to) {
        if(Math.abs(from.row() - to.row()) > 1){
            Coordinate pieceInBetween = new Coordinate((from.column() + to.column()) / 2, (from.row() + to.row()) / 2);
            pieces.remove(pieceInBetween);
        }
    }

    private void tryPromotion(Board board, Map<Coordinate, Piece> pieces, Piece currentPiece, Coordinate to) {
        String pieceId = currentPiece.getId();
        Color color = currentPiece.getColor();
        PieceType pieceType = currentPiece.getPieceType();
        if( pieceType != PieceType.QUEEN && (to.row() == board.getRowSize() || to.row() == 1)) {
            pieces.put(to, pieceFactory.createQueen(pieceId, color));
            return;
        }
        pieces.put(to, currentPiece);
    }

    private boolean isForcedToEat(Board board, Coordinate from, Coordinate to){
        Piece currentPiece = board.getBoard().get(from);
        Map<Coordinate,Coordinate> piecesThatCanEat = getPiecesThatCanEat(board, currentPiece);
        if(!piecesThatCanEat.isEmpty()) {
            if(piecesThatCanEat.containsKey(from)) {
                return !piecesThatCanEat.get(from).equals(to);
            }
            return true;
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

    private Map<Coordinate, Coordinate> getPiecesThatCanEat(Board board, Piece currentPiece){
        Map<Coordinate,Coordinate> piecesThatCanEat = new HashMap<>();
        for (Map.Entry<Coordinate, Piece> entry : board.getBoard().entrySet()) {
            Coordinate coordinate = entry.getKey();
            Piece piece = entry.getValue();
            if(piece.getColor() == currentPiece.getColor()) {
                List<Coordinate> possibleMoves = getPossibleMoves(coordinate);
                for (Coordinate possibleMove : possibleMoves) {
                    if (piece.getValidator().isValid(List.of(board), coordinate, possibleMove)) {
                        piecesThatCanEat.put(coordinate,possibleMove);
                    }
                }
            }
        }
        return piecesThatCanEat;
    }
}
