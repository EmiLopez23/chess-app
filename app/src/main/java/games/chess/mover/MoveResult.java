package games.chess.mover;

public class MoveResult<Board, String> {
    private final Board board;
    private final String message;

    public MoveResult(Board board, String message) {
        this.board = board;
        this.message = message;
    }

    public Board getBoard() {
        return this.board;
    }

    public Boolean isValid() {
        return this.message == null;
    }

    public String getMessage() {
        return this.message;
    }
}
