package net.larry1123.lib.customPacket;

import java.util.LinkedList;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.lib.config.UtilConfig;
import net.larry1123.lib.plugin.UtilPlugin;

public class BungeeCordless extends BungeeCord {

    public BungeeCordless(UtilPlugin utilplugin) {
        super(utilplugin);
    }

    @Override
    public String getRealPlayerIp(Player player) {
        return player.getIP();
    }

    @Override
    public int getServerPlayerCount(String server) {
        // TODO not sure about this one yet
        return Canary.getServer().getNumPlayersOnline();
    }

    @Override
    public LinkedList<OfflinePlayer> getServerPlayerList(String server) {
        return new LinkedList<OfflinePlayer>();
    }

    @Override
    public LinkedList<String> getServerList() {
        return new LinkedList<String>();
    }

    @Override
    public String getCurrentServerName() {
        return UtilConfig.getConfig().getBungeeCordConfig().getServerName();
    }

    @Override
    public void sendPlayertoServer(Player player, String server) {}

    @Override
    public void sendMessageToServer(String server, String subCnannel, String data) {}

    @Override
    public void sendMessageToServerAsAllPlayers(String server, String subCnannel, String data) {}

    @Override
    public void sendMessageToServerAsPlayer(String server, String subCnannel, String data, Player player) {}

}
