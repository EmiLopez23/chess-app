package common.mover;

import common.*;
import common.enums.GameState;

public class ValidBasicsMover implements Mover{
    @Override
    public MoveResponse move(Game game, Movement move) {
        Piece currentPiece = game.getBoard().getPiece(move.from());
        if (currentPiece == null) {
            return new MoveResponse(game, "No piece in that coordinate", GameState.INVALID_MOVE);
        }
        if (game.getCurrentPlayer().getColor() != currentPiece.getColor()) {
            return new MoveResponse(game, "Not your turn", GameState.INVALID_MOVE);
        }
        if (move.from().equals(move.to())) {
            return new MoveResponse(game, "You can't move to the same place", GameState.INVALID_MOVE);
        }
        return new MoveResponse(game, null, GameState.KEEP_PLAYING);
    }
}
