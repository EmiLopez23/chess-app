package common;

import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.MoveResult;
import common.adapter.Adapter;
import games.chess.game.Game;
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
        GameResponse<Game,String> gameResult = game.play(movement);
        if(!gameResult.isValid()){
            if(gameResult.getMessage().equals("Game Over")){
                return new GameOver(adapter.colorToPlayerColor(gameResult.getGame().getNextPlayer().getColor()));
            }
            return new InvalidMove(gameResult.getMessage());
        }
        else {
            Game result = gameResult.getGame();
            this.game = result;
            return new NewGameState(adapter.piecesToChessPieces(result.getBoard().getBoard()), adapter.colorToPlayerColor(result.getTurnManager().getCurrentPlayer().getColor()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(adapter.boardToBoardSize(game.getBoard()), adapter.piecesToChessPieces(game.getBoard().getBoard()), PlayerColor.WHITE);
    }
}
