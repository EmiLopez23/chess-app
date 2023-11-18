package games.chess.factory;

import common.Board;
import common.Coordinate;
import common.Piece;
import common.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static final PieceFactory pieceFactory = new PieceFactory();

    public Board createClassicBoard() {
        int id = 0;

        Map<Coordinate, Piece> pieces = new HashMap<>();
        //create white pawns
        for (int i = 1; i <= 8; i++) {
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

    public Board createArchBoard() {
        int id = 0;

        Map<Coordinate, Piece> pieces = new HashMap<>();
        //create white pawns
        for (int i = 1; i <= 8; i++) {
            pieces.put(new Coordinate(i, 2), pieceFactory.createWhitePawn(String.valueOf(id++)));
            pieces.put(new Coordinate(i, 7), pieceFactory.createBlackPawn(String.valueOf(id++)));
        }

        //create white rooks
        pieces.put(new Coordinate(1, 1), pieceFactory.createChancellor(String.valueOf(id++), Color.WHITE));
        pieces.put(new Coordinate(8, 1), pieceFactory.createChancellor(String.valueOf(id++), Color.WHITE));
        pieces.put(new Coordinate(1, 8), pieceFactory.createChancellor(String.valueOf(id++), Color.BLACK));
        pieces.put(new Coordinate(8, 8), pieceFactory.createChancellor(String.valueOf(id++), Color.BLACK));

        //create white knights
        pieces.put(new Coordinate(2, 1), pieceFactory.createWhiteKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 1), pieceFactory.createWhiteKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(2, 8), pieceFactory.createBlackKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 8), pieceFactory.createBlackKnight(String.valueOf(id++)));

        //create white bishops
        pieces.put(new Coordinate(3, 1), pieceFactory.createArchBishop(String.valueOf(id++), Color.WHITE));
        pieces.put(new Coordinate(6, 1), pieceFactory.createArchBishop(String.valueOf(id++), Color.WHITE));
        pieces.put(new Coordinate(3, 8), pieceFactory.createArchBishop(String.valueOf(id++), Color.BLACK));
        pieces.put(new Coordinate(6, 8), pieceFactory.createArchBishop(String.valueOf(id++), Color.BLACK));

        //create white queen
        pieces.put(new Coordinate(4, 1), pieceFactory.createWhiteQueen(String.valueOf(id++)));
        pieces.put(new Coordinate(4, 8), pieceFactory.createBlackQueen(String.valueOf(id++)));

        //create white king
        pieces.put(new Coordinate(5, 1), pieceFactory.createWhiteKing(String.valueOf(id++)));
        pieces.put(new Coordinate(5, 8), pieceFactory.createBlackKing(String.valueOf(id++)));


        return new Board(8, 8, pieces);
    }

    public Board createCapaBlancaBoard() {
        int id = 0;

        Map<Coordinate, Piece> pieces = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            pieces.put(new Coordinate(i, 2), pieceFactory.createWhitePawn(String.valueOf(id++)));
            pieces.put(new Coordinate(i, 7), pieceFactory.createBlackPawn(String.valueOf(id++)));
        }
        //White rooks
        pieces.put(new Coordinate(1, 1), pieceFactory.createWhiteRook(String.valueOf(id++)));
        pieces.put(new Coordinate(10, 1), pieceFactory.createWhiteRook(String.valueOf(id++)));

        //Black rooks
        pieces.put(new Coordinate(1, 8), pieceFactory.createBlackRook(String.valueOf(id++)));
        pieces.put(new Coordinate(10, 8), pieceFactory.createBlackRook(String.valueOf(id++)));

        //White knights
        pieces.put(new Coordinate(2, 1), pieceFactory.createWhiteKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(9, 1), pieceFactory.createWhiteKnight(String.valueOf(id++)));

        //Black knights
        pieces.put(new Coordinate(2, 8), pieceFactory.createBlackKnight(String.valueOf(id++)));
        pieces.put(new Coordinate(9, 8), pieceFactory.createBlackKnight(String.valueOf(id++)));

        //archbishops
        pieces.put(new Coordinate(3, 1), pieceFactory.createArchBishop(String.valueOf(id++), Color.WHITE));
        pieces.put(new Coordinate(3, 8), pieceFactory.createArchBishop(String.valueOf(id++), Color.BLACK));

        //chancellors
        pieces.put(new Coordinate(8, 1), pieceFactory.createChancellor(String.valueOf(id++), Color.WHITE));
        pieces.put(new Coordinate(8, 8), pieceFactory.createChancellor(String.valueOf(id++), Color.BLACK));

        //White bishops
        pieces.put(new Coordinate(4, 1), pieceFactory.createWhiteBishop(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 1), pieceFactory.createWhiteBishop(String.valueOf(id++)));

        //Black bishops
        pieces.put(new Coordinate(4, 8), pieceFactory.createBlackBishop(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 8), pieceFactory.createBlackBishop(String.valueOf(id++)));

        //White queen
        pieces.put(new Coordinate(5, 1), pieceFactory.createWhiteQueen(String.valueOf(id++)));

        //Black queen
        pieces.put(new Coordinate(5, 8), pieceFactory.createBlackQueen(String.valueOf(id++)));

        //White king
        pieces.put(new Coordinate(6, 1), pieceFactory.createWhiteKing(String.valueOf(id++)));

        //Black king
        pieces.put(new Coordinate(6, 8), pieceFactory.createBlackKing(String.valueOf(id++)));



        return new Board(8, 10, pieces);
    }
}
