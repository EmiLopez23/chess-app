package common;

import common.enums.Color;
import common.enums.PieceType;
import common.validators.MovementValidator;

public record Piece(String id, Color color, PieceType pieceType, MovementValidator validator) {

}
