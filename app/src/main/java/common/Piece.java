package common;

import common.enums.Color;
import common.enums.PieceType;
import common.validators.MovementValidator;

public class Piece {

    private final String id;
    private final Color color;
    private final PieceType pieceType;

    private final MovementValidator validator;

    public Piece(String id,Color color, PieceType pieceType, MovementValidator validator) {
        this.id = id;
        this.color = color;
        this.pieceType = pieceType;
        this.validator = validator;
    }

    public Color getColor() {
        return this.color;
    }

    public String getId() {
        return this.id;
    }
    public MovementValidator getValidator() {
        return this.validator;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }
}
