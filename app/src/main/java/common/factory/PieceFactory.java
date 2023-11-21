package common.factory;

import common.Piece;
import common.enums.Color;
import common.enums.PieceType;

public interface PieceFactory {
    Piece createPiece (String id, Color color, PieceType pieceType);
}
