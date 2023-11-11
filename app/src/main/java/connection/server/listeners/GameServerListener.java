package connection.server.listeners;

import connection.server.GameServer;
import edu.austral.dissis.chess.gui.GameEngine;
import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.ServerConnectionListener;
import org.jetbrains.annotations.NotNull;

public class GameServerListener implements ServerConnectionListener {

    GameServer gameServer;

    public GameServerListener(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    @Override
    public void handleClientConnection(@NotNull String s) {
        gameServer.handleNewGame();
    }

    @Override
    public void handleClientConnectionClosed(@NotNull String s) {

    }
}
