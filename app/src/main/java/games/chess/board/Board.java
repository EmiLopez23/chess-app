package games.chess.board;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Coordinate, Piece> board;
    private int rowSize;
    private int columnSize;

    public Board(int rowSize, int columnSize){
        this.board = new HashMap<>();
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public Board(int rowSize, int columnSize, Map<Coordinate, Piece> board){
        this.board = board;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public void addPiece(Coordinate coordinate, Piece piece){
        if(this.board.containsKey(coordinate)) {
            this.board.put(coordinate, piece);
        }
    }

    public Piece getPiece(Coordinate coordinate){
        return this.board.get(coordinate);
    }

    public Coordinate getCoordinate(Piece piece){
        for(Coordinate coordinate : this.board.keySet()){
            if(this.board.get(coordinate).equals(piece)){
                return coordinate;
            }
        }
        return null;
    }

    public int getRowSize(){
        return this.rowSize;
    }

    public int getColumnSize(){
        return this.columnSize;
    }


    public Map<Coordinate, Piece> getBoard(){
        return this.board;
    }

    public Board copy() {
        Map<Coordinate, Piece> newPieces = new HashMap<>(this.board);
        return new Board(this.rowSize, this.columnSize, newPieces);
    }


}
