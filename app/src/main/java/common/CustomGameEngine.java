package common;

import common.adapter.Adapter;
import common.enums.GameState;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.Move;
import org.jetbrains.annotations.NotNull;

public class CustomGameEngine implements GameEngine {
    private Game game;
    private final Adapter adapter = new Adapter();

    public CustomGameEngine(Game game) {
        this.game = game;
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Movement movement = adapter.moveToMovement(move);
        MoveResponse gameResult = game.play(movement);
        if (!gameResult.isValid()) {
            if (gameResult.state() == GameState.GAME_OVER) {
                return new GameOver(adapter.colorToPlayerColor(gameResult.game().getCurrentPlayer().color()));
            }
            return new InvalidMove(gameResult.message());
        }
        Game result = gameResult.game();
        this.game = result;
        return new NewGameState(adapter.piecesToChessPieces(result.getBoard().getPieces()), adapter.colorToPlayerColor(result.turnManager().currentPlayer().color()));
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(game.getBoard()), adapter.piecesToChessPieces(game.getBoard().getPieces()), PlayerColor.WHITE);
    }
}
