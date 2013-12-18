package net.larry1123.util.customPacket;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.channels.ChannelListener;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.DisconnectionHook;
import net.canarymod.plugin.PluginListener;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

public final class BungeeCordListener extends ChannelListener implements PluginListener {

    @HookHandler
    public void onPlayerDisconnect(DisconnectionHook hook) {
        BungeeCord.removePlayerIp(hook.getPlayer(), this);
    }

    @Override
    public void onChannelInput(String channel, Player player, byte[] byteStream) {
        try {
        DataInput input = new DataInputStream(new ByteArrayInputStream(byteStream));
        String subChannel = input.readUTF();

        if (subChannel.startsWith("IP")) {
            BungeeCord.setPlayerIp(player, input.readUTF(), this);
        }
        if (subChannel.startsWith("PlayerCount")) {
            String server = input.readUTF();
            int playerCount = input.readInt();
            BungeeCord.setPlayerCountForServer(server, playerCount, this);
        }
        if (subChannel.startsWith("PlayerList")) {
            String server = input.readUTF();
            LinkedList<String> players = new LinkedList<String>();
            try {
                String rawplayers = input.readUTF();
                for (String playerr : rawplayers.split(",")) {
                    players.add(playerr);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // No one on this server
            }
            BungeeCord.setPlayerList(server, players, this);
        }
        if (subChannel.startsWith("GetServer")) {
            if (subChannel.startsWith("GetServers")) {
                String rawservers = input.readUTF();
                LinkedList<String> servers = new LinkedList<String>();
                for (String server : rawservers.split(",")) {
                    servers.add(server);
                }
                BungeeCord.setServerList(servers, this);
            } else {
                String server = input.readUTF();
                BungeeCord.setCurrentServerName(server, this);
            }
        }
        } catch (IOException error) {
            // No clue what should be done if this happens.
        }
    }

}
