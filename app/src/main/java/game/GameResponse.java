package game;

public class GameResponse<T, String> {
    private final T game;
    private final String message;

    public GameResponse(T game, String message) {
        this.game = game;
        this.message = message;
    }

    public T getGame() {
        return this.game;
    }

    public Boolean isValid() {
        return this.message == null;
    }

    public String getMessage() {
        return this.message;
    }
}
