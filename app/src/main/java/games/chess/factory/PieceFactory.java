package games.chess.factory;

import common.Coordinate;
import common.Piece;
import common.enums.Color;
import common.enums.PieceType;
import common.validators.*;
import common.validators.DiagonalValidator;
import games.chess.validators.*;

import java.util.List;

public class PieceFactory {

    public Piece createWhitePawn(String id) {

        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new GoForwardInYValidator(true),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new VerticalValidator(),
                                new IsAllowedToEatValidator(false)
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new DiagonalValidator(),
                                new IsAllowedToEatValidator(true)
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new VerticalValidator(),
                                new IsAllowedToEatValidator(false),
                                new FirstMoveValidator()
                        )
                )
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);

    }

    public Piece createBlackPawn(String id) {

        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new GoForwardInYValidator(false),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new VerticalValidator(),
                                new IsAllowedToEatValidator(false)
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new DiagonalValidator(),
                                new IsAllowedToEatValidator(true)
                        ),
                        new CompositeAndValidator(
                                new FirstMoveValidator(),
                                new LimitedMoveValidator(2),
                                new VerticalValidator(),
                                new IsAllowedToEatValidator(false)
                        )
                )
        );
        return new Piece(id, Color.BLACK, PieceType.PAWN, validator);

    }

    public Piece createWhiteRook(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator()
                )
        );
        return new Piece(id, Color.WHITE, PieceType.ROOK, validator);
    }

    public Piece createBlackRook(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator()
                )
        );
        return new Piece(id, Color.BLACK, PieceType.ROOK, validator);
    }

    public Piece createWhiteKnight(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new JumpValidator(createKnightCoordinates())
        );
        return new Piece(id, Color.WHITE, PieceType.KNIGHT, validator);
    }

    public Piece createBlackKnight(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new JumpValidator(createKnightCoordinates())
        );
        return new Piece(id, Color.BLACK, PieceType.KNIGHT, validator);
    }

    public Piece createWhiteBishop(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new DiagonalValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.BISHOP, validator);
    }

    public Piece createBlackBishop(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new DiagonalValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.BISHOP, validator);
    }

    public Piece createWhiteQueen(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator(),
                        new DiagonalValidator()
                )
        );
        return new Piece(id, Color.WHITE, PieceType.QUEEN, validator);
    }

    public Piece createBlackQueen(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator(),
                        new DiagonalValidator()
                )
        );
        return new Piece(id, Color.BLACK, PieceType.QUEEN, validator);
    }

    public Piece createWhiteKing(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new LimitedMoveValidator(1),
                new NoSelfEatingValidator(),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator(),
                        new DiagonalValidator()
                )
        );
        return new Piece(id, Color.WHITE, PieceType.KING, validator);
    }

    public Piece createBlackKing(String id) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new LimitedMoveValidator(1),
                new NoSelfEatingValidator(),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator(),
                        new DiagonalValidator()
                )
        );
        return new Piece(id, Color.BLACK, PieceType.KING, validator);
    }

    public Piece createArchBishop(String id, Color color) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new CompositeOrValidator(
                        new DiagonalValidator(),
                        new JumpValidator(createKnightCoordinates())
                )
        );
        return new Piece(id, color, PieceType.ARCHBISHOP, validator);
    }

    public Piece createChancellor(String id, Color color) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator(),
                        new JumpValidator(createKnightCoordinates())
                )
        );
        return new Piece(id, color, PieceType.CHANCELLOR, validator);
    }

    private List<Coordinate> createKnightCoordinates() {
        return List.of(
                new Coordinate(1, 2),
                new Coordinate(1, -2),
                new Coordinate(-1, 2),
                new Coordinate(-1, -2),
                new Coordinate(2, 1),
                new Coordinate(2, -1),
                new Coordinate(-2, 1),
                new Coordinate(-2, -1)
        );
    }
}
