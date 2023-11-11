package games.checkers.factory;

import common.Piece;
import common.enums.Color;
import common.enums.PieceType;
import common.validators.*;
import games.checkers.validators.DiagonalValidator;
import games.checkers.validators.EmptySquareValidator;
import games.checkers.validators.PieceInBetweenValidator;

public class PieceFactory {

    public Piece createDarkPiece(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new EmptySquareValidator(),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new DiagonalValidator(false)
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new DiagonalValidator(false),
                                new PieceInBetweenValidator()
                        )
                )
        );
        return new Piece(id, Color.BLACK, PieceType.PAWN, validator);
    }

    public Piece createLightPiece(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new EmptySquareValidator(),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new DiagonalValidator(true)
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new DiagonalValidator(true),
                                new PieceInBetweenValidator()
                        )
                )
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);
    }

    public Piece createQueen(String id, Color color) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new EmptySquareValidator(),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new CompositeOrValidator(
                                        new DiagonalValidator(true),
                                        new DiagonalValidator(false)
                                )
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new PieceInBetweenValidator(),
                                new CompositeOrValidator(
                                        new DiagonalValidator(true),
                                        new DiagonalValidator(false)
                                )
                        )
                )
        );
        return new Piece(id, color, PieceType.QUEEN, validator);
    }
}
