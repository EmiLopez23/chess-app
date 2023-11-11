package games.chess.factory;

import common.*;
import common.enums.Color;
import games.chess.mover.Mover;
import games.chess.validators.CheckMateValidator;
import games.chess.validators.CheckValidator;

import java.util.List;

public class ChessFactory {
    private static final BoardFactory boardFactory = new BoardFactory();

    public Game createClassicGame() {
        List<Player> playerList = List.of(new Player(Color.WHITE), new Player(Color.BLACK));
        List<Board> boardList = List.of(boardFactory.createClassicBoard());
        SimpleMover mover = new Mover();
        return new Game(boardList,mover,playerList, new TurnManager(playerList.get(0)), new CheckMateValidator(new CheckValidator()));
    }
}
