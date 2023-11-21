package games.chess.factory;

import common.Board;
import common.Coordinate;
import common.Piece;
import common.enums.Color;
import common.enums.PieceType;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static final ChessPieceFactory chessPieceFactory = new ChessPieceFactory();

    public Board createClassicBoard() {
        int id = 0;

        Map<Coordinate, Piece> pieces = new HashMap<>();
        //create pawns
        for (int i = 1; i <= 8; i++) {
            pieces.put(new Coordinate(i, 2), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.PAWN));
            pieces.put(new Coordinate(i, 7), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.PAWN));
        }

        //create rooks
        pieces.put(new Coordinate(1, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.ROOK));
        pieces.put(new Coordinate(8, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.ROOK));
        pieces.put(new Coordinate(1, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.ROOK));
        pieces.put(new Coordinate(8, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.ROOK));

        //create knights
        pieces.put(new Coordinate(2, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KNIGHT));
        pieces.put(new Coordinate(7, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KNIGHT));
        pieces.put(new Coordinate(2, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KNIGHT));
        pieces.put(new Coordinate(7, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KNIGHT));

        //create bishops
        pieces.put(new Coordinate(3, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.BISHOP));
        pieces.put(new Coordinate(6, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.BISHOP));
        pieces.put(new Coordinate(3, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.BISHOP));
        pieces.put(new Coordinate(6, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.BISHOP));

        //create queen
        pieces.put(new Coordinate(4, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.QUEEN));
        pieces.put(new Coordinate(4, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.QUEEN));

        //create king
        pieces.put(new Coordinate(5, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KING));;
        pieces.put(new Coordinate(5, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KING));


        return new Board(8, 8, pieces);
    }

    public Board createArchBoard() {
        int id = 0;

        Map<Coordinate, Piece> pieces = new HashMap<>();
        //create pawns
        for (int i = 1; i <= 8; i++) {
            pieces.put(new Coordinate(i, 2), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.PAWN));
            pieces.put(new Coordinate(i, 7), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.BISHOP));
        }

        //create rooks
        pieces.put(new Coordinate(1, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.CHANCELLOR));
        pieces.put(new Coordinate(8, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.CHANCELLOR));
        pieces.put(new Coordinate(1, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.CHANCELLOR));
        pieces.put(new Coordinate(8, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.CHANCELLOR));

        //create knights
        pieces.put(new Coordinate(2, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KNIGHT));
        pieces.put(new Coordinate(7, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KNIGHT));
        pieces.put(new Coordinate(2, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KNIGHT));
        pieces.put(new Coordinate(7, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KNIGHT));

        //create bishops
        pieces.put(new Coordinate(3, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.ARCHBISHOP));
        pieces.put(new Coordinate(6, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.ARCHBISHOP));
        pieces.put(new Coordinate(3, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.ARCHBISHOP));
        pieces.put(new Coordinate(6, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.ARCHBISHOP));

        //create queen
        pieces.put(new Coordinate(4, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.QUEEN));
        pieces.put(new Coordinate(4, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.QUEEN));

        //create king
        pieces.put(new Coordinate(5, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KING));;
        pieces.put(new Coordinate(5, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KING));


        return new Board(8, 8, pieces);
    }

    public Board createCapaBlancaBoard() {
        int id = 0;

        Map<Coordinate, Piece> pieces = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            pieces.put(new Coordinate(i, 2), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.PAWN));
            pieces.put(new Coordinate(i, 7), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.PAWN));
        }
        //White rooks
        pieces.put(new Coordinate(1, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.ROOK));
        pieces.put(new Coordinate(10, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.ROOK));

        //Black rooks
        pieces.put(new Coordinate(1, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.ROOK));
        pieces.put(new Coordinate(10, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.ROOK));

        //White knights
        pieces.put(new Coordinate(2, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KNIGHT));
        pieces.put(new Coordinate(9, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KNIGHT));

        //Black knights
        pieces.put(new Coordinate(2, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KNIGHT));
        pieces.put(new Coordinate(9, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KNIGHT));

        //archbishops
        pieces.put(new Coordinate(3, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.ARCHBISHOP));
        pieces.put(new Coordinate(3, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.ARCHBISHOP));

        //chancellors
        pieces.put(new Coordinate(8, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.CHANCELLOR));
        pieces.put(new Coordinate(8, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.CHANCELLOR));

        //White bishops
        pieces.put(new Coordinate(4, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.BISHOP));
        pieces.put(new Coordinate(7, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.BISHOP));

        //Black bishops
        pieces.put(new Coordinate(4, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.BISHOP));
        pieces.put(new Coordinate(7, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.BISHOP));

        //White queen
        pieces.put(new Coordinate(5, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.QUEEN));

        //Black queen
        pieces.put(new Coordinate(5, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.QUEEN));

        //White king
        pieces.put(new Coordinate(6, 1), chessPieceFactory.createPiece(String.valueOf(id++), Color.WHITE, PieceType.KING));

        //Black king
        pieces.put(new Coordinate(6, 8), chessPieceFactory.createPiece(String.valueOf(id++), Color.BLACK, PieceType.KING));



        return new Board(8, 10, pieces);
    }
}
