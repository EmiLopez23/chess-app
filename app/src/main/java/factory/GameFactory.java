package factory;

import board.Board;
import board.Player;
import board.TurnManager;
import enums.Color;
import game.Game;
import mover.Mover;
import validators.CheckMateValidator;
import validators.CheckValidator;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public Game createGame() {
        List<Player> playerList = new ArrayList<>();
        List<Board> boardList = new ArrayList<>();
        boardList.add(boardFactory.createClassicBoard());
        playerList.add(new Player(Color.WHITE));
        playerList.add(new Player(Color.BLACK));
        Mover mover = new Mover();
        CheckValidator checkValidator = new CheckValidator();
        return new Game(boardList, new ArrayList<>(), mover,playerList, new TurnManager(playerList.get(0)), checkValidator, new CheckMateValidator(checkValidator,mover));
    }
}
