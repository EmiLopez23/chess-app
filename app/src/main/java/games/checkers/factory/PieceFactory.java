package games.checkers.factory;

import common.Piece;
import common.enums.Color;
import common.enums.PieceType;
import common.validators.*;
import common.validators.DiagonalValidator;
import common.validators.EmptySquareValidator;
import common.validators.AllowEnemyPieceInBetweenValidator;

public class PieceFactory {

    public Piece createDarkPiece(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new EmptySquareValidator(),
                new GoForwardInYValidator(false),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new DiagonalValidator()
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new DiagonalValidator(),
                                new AllowEnemyPieceInBetweenValidator(true)
                        )
                )
        );
        return new Piece(id, Color.BLACK, PieceType.PAWN, validator);
    }

    public Piece createLightPiece(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new EmptySquareValidator(),
                new GoForwardInYValidator(true),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new DiagonalValidator()
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new DiagonalValidator(),
                                new AllowEnemyPieceInBetweenValidator(true)
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
                        new GoForwardInYValidator(true),
                        new GoForwardInYValidator(false)
                ),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new DiagonalValidator()
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new AllowEnemyPieceInBetweenValidator(true),
                                new DiagonalValidator()
                        )
                )
        );
        return new Piece(id, color, PieceType.QUEEN, validator);
    }
}
