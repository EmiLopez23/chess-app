package games.checkers.mover;

import common.*;
import common.enums.GameState;
import common.mover.Mover;
import common.validators.MovementValidator;
import games.checkers.validators.CanKeepEatingValidator;

import java.util.ArrayList;
import java.util.List;

public class KeepEatingMover implements Mover {


    MovementValidator canKeepEating;

    public KeepEatingMover(List<Coordinate> possibleMoves, MovementValidator hasEaten) {
        this.canKeepEating = new CanKeepEatingValidator(possibleMoves, hasEaten);
    }

    @Override
    public MoveResponse move(Game game, Movement move) {
        if (canKeepEating.isValid(game.history(), move)) {
            return new MoveResponse(createNewGame(game, game.history()), null, GameState.KEEP_PLAYING);
        }
        return new MoveResponse(game, null, GameState.KEEP_PLAYING);
    }

    private Game createNewGame(Game game, List<Board> history) {
        return new Game(history, game.mover(), game.players(), new TurnManager(game.getNextPlayer()), game.winValidator());
    }

}
