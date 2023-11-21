package games.checkers.factory;

import common.*;
import common.enums.Color;
import common.factory.PieceFactory;
import common.mover.Mover;
import common.mover.PromoterMover;
import common.mover.SequenceMover;
import common.mover.ValidBasicsMover;
import common.validators.MovementValidator;
import games.checkers.mover.CheckersMover;
import common.validators.ZeroEnemyPiecesValidator;
import games.checkers.mover.KeepEatingMover;

import java.util.ArrayList;
import java.util.List;

public class CheckersFactory {
    private static final BoardFactory boardFactory = new BoardFactory();
    private static final PieceFactory pieceFactory = new CheckersPieceFactory();

    public Game createClassicGame() {
        List<Player> playerList = List.of(new Player(Color.WHITE), new Player(Color.BLACK));
        List<Board> boardList = List.of(boardFactory.createClassicBoard());
        Mover checkersMover = new SequenceMover(
                new ValidBasicsMover(),
                new CheckersMover(true),
                new KeepEatingMover(possibleMoves()),
                new PromoterMover(pieceFactory)
        );
        return new Game(boardList, checkersMover, playerList, new TurnManager(playerList.get(0)), new ZeroEnemyPiecesValidator());
    }


    private List<Coordinate> possibleMoves(){
        return List.of(
                new Coordinate(2,2),
                new Coordinate(2,-2),
                new Coordinate(-2,2),
                new Coordinate(-2,-2)
        );
    }
}
