package factory;

import board.Board;
import board.Coordinate;
import board.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static final PieceFactory pieceFactory = new PieceFactory();

    public Board createClassicBoard(){
        int id = 0;

        Map<Coordinate, Piece> pieces = new HashMap<>();
        //create white pawns
        for(int i = 1; i<= 8; i++){
            pieces.put(new Coordinate(i, 2), pieceFactory.createWhitePawn(String.valueOf(id++)));
            pieces.put(new Coordinate(i, 7), pieceFactory.createBlackPawn(String.valueOf(id++)));
        }

        //create white rooks
        pieces.put(new Coordinate(1, 1), pieceFactory.createWhiteRook(String.valueOf(id++)));
        pieces.put(new Coordinate(8, 1), pieceFactory.createWhiteRook(String.valueOf(id++)));
        pieces.put(new Coordinate(1, 8), pieceFactory.createBlackRook(String.valueOf(id++)));
        pieces.put(new Coordinate(8, 8), pieceFactory.createBlackRook(String.valueOf(id++)));

        //create white knights
        pieces.put(new Coordinate(2, 1), pieceFactory.createWhiteKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 1), pieceFactory.createWhiteKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(2, 8), pieceFactory.createBlackKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 8), pieceFactory.createBlackKnight(String.valueOf(id++)));

        //create white bishops
        pieces.put(new Coordinate(3, 1), pieceFactory.createWhiteBishop(String.valueOf(id++)));
        pieces.put(new Coordinate(6, 1), pieceFactory.createWhiteBishop(String.valueOf(id++)));
        pieces.put(new Coordinate(3, 8), pieceFactory.createBlackBishop(String.valueOf(id++)));
        pieces.put(new Coordinate(6, 8), pieceFactory.createBlackBishop(String.valueOf(id++)));

        //create white queen
        pieces.put(new Coordinate(4, 1), pieceFactory.createWhiteQueen(String.valueOf(id++)));
        pieces.put(new Coordinate(4, 8), pieceFactory.createBlackQueen(String.valueOf(id++)));

        //create white king
        pieces.put(new Coordinate(5, 1), pieceFactory.createWhiteKing(String.valueOf(id++)));
        pieces.put(new Coordinate(5, 8), pieceFactory.createBlackKing(String.valueOf(id++)));


        return new Board(8, 8, pieces);
    }
}
