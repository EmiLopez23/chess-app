package adapter;

import board.Movement;
import edu.austral.dissis.chess.gui.*;
import factory.GameFactory;
import game.Game;
import org.jetbrains.annotations.NotNull;

public class ChessGameEngine implements GameEngine {
    private Game game;

    private final Adapter adapter = new Adapter();

    private final GameFactory factory = new GameFactory();

    public ChessGameEngine() {
        this.game = factory.createGame();
    }
    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        mover.MoveResult<Game,Boolean> gameResult = game.play(movement);
        if(!gameResult.isValid()){
            return new InvalidMove("Invalid move");
        }
        else {
            this.game = gameResult.getObject();
            return new NewGameState(adapter.piecesToChessPieces(gameResult.getObject().getBoard().getBoard()), adapter.colorToPlayerColor(gameResult.getObject().getTurnManager().getCurrentPlayer().getColor()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(game.getBoard()), adapter.piecesToChessPieces(game.getBoard().getBoard()), PlayerColor.WHITE);
    }
}
