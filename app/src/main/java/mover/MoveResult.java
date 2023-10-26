package mover;

public class MoveResult<T, Boolean> {
    private final T game;
    private final Boolean isValid;

    public MoveResult(T game, Boolean isValid) {
        this.game = game;
        this.isValid = isValid;
    }

    public T getObject() {
        return this.game;
    }

    public Boolean isValid() {
        return this.isValid;
    }
}
