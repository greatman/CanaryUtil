/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:23:19 AM
 */

package net.larry1123.lib.customPacket;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.channels.ChannelListener;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.DisconnectionHook;
import net.canarymod.plugin.PluginListener;

import java.util.LinkedList;

public final class BungeeCordListener extends ChannelListener implements PluginListener {

    @HookHandler
    public void onPlayerDisconnect(DisconnectionHook hook) {
        BungeeCord.removePlayerIp(hook.getPlayer(), this);
    }

    @Override
    public void onChannelInput(String channel, Player player, byte[] byteStream) {
        String[] data = new String(byteStream).split("\u0000");
        String subChannel = data[1].substring(1);

        if (subChannel.startsWith("IP")) {
            BungeeCord.setPlayerIp(player, data[2].substring(1), this);
        }
        if (subChannel.startsWith("PlayerCount")) {
            String tempcount = "";
            String server = data[2].substring(1);
            int playercount;
            try {
                for (char car : data[5].toCharArray()) {
                    tempcount = tempcount + (int) car;
                }
                playercount = Integer.parseInt(tempcount);
            } catch (ArrayIndexOutOfBoundsException e) {
                // There is no one on the server so it is 0
                playercount = 0;
            }
            BungeeCord.setPlayerCountForServer(server, playercount, this);
        }
        if (subChannel.startsWith("PlayerList")) {
            String server = data[2].substring(1);
            LinkedList<String> players = new LinkedList<String>();
            try {
                String rawplayers = data[3];
                for (String playerr : rawplayers.split(",")) {
                    players.add(playerr.substring(1));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // No one on this server
            }
            BungeeCord.setPlayerList(server, players, this);
        }
        if (subChannel.startsWith("GetServer")) {
            if (subChannel.startsWith("GetServers")) {
                String rawservers = data[2];
                LinkedList<String> servers = new LinkedList<String>();
                for (String server : rawservers.split(",")) {
                    servers.add(server.substring(1));
                }
                BungeeCord.setServerList(servers, this);
            } else {
                String server = data[2].substring(1);
                BungeeCord.setCurrentServerName(server, this);
            }
        }
    }

}
