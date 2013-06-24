/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:53 AM
 */

package net.larry1123.lib.customPacket;

import java.util.LinkedList;
import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.lib.config.UtilConfigManager;
import net.larry1123.lib.plugin.UtilPlugin;

public class BungeeCordless extends BungeeCord {

    public BungeeCordless(UtilPlugin utilplugin) {
        super(utilplugin);
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
    public int getServerPlayerCount(String server) {
        // TODO not sure about this one yet
        return Canary.getServer().getNumPlayersOnline();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<OfflinePlayer> getServerPlayerList(String server) {
        return new LinkedList<OfflinePlayer>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<String> getServerList() {
        return new LinkedList<String>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentServerName() {
        return UtilConfigManager.getConfig().getBungeeCordConfig().getServerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendPlayertoServer(Player player, String server) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServer(String server, String subCnannel, String data) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServerAsAllPlayers(String server, String subCnannel, String data) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServerAsPlayer(String server, String subCnannel, String data, Player player) {
        return false;
    }

}
