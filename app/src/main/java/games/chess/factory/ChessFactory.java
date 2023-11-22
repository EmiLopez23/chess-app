package games.chess.factory;

import common.*;
import common.enums.Color;
import common.factory.PieceFactory;
import common.mover.Mover;
import common.mover.SequenceMover;
import common.mover.ValidBasicsMover;
import common.validators.HasEatenValidator;
import games.chess.mover.CastlingMover;
import games.chess.mover.ChessMover;
import common.mover.PromoterMover;
import games.chess.validators.CheckMateValidator;
import games.chess.validators.CheckValidator;

import java.util.List;

public class ChessFactory {
    private static final BoardFactory boardFactory = new BoardFactory();
    private static final PieceFactory pieceFactory = new ChessPieceFactory();

    public Game createClassicGame() {
        List<Player> playerList = List.of(new Player(Color.WHITE), new Player(Color.BLACK));
        List<Board> boardList = List.of(boardFactory.createClassicBoard());
        Mover mover = new SequenceMover(
                new ValidBasicsMover(),
                new ChessMover(),
                new PromoterMover(pieceFactory),
                new CastlingMover()
        );
        return new Game(boardList, mover, playerList, new TurnManager(playerList.get(0)), new CheckMateValidator(new CheckValidator()));
    }

    public Game createArchGame(){
        List<Player> playerList = List.of(new Player(Color.WHITE), new Player(Color.BLACK));
        List<Board> boardList = List.of(boardFactory.createArchBoard());
        Mover mover = new SequenceMover(new ValidBasicsMover(), new ChessMover(), new PromoterMover(pieceFactory));
        return new Game(boardList, mover, playerList, new TurnManager(playerList.get(0)), new CheckMateValidator(new CheckValidator()));
    }

    public Game createCapaBlancaGame(){
        List<Player> playerList = List.of(new Player(Color.WHITE), new Player(Color.BLACK));
        List<Board> boardList = List.of(boardFactory.createCapaBlancaBoard());
        Mover mover = new SequenceMover(new ValidBasicsMover(), new ChessMover(), new PromoterMover(pieceFactory));
        return new Game(boardList, mover, playerList, new TurnManager(playerList.get(0)), new CheckMateValidator(new CheckValidator()));
    }

    public Game createFirstToEatWins(){
        List<Player> playerList = List.of(new Player(Color.WHITE), new Player(Color.BLACK));
        List<Board> boardList = List.of(boardFactory.createClassicBoard());
        Mover mover = new SequenceMover(new ValidBasicsMover(), new ChessMover(), new PromoterMover(pieceFactory));
        return new Game(boardList, mover, playerList, new TurnManager(playerList.get(0)), new HasEatenValidator());
    }
}
