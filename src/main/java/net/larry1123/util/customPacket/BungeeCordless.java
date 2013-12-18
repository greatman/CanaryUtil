package net.larry1123.util.customPacket;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.util.config.UtilConfigManager;

import java.util.LinkedList;

public class BungeeCordless extends BungeeCord {

    public BungeeCordless() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRealPlayerIp(Player player) {
        return player.getIP();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getServerPlayerCount(RemoteServer server) {
        // TODO not sure about this one yet
        return Canary.getServer().getNumPlayersOnline();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<OfflinePlayer> getServerPlayerList(RemoteServer server) {
        return new LinkedList<OfflinePlayer>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<RemoteServer> getServerList() {
        return new LinkedList<RemoteServer>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RemoteServer getCurrentServerName() {
        return RemoteServer.getServer(UtilConfigManager.getConfig().getBungeeCordConfig().getServerName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendPlayertoServer(Player player, RemoteServer server) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServer(RemoteServer server, String subCnannel, String data) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServerAsAllPlayers(RemoteServer server, String subCnannel, String data) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServerAsPlayer(RemoteServer server, String subCnannel, String data, Player player) {
        return false;
    }

}
