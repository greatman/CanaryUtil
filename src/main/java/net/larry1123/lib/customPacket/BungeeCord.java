/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:13:09 AM
 */

package net.larry1123.lib.customPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.channels.ChannelListener;
import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.plugin.UtilPlugin;

public class BungeeCord {

    private static ChannelListener lis = new BungeeCordListener();

    private static HashMap<OfflinePlayer, String> IPs = new HashMap<OfflinePlayer, String>();
    private static HashMap<String, Integer> serverPlayerCount = new HashMap<String, Integer>();
    private static LinkedList<String> serverList = new LinkedList<String>();
    private static HashMap<String, LinkedList<OfflinePlayer>> playerList = new HashMap<String, LinkedList<OfflinePlayer>>();
    private static String currentServer = CanaryUtil.getBungeeCordServerName();

    private final UtilPlugin plugin;

    public BungeeCord(UtilPlugin utilplugin) {
        this.plugin = utilplugin;
        Canary.hooks().registerListener(new BungeeCordListener(), plugin);
        Canary.channels().registerListener(plugin, "BungeeCord", lis);
    }

    static void addPlayerIp(String player, String Ip, BungeeCordListener liss) {
        if (lis == liss) {
            if (Ip != null) {
                IPs.put(Canary.getServer().getOfflinePlayer(player), Ip);
            }
        }
    }

    public String getRealPlayerIp(Player player) {
        OfflinePlayer offlineplyer = Canary.getServer().getOfflinePlayer(player.getName());
        if (IPs.containsKey(offlineplyer)) {
            return IPs.get(offlineplyer);
        } else {
            return player.getIP();
        }
    }

    static void removePlayerIp(String player, BungeeCordListener liss) {
        if (lis == liss) {
            IPs.remove(Canary.getServer().getOfflinePlayer(player));
        }
    }

    static void setPlayerCountForServer(String server, int players, BungeeCordListener liss) {
        if (lis == liss) {
            serverPlayerCount.put(server, players);
        }
    }

    public int getServerPlayerCount(String server) {
        Integer count = serverPlayerCount.get(server);
        if (count != null) {
            return count;
        } else {
            return -1;
        }
    }

    static void setPlayerList(String server, LinkedList<String> players, BungeeCordListener liss) {
        if (lis == liss) {
            LinkedList<OfflinePlayer> serverplayers = new LinkedList<OfflinePlayer>();
            for (String playerr : players) {
                serverplayers.add(Canary.getServer().getOfflinePlayer(playerr));
            }
            playerList.put(server, serverplayers);

            if (server.equals("ALL")) {
                // Removes players that for some reason are still there when they should not be
                for (OfflinePlayer player : IPs.keySet()) {
                    if (!playerList.get("ALL").contains(player)) {
                        IPs.remove(player);
                    }
                }

                // Removes players from the list for an other server if they are not in the list for all
                for (String key : playerList.keySet()) {
                    if (!key.equals("ALL")) {
                        for (OfflinePlayer player : playerList.get(key)) {
                            if (!playerList.get("ALL").contains(player)) {
                                playerList.get(key).remove(player);
                            }
                        }
                    }
                }

            } else {

                // Adds players that are known to be on other servers to the all list!
                for (OfflinePlayer player : playerList.get(server)) {
                    if (playerList.get("ALL") != null) {
                        if (!playerList.get("ALL").contains(player)) {
                            playerList.get("ALL").add(player);
                        }
                    }
                }
            }
        }
    }

    public LinkedList<OfflinePlayer> getServerPlayerList(String server) {
        return playerList.get(server);
    }

    static void setServerList(LinkedList<String> servers, BungeeCordListener liss) {
        if (lis == liss) {
            serverList = servers;
            for (String server : serverList) {
                if (!serverPlayerCount.containsKey(server)) {
                    serverPlayerCount.remove(server);
                }
            }
        }
    }

    public LinkedList<String> getServerList() {
        return serverList;
    }

    static void setCurrentServerName(String server, BungeeCordListener liss) {
        if (lis == liss) {
            currentServer = server;
        }
    }

    public String getCurrentServerName() {
        return currentServer;
    }

    public void sendPlayertoServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server);
        } catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    public void sendMessageToServer(String server, String subCnannel, String data) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server);
            out.writeShort(data.length());
            out.writeUTF(data);
        } catch (IOException e) {
            // Can't happen man
        }
        boolean once = false;
        for (Player player: Canary.getServer().getPlayerList()){
            if (!once) {
                once = true;
                Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
            }
        }
    }

    public void sendMessageToServerAsAllPlayers(String server, String subCnannel, String data) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server);
            out.writeShort(data.length());
            out.writeUTF(data);
        } catch (IOException e) {
            // Can't happen man
        }
        for (Player player: Canary.getServer().getPlayerList()){
            Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
        }
    }

    public void sendMessageToServerAsPlayer(String server, String subCnannel, String data, Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server);
            out.writeShort(data.length());
            out.writeUTF(data);
        } catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

}
