package games.checkers.factory;

import common.Board;
import common.Coordinate;
import common.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private final PieceFactory pieceFactory = new PieceFactory();

    public Board createClassicBoard() {
        int id = 0;
        Map<Coordinate, Piece> pieces = new HashMap<>();
        pieces.put(new Coordinate(1, 7), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(2, 6), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(2, 8), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(3, 7), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(4, 6), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(4, 8), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(5, 7), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(6, 6), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(6, 8), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 7), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(8, 6), pieceFactory.createDarkPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(8, 8), pieceFactory.createDarkPiece(String.valueOf(id++)));
        //create white pawns
        pieces.put(new Coordinate(1, 1), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(1, 3), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(2, 2), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(3, 1), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(3, 3), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(4, 2), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(5, 1), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(5, 3), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(6, 2), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 1), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(7, 3), pieceFactory.createLightPiece(String.valueOf(id++)));
        pieces.put(new Coordinate(8, 2), pieceFactory.createLightPiece(String.valueOf(id++)));

        return new Board(8, 8, pieces);
    }
}
