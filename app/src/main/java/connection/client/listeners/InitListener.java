package connection.client.listeners;
import connection.client.GameClient;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InitListener implements MessageListener<InitialState> {
    private final GameClient gameClient;

    public InitListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void handleMessage(@NotNull Message<InitialState> message) {
        gameClient.handleInitialState(message.getPayload());
    }
}
