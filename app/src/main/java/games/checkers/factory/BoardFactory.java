package games.checkers.factory;

import common.Board;
import common.Coordinate;
import common.Piece;
import common.enums.Color;
import common.enums.PieceType;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private final CheckersPieceFactory checkersPieceFactory = new CheckersPieceFactory();

    public Board createClassicBoard() {
        int id = 0;
        Map<Coordinate, Piece> pieces = new HashMap<>();
        pieces.put(new Coordinate(1, 7), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(2, 6), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(2, 8), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(3, 7), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(4, 6), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(4, 8), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(5, 7), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(6, 6), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(6, 8), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(7, 7), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(8, 6), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        pieces.put(new Coordinate(8, 8), checkersPieceFactory.createPiece(String.valueOf(id++),Color.BLACK, PieceType.PAWN));
        //create white pawns
        pieces.put(new Coordinate(1, 1), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(1, 3), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(2, 2), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(3, 1), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(3, 3), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(4, 2), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(5, 1), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(5, 3), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(6, 2), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(7, 1), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(7, 3), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));
        pieces.put(new Coordinate(8, 2), checkersPieceFactory.createPiece(String.valueOf(id++),Color.WHITE, PieceType.PAWN));

        return new Board(8, 8, pieces);
    }
}
