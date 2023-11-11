package connection.client.listeners;

import connection.client.GameClient;
import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

public class GameOverListener implements MessageListener<GameOver> {
    private final GameClient gameClient;

    public GameOverListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }


    @Override
    public void handleMessage(@NotNull Message<GameOver> message) {
        gameClient.handleGameOver(message.getPayload());
    }
}
