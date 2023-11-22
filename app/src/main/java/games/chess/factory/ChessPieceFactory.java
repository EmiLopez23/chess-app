package games.chess.factory;

import common.Coordinate;
import common.Piece;
import common.enums.Color;
import common.enums.PieceType;
import common.factory.PieceFactory;
import common.validators.*;
import common.validators.DiagonalValidator;
import games.chess.validators.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChessPieceFactory implements PieceFactory {

    @Override
    public Piece createPiece(String id, Color color, PieceType pieceType) {
        return switch (pieceType) {
            case KING -> createKing(id,color);
            case PAWN -> (color == Color.BLACK) ? createBlackPawn(id) : createWhitePawn(id);
            case ROOK -> createRook(id, color);
            case KNIGHT -> createKnight(id, color);
            case BISHOP -> createBishop(id, color);
            case QUEEN -> createQueen(id, color);
            case ARCHBISHOP -> createArchBishop(id, color);
            case CHANCELLOR -> createChancellor(id, color);
            default -> throw new IllegalArgumentException("Invalid piece type");
        };
    }
    private Piece createWhitePawn(String id) {

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

    private Piece createBlackPawn(String id) {

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

    private Piece createRook(String id, Color color) {
        MovementValidator validator = rookMovementValidator();
        return new Piece(id, color, PieceType.ROOK, validator);
    }

    private Piece createKnight(String id, Color color) {
        MovementValidator validator = knightMovementValidator();
        return new Piece(id, color, PieceType.KNIGHT, validator);
    }

    private Piece createBishop(String id, Color color) {
        MovementValidator validator = bishopMovementValidator();
        return new Piece(id, color, PieceType.BISHOP, validator);
    }

    private Piece createQueen(String id, Color color) {
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
        return new Piece(id, color, PieceType.QUEEN, validator);
    }

    private Piece createKing(String id, Color color) {
        MovementValidator validator = new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new CompositeOrValidator(
                        new CompositeAndValidator(
                                new LimitedMoveValidator(1),
                                new CompositeOrValidator(
                                        new VerticalValidator(),
                                        new HorizontalValidator(),
                                        new DiagonalValidator()
                                )
                        ),
                        new CompositeAndValidator(
                                new LimitedMoveValidator(2),
                                new FirstMoveValidator(),
                                new AllowEnemyPieceInBetweenValidator(false),
                                new EmptySquareValidator(),
                                new HorizontalValidator()
                        )

                )
        );
        return new Piece(id, color, PieceType.KING, validator);
    }

    private Piece createArchBishop(String id, Color color) {
        MovementValidator validator = new CompositeOrValidator(
                knightMovementValidator(),
                bishopMovementValidator()
        );
        return new Piece(id, color, PieceType.ARCHBISHOP, validator);
    }

    private Piece createChancellor(String id, Color color) {
        MovementValidator validator = new CompositeOrValidator(
                rookMovementValidator(),
                knightMovementValidator()
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

    @NotNull
    private CompositeAndValidator bishopMovementValidator() {
        return new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new DiagonalValidator()
        );
    }

    @NotNull
    private MovementValidator knightMovementValidator() {
        return new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new JumpValidator(createKnightCoordinates())
        );
    }

    @NotNull
    private static CompositeAndValidator rookMovementValidator() {
        return new CompositeAndValidator(
                new OutOfBoundsValidator(),
                new NoSelfEatingValidator(),
                new AllowEnemyPieceInBetweenValidator(false),
                new CompositeOrValidator(
                        new VerticalValidator(),
                        new HorizontalValidator()
                )
        );
    }

}
