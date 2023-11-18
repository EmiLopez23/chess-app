package common;

import common.enums.GameState;

public record MoveResponse(Game game, String message, GameState state) {

    public Boolean isValid() {
        return state == GameState.KEEP_PLAYING;
    }
}
