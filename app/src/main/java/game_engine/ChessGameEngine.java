package game_engine;

import games.chess.board.Movement;
import edu.austral.dissis.chess.gui.*;
import games.chess.factory.GameFactory;
import games.chess.game.Game;
import games.chess.game.GameResponse;
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
        GameResponse<Game,String> gameResult = game.play(movement);
        if(!gameResult.isValid()){
            return new InvalidMove(gameResult.getMessage());
        }
        else {
            Game result = gameResult.getGame();
            if(!result.getCheckValidator().isValid(result.getBoards(), movement.getFrom(), movement.getTo())){
                return new InvalidMove( "Check");
            }
            if(result.getWinValidator().isValid(result.getBoards(), movement.getFrom(), movement.getTo())){
                return new GameOver(adapter.colorToPlayerColor(result.getTurnManager().nextPlayer().getColor()));
            }
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
