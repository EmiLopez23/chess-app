package connection.client.listeners;

import connection.client.GameClient;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.NewGameState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

public class NewStateListener implements MessageListener<NewGameState> {

    private final GameClient gameClient;

    public NewStateListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void handleMessage(@NotNull Message<NewGameState> message) {
        gameClient.handleNewGameState(message.getPayload());
    }
}
