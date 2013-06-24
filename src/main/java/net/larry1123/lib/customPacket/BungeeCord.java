/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:43 AM
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
import net.larry1123.lib.config.UtilConfigManager;
import net.larry1123.lib.plugin.UtilPlugin;

public class BungeeCord {

    private static ChannelListener lis = new BungeeCordListener();

    private static HashMap<OfflinePlayer, String> IPs = new HashMap<OfflinePlayer, String>();
    private static HashMap<String, Integer> serverPlayerCount = new HashMap<String, Integer>();
    private static LinkedList<String> serverList = new LinkedList<String>();
    private static HashMap<String, LinkedList<OfflinePlayer>> playerList = new HashMap<String, LinkedList<OfflinePlayer>>();
    private static String currentServer = UtilConfigManager.getConfig().getBungeeCordConfig().getServerName();

    private final UtilPlugin plugin;

    public BungeeCord(UtilPlugin utilplugin) {
        this.plugin = utilplugin;
        Canary.channels().registerListener(plugin, "BungeeCord", lis);
    }

    public void unregChannelListener() {
        Canary.channels().unregisterListeners(plugin);
    }

    /**
     * For use from the Channel Listener only
     * 
     * @param player
     *            String of the Player's Name
     * @param Ip
     *            Current IP of a Player
     * @param liss
     *            The Object of the Listener
     */
    static void addPlayerIp(String player, String Ip, BungeeCordListener liss) {
        if (lis == liss) {
            if (Ip != null) {
                IPs.put(Canary.getServer().getOfflinePlayer(player), Ip);
            }
        }
    }

    /**
     * Gets the Real IP of a Player
     * This will work with both BungeeCord Enabled or disabled
     * This will also work if the Player is not connected to the BungeeCord Server
     * 
     * @param player
     *            Player Class
     * @return The IP of the Player
     */
    public String getRealPlayerIp(Player player) {
        OfflinePlayer offlineplyer = Canary.getServer().getOfflinePlayer(player.getName());
        if (IPs.containsKey(offlineplyer)) {
            return IPs.get(offlineplyer);
        } else {
            return player.getIP();
        }
    }

    /**
     * For use from the Channel Listener only
     * 
     * @param player
     *            String of the Player's Name
     * @param liss
     *            The Object of the Listener
     */
    static void removePlayerIp(String player, BungeeCordListener liss) {
        if (lis == liss) {
            IPs.remove(Canary.getServer().getOfflinePlayer(player));
        }
    }

    /**
     * For use from the Channel Listener only
     * 
     * @param server
     *            Server to update
     * @param players
     *            Number of Players
     * @param liss
     *            The Object of the Listener
     */
    static void setPlayerCountForServer(String server, int players, BungeeCordListener liss) {
        if (lis == liss) {
            serverPlayerCount.put(server, players);
        }
    }

    /**
     * Gets the last known amount of players on the given Server
     * Will return -1 if the server is Offline
     * 
     * @param server
     *            What Server to check
     * @return Player Count for given Server
     */
    public int getServerPlayerCount(String server) {
        Integer count = serverPlayerCount.get(server);
        if (count != null) {
            return count;
        } else {
            return -1;
        }
    }

    /**
     * For use from the Channel Listener only
     * This Method handles the server name ALL, when ALL is handled it will remove from all other Server list missing players
     * 
     * @param server
     *            Server to update
     * @param players
     *            List of Players on Given Server
     * @param liss
     *            The Object of the Listener
     */
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

    /**
     * Gets a OfflinePlayer LinkedList of Players for the given Server or a empty List
     * 
     * @param server
     *            What Server to check
     * @return LinkedList of OfflinePlayers for the given Server
     */
    public LinkedList<OfflinePlayer> getServerPlayerList(String server) {
        LinkedList<OfflinePlayer> ren = new LinkedList<OfflinePlayer>();
        if (playerList.get(server) != null) {
            ren = playerList.get(server);
        }
        return ren;
    }

    /**
     * For use from the Channel Listener only
     * 
     * @param servers
     *            String LinkedList of Server Names
     * @param liss
     *            The Object of the Listener
     */
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

    /**
     * Gets a LinkedList<String> of the last known list of currently Online Servers
     * 
     * @return List of Online Servers
     */
    public LinkedList<String> getServerList() {
        return serverList;
    }

    /**
     * For use from the Channel Listener only
     * 
     * @param server
     *            Server to update
     * @param liss
     *            The Object of the Listener
     */
    static void setCurrentServerName(String server, BungeeCordListener liss) {
        if (lis == liss) {
            // Only update if changed!
            if (!currentServer.equals(server)) {
                UtilConfigManager.getConfig().getBungeeCordConfig().setServerName(currentServer = server);
            }
        }
    }

    /**
     * Gets the name for this server
     * 
     * @return The Name for this Server
     */
    public String getCurrentServerName() {
        return currentServer;
    }

    /**
     * This will disconnect the Player from this server and send them to a different server if it is online
     * 
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * 
     * Will also return false if you are trying to send to the current server;
     * 
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     * 
     * @param player Player Object of who to send to a new server
     * @param server What server to send to
     * @return true if the packet was sent, false if the packet was not sent
     */
    public boolean sendPlayertoServer(Player player, String server) {
        if (!serverList.contains(server) || currentServer.equals(server)) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server);
        } catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
        return true;
    }

    /**
     * Will send a CustomPayload Packet to the given server as any connected player
     * 
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * 
     * Will also return false if you are trying to send to the current server;
     * 
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     * 
     * @param server What server to send to.
     * @param subCnannel What channel to send over
     * @param data What data to pass
     * @return true if the packet was sent, false if the packet was not sent
     */
    public boolean sendMessageToServer(String server, String subCnannel, String data) {
        if (!serverList.contains(server) || currentServer.equals(server)) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            out.writeUTF(server);
            out.writeShort(data.length());
            out.writeUTF(data);
        } catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        boolean once = false;
        for (Player player : Canary.getServer().getPlayerList()) {
            if (!once) {
                once = Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
            }
        }
        return once;
    }

    /**
     * Will send a CustomPayload Packet to the given server as All Connected Players
     * 
     * Will return true if any packet was sent, false if we know that the server is not online at this time.
     * 
     * Will also return false if you are trying to send to the current server;
     * 
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     * 
     * @param server
     * @param subCnannel
     * @param data
     * @return
     */
    public boolean sendMessageToServerAsAllPlayers(String server, String subCnannel, String data) {
        if (!serverList.contains(server) || currentServer.equals(server)) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server);
            out.writeShort(data.length());
            out.writeUTF(data);
        } catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        boolean ret = false;
        for (Player player : Canary.getServer().getPlayerList()) {
            if (Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player)) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * 
     * Will return true if the packet was sent, false if we know that the server is not online at this time.
     * 
     * Will also return false if you are trying to send to the current server;
     * 
     * Will return false if the player is not connected to the BungeeCord Server
     * 
     * @param server
     * @param subCnannel
     * @param data
     * @param player
     * @return
     */
    public boolean sendMessageToServerAsPlayer(String server, String subCnannel, String data, Player player) {
        if (!serverList.contains(server) || currentServer.equals(server)) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server);
            out.writeShort(data.length());
            out.writeUTF(data);
        } catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        return Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

}
