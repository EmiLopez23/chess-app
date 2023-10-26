package factory;

import board.Player;
import board.TurnManager;
import enums.Color;
import game.Game;
import mover.Mover;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public Game createGame() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(Color.WHITE));
        playerList.add(new Player(Color.BLACK));
        return new Game(boardFactory.createClassicBoard(), new ArrayList<>(), new Mover(),playerList, new TurnManager(playerList.get(0)));
    }
}
