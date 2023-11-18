package common.promoter;

import common.Board;
import common.Coordinate;
import common.Movement;
import common.Piece;

import java.util.Map;

public interface Promoter {
    Map<Coordinate, Piece> tryPromoting(Board board, Piece currentPiece, Movement movement);
}
