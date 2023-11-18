package common.mover;

import common.Coordinate;
import common.Game;
import common.MoveResponse;
import common.Movement;
import common.enums.GameState;

public class SequenceMover implements Mover{
    public Mover[] movers;

    public SequenceMover(Mover... movers) {
        this.movers = movers;
    }

    @Override
    public MoveResponse move(Game game, Movement move) {
        MoveResponse result = new MoveResponse(game, null, GameState.KEEP_PLAYING);
        for (Mover mover: movers){
            result = mover.move(result.game(),move);
            if(!result.isValid()) return result;
        }
        return result;
    }
}
