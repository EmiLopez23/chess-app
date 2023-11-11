package common;

public class GameResponse {
    private final Game game;
    private final String message;

    public GameResponse(Game game, String message) {
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
