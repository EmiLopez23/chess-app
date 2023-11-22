import common.*;
import common.enums.GameState;
import games.chess.factory.ChessFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChessTest {
    Game game = new ChessFactory().createClassicGame();

    @Test
    public void testPawnDiagonalMovementToEmptySquare() {
        Coordinate pawnCoordinate = new Coordinate(1,2);
        Coordinate destination = new Coordinate(2,3);
        MoveResponse result = game.play(new Movement(pawnCoordinate, destination));
        assertNull(result.game().getBoard().getPiece(destination));
        assertEquals(result.state(), GameState.INVALID_MOVE);
    }

    @Test
    public void testPawnVerticalMovementToEmptySquare() {
        Coordinate pawnCoordinate = new Coordinate(1,2);
        Coordinate destination = new Coordinate(1,3);
        MoveResponse result = game.play(new Movement(pawnCoordinate, destination));
        assertNull(game.getBoard().getPiece(destination));
        assertNull(result.game().getBoard().getPiece(pawnCoordinate));
        assertEquals(result.game().getBoard().getPiece(destination), game.getBoard().getPiece(pawnCoordinate));
        assertEquals(result.state(), GameState.KEEP_PLAYING);
    }

    @Test
    public void testPawnVerticalMovementTwoSquares() {
        Coordinate pawnCoordinate = new Coordinate(1,2);
        Coordinate destination = new Coordinate(1,4);
        MoveResponse result = game.play(new Movement(pawnCoordinate, destination));
        assertNull(game.getBoard().getPiece(destination));
        assertNull(result.game().getBoard().getPiece(pawnCoordinate));
        assertEquals(result.game().getBoard().getPiece(destination), game.getBoard().getPiece(pawnCoordinate));
        assertEquals(result.state(), GameState.KEEP_PLAYING);
    }

    @Test
    public void testTryMovingBlackPieceOnWhiteTurn() {
        Coordinate pawnCoordinate = new Coordinate(1,7);
        Coordinate destination = new Coordinate(1,6);
        MoveResponse result = game.play(new Movement(pawnCoordinate, destination));
        assertNull(result.game().getBoard().getPiece(destination));
        assertEquals(result.message(), "Not your turn");
        assertNull(result.game().getBoard().getPiece(destination));
        assertEquals(result.game().getBoard().getPiece(pawnCoordinate), game.getBoard().getPiece(pawnCoordinate));
        assertEquals(result.state(), GameState.INVALID_MOVE);
    }

    @Test
    public void testCheckMateInFewMoves(){
        Game newGame = new ChessFactory().createClassicGame();
        List<Movement> movements = getCheckMateInFewMoves();
        MoveResponse result = new MoveResponse(game, null, GameState.KEEP_PLAYING);
        for (Movement movement : movements) {
            result = newGame.play(movement);
            newGame = result.game();
        }
        assertEquals(result.state(), GameState.GAME_OVER);
    }

    private List<Movement> getCheckMateInFewMoves(){
        return List.of(
                new Movement(new Coordinate(6,2), new Coordinate(6,3)),
                new Movement(new Coordinate(5,7), new Coordinate(5,6)),
                new Movement(new Coordinate(7,2), new Coordinate(7,4)),
                new Movement(new Coordinate(4,8), new Coordinate(8,4))
        );
    }

}
