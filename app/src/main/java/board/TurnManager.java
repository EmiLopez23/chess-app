package board;

public class TurnManager {
    private Player currentPlayer;

    public TurnManager(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
