package games.checkers.factory;

import common.Board;
import common.Game;
import common.Player;
import common.TurnManager;
import common.enums.Color;
import games.checkers.mover.CheckersMover;
import common.validators.ZeroEnemyPiecesValidator;

import java.util.ArrayList;
import java.util.List;

public class CheckersFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public Game createClassicGame() {
        List<Player> playerList = new ArrayList<>();
        List<Board> boardList = new ArrayList<>();
        boardList.add(boardFactory.createClassicBoard());
        playerList.add(new Player(Color.WHITE));
        playerList.add(new Player(Color.BLACK));
        CheckersMover checkersMover = new CheckersMover();
        return new Game(boardList, checkersMover, playerList, new TurnManager(playerList.get(0)), new ZeroEnemyPiecesValidator());
    }
}
