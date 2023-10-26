package adapter;

import board.Board;
import board.Coordinate;
import board.Movement;
import board.Piece;
import edu.austral.dissis.chess.gui.*;
import enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Adapter {
    public Position coordinateToPosition(Coordinate coordinate) {
        return new Position(coordinate.row(), coordinate.column());
    }

    public Move movementToMove(Movement movement){
        return new Move(coordinateToPosition(movement.getFrom()), coordinateToPosition(movement.getTo()));
    }

    public BoardSize boardToBoardSize(Board board) {
        return new BoardSize(board.getColumnSize(), board.getRowSize());
    }

    public PlayerColor colorToPlayerColor(Color color){
        if (color == Color.WHITE) return PlayerColor.WHITE;
        else return PlayerColor.BLACK;
    }

    public List<ChessPiece> piecesToChessPieces(Map<Coordinate, Piece> pieces){
        List<ChessPiece> chessPieces = new ArrayList<>();
        for (Map.Entry<Coordinate, Piece> entry : pieces.entrySet()) {
            Coordinate coordinate = entry.getKey();
            Piece piece = entry.getValue();
            chessPieces.add(new ChessPiece(piece.getId(), colorToPlayerColor(piece.getColor()), coordinateToPosition(coordinate), piece.getPieceType().toString().toLowerCase()));
        }
        return chessPieces;
    }

    public Movement moveToMovement(Move move){
        return new Movement(positionToCoordinate(move.getFrom()), positionToCoordinate(move.getTo()));
    }

    public Coordinate positionToCoordinate(Position position){
        return new Coordinate(position.getColumn(), position.getRow());
    }
}
