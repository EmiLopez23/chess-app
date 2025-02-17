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
        return createCustomBoard(8, 8, 3);
    }

    public Board createCustomBoard(int colNumber, int rowNumber, int rowsPerColor){
        int id = 0;
        Map<Coordinate, Piece> pieces = new HashMap<>();
        for (int row = 1; row <= rowNumber; row++) {
            for (int col = 1; col <= colNumber; col++) {
                if ((row + col) % 2 == 0) {
                    Color pieceColor;
                    if (row <= rowsPerColor) {
                        pieceColor = Color.WHITE;
                    } else if (row > rowNumber - rowsPerColor) {
                        pieceColor = Color.BLACK;
                    } else {
                        continue;
                    }
                    PieceType pieceType = PieceType.PAWN;
                    pieces.put(new Coordinate(col, row), checkersPieceFactory.createPiece(String.valueOf(id++), pieceColor, pieceType));
                }
            }
        }
        return new Board(colNumber, rowNumber, pieces);
    }
}
