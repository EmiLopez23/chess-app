package common;

import common.enums.Color;

public class TurnManager {
    private Player currentPlayer;

    public TurnManager(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Player nextPlayer(){
        Color nextColor = currentPlayer.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;
        return new Player(nextColor);
    }
}
