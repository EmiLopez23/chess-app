package games.chess.factory;

import common.Board;
import common.Player;
import common.TurnManager;
import games.chess.enums.Color;
import games.chess.game.Game;
import games.chess.mover.Mover;
import games.chess.validators.CheckMateValidator;
import games.chess.validators.CheckValidator;

import java.util.ArrayList;
import java.util.List;

public class ChessGameFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public Game createGame() {
        List<Player> playerList = new ArrayList<>();
        List<Board> boardList = new ArrayList<>();
        boardList.add(boardFactory.createClassicBoard());
        playerList.add(new Player(Color.WHITE));
        playerList.add(new Player(Color.BLACK));
        Mover mover = new Mover();
        CheckValidator checkValidator = new CheckValidator();
        return new Game(boardList,mover,playerList, new TurnManager(playerList.get(0)), checkValidator, new CheckMateValidator(checkValidator,mover));
    }
}
