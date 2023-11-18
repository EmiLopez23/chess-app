package common.mover;

import common.Coordinate;
import common.Game;
import common.MoveResponse;
import common.Movement;

public interface Mover {
    public MoveResponse move(Game game, Movement move);
}
