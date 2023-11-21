package common;

import common.enums.Color;

public record TurnManager(Player currentPlayer) {

    public Player nextPlayer() {
        Color nextColor = currentPlayer.color() == Color.WHITE ? Color.BLACK : Color.WHITE;
        return new Player(nextColor);
    }
}
