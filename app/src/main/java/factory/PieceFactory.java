package factory;

import board.Piece;
import enums.Color;
import enums.PieceType;
import validators.*;

public class PieceFactory {
    public Piece createWhitePawn(String id){

        MovementValidator validator = new CompositeOrValidator(
                new VerticalValidator(true),
                new DiagonalValidator(true)
//                new CompositeAndValidator(
//                        new VerticalValidator(true)
                        //new NotEatingValidator(),
                        //new LimitedMoveValidator(1)
//                ),
//                new CompositeAndValidator(
//                        new DiagonalValidator(true)
//                        new EatingValidator(),
//                        new LimitedMoveValidator(1)
//                ),
//                new CompositeAndValidator(
//                        new VerticalValidator(true)
//                        new NotEatingValidator(),
//                        new LimitedMoveValidator(2),
//                        new PieceFirstMoveValidator()
//                )
        );
        return new Piece(id, Color.WHITE, PieceType.PAWN, validator);

    }

    public Piece createBlackPawn(String id){

        MovementValidator validator = new CompositeOrValidator(
                new CompositeAndValidator(
                        new VerticalValidator(false)
//                        new NotEatingValidator(),
//                        new LimitedMoveValidator(1)
                ),
                new CompositeAndValidator(
                        new DiagonalValidator(false)
//                        new EatingValidator(),
//                        new LimitedMoveValidator(1)
                ),
                new CompositeAndValidator(
                        new VerticalValidator(false)
//                        new NotEatingValidator(),
//                        new LimitedMoveValidator(2),
//                        new PieceFirstMoveValidator()
                )
        );
        return new Piece(id, Color.BLACK, PieceType.PAWN, validator);

    }

    public Piece createWhiteRook(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalValidator(true),
                        new VerticalValidator(false),
                        new HorizontalValidator(true),
                        new HorizontalValidator(false)
                )
//                , new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.ROOK, validator);
    }

    public Piece createBlackRook(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalValidator(true),
                        new VerticalValidator(false),
                        new HorizontalValidator(true),
                        new HorizontalValidator(false)
                )
//                , new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.ROOK, validator);
    }

    public Piece createWhiteKnight(String id){
        MovementValidator validator = new CompositeAndValidator(
                new LValidator()
//                ,
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KNIGHT, validator);
    }

    public Piece createBlackKnight(String id){
        MovementValidator validator = new CompositeAndValidator(
                new LValidator()
//                ,
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KNIGHT, validator);
    }

    public Piece createWhiteBishop(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalValidator(true),
                        new DiagonalValidator(false)
                )
//                ,
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.BISHOP, validator);
    }

    public Piece createBlackBishop(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new DiagonalValidator(true),
                        new DiagonalValidator(false)
                )
//                ,
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.BISHOP, validator);
    }

    public Piece createWhiteQueen(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalValidator(true),
                        new VerticalValidator(false),
                        new HorizontalValidator(true),
                        new HorizontalValidator(false),
                        new DiagonalValidator(true),
                        new DiagonalValidator(false)
                )
//                ,
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.QUEEN, validator);
    }

    public Piece createBlackQueen(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalValidator(true),
                        new VerticalValidator(false),
                        new HorizontalValidator(true),
                        new HorizontalValidator(false),
                        new DiagonalValidator(true),
                        new DiagonalValidator(false)
                )
//                ,
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.QUEEN, validator);
    }

    public Piece createWhiteKing(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalValidator(true),
                        new VerticalValidator(false),
                        new HorizontalValidator(true),
                        new HorizontalValidator(false),
                        new DiagonalValidator(true),
                        new DiagonalValidator(false)
                )
//                ,
//                new LimitedMoveValidator(1),
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.WHITE, PieceType.KING, validator);
    }

    public Piece createBlackKing(String id){
        MovementValidator validator = new CompositeAndValidator(
                new CompositeOrValidator(
                        new VerticalValidator(true),
                        new VerticalValidator(false),
                        new HorizontalValidator(true),
                        new HorizontalValidator(false),
                        new DiagonalValidator(true),
                        new DiagonalValidator(false)
                )
//                ,
//                new LimitedMoveValidator(1),
//                new NoFriendlyFireValidator()
        );
        return new Piece(id, Color.BLACK, PieceType.KING, validator);
    }
}
