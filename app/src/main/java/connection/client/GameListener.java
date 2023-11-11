package connection.client;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.Message;
import org.jetbrains.annotations.NotNull;

public class GameListener implements GameEventListener {
    private final GameClient client;

    public GameListener(GameClient client) {
        this.client = client;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        client.handleMove(move);
    }
}
