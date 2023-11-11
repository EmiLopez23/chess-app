package common;

public class MoveResponse {
    private final Game game;
    private final String message;

    public MoveResponse(Game game, String message) {
        this.game = game;
        this.message = message;
    }

    public Game getGame() {
        return this.game;
    }

    public Boolean isValid() {
        return this.message == null;
    }

    public String getMessage() {
        return this.message;
    }
}
