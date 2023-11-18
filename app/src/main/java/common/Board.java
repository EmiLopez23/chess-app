package common;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Coordinate, Piece> pieces;
    private final int rowSize;
    private final int columnSize;

    public Board(int rowSize, int columnSize) {
        this.pieces = new HashMap<>();
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public Board(int rowSize, int columnSize, Map<Coordinate, Piece> pieces) {
        this.pieces = pieces;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public Piece getPiece(Coordinate coordinate) {
        return this.pieces.get(coordinate);
    }

    public Coordinate getCoordinate(Piece piece) {
        for (Coordinate coordinate : this.pieces.keySet()) {
            if (this.pieces.get(coordinate).equals(piece)) {
                return coordinate;
            }
        }
        return null;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int getColumnSize() {
        return this.columnSize;
    }


    public Map<Coordinate, Piece> getPieces() {
        return this.pieces;
    }

    public Board copy() {
        Map<Coordinate, Piece> newPieces = new HashMap<>(this.pieces);
        return new Board(this.rowSize, this.columnSize, newPieces);
    }


}
