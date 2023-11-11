package games.checkers.factory;

import common.Board;
import common.Player;
import common.TurnManager;
import common.enums.Color;
import games.checkers.game.CheckersGame;
import games.checkers.mover.Mover;
import games.checkers.validators.ZeroEnemyPiecesValidator;

import java.util.ArrayList;
import java.util.List;

public class ClassicCheckersFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public CheckersGame createGame() {
        List<Player> playerList = new ArrayList<>();
        List<Board> boardList = new ArrayList<>();
        boardList.add(boardFactory.createClassicBoard());
        playerList.add(new Player(Color.WHITE));
        playerList.add(new Player(Color.BLACK));
        Mover mover = new Mover();
        return new CheckersGame(boardList,mover,playerList, new TurnManager(playerList.get(0)), new ZeroEnemyPiecesValidator());
    }
}
