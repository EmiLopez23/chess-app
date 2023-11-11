package games.checkers.factory;

import common.Board;
import common.Player;
import common.TurnManager;
import common.enums.Color;
import common.Game;
import games.checkers.mover.Mover;
import games.checkers.validators.ZeroEnemyPiecesValidator;

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
        Mover mover = new Mover();
        return new Game(boardList,mover,playerList, new TurnManager(playerList.get(0)), new ZeroEnemyPiecesValidator());
    }
}
