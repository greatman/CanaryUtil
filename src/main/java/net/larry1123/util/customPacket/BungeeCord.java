package net.larry1123.util.customPacket;

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.util.config.UtilConfigManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class BungeeCord {

    private static final BungeeCordListener lis = new BungeeCordListener();
    private static final RemoteServer all = RemoteServer.getALLServerObject();

    private static final HashMap<OfflinePlayer, String> IPs = new HashMap<OfflinePlayer, String>();
    private static final HashMap<RemoteServer, Integer> serverPlayerCount = new HashMap<RemoteServer, Integer>();
    private static LinkedList<RemoteServer> serverList = new LinkedList<RemoteServer>();
    private static final HashMap<RemoteServer, LinkedList<OfflinePlayer>> playerList = new HashMap<RemoteServer, LinkedList<OfflinePlayer>>();
    private static RemoteServer currentServer = RemoteServer.getServer(UtilConfigManager.getConfig().getBungeeCordConfig().getServerName());

    public BungeeCord() {
        Canary.channels().registerListener(getPlugin(), "BungeeCord", lis);
        Canary.hooks().registerListener(lis, getPlugin());
    }

    public void unregChannelListener() {
        Canary.channels().unregisterListeners(getPlugin());
    }

    /**
     * For use from the Listener only
     *
     * @param player String of the Player's Name
     * @param Ip     Current IP of a Player
     * @param liss   The Object of the Listener
     */
    static void setPlayerIp(Player player, String Ip, BungeeCordListener liss) {
        if (lis == liss) {
            if (Ip != null) {
                IPs.put(Canary.getServer().getOfflinePlayer(player.getName()), Ip);
                player.message(Ip);
            }
        }
    }

    /**
     * Gets the Real IP of a Player
     * This will work with both BungeeCord Enabled or disabled
     * This will also work if the Player is not connected to the BungeeCord Server
     *
     * @param player Player Class
     * @return The IP of the Player
     */
    public String getRealPlayerIp(Player player) {
        if (IPs.get(Canary.getServer().getOfflinePlayer(player.getName())) != null) {
            return IPs.get(Canary.getServer().getOfflinePlayer(player.getName()));
        } else {
            return player.getIP();
        }
    }

    /**
     * For use from the Listener only
     *
     * @param player String of the Player's Name
     * @param liss   The Object of the Listener
     */
    static void removePlayerIp(Player player, BungeeCordListener liss) {
        if (lis == liss) {
            IPs.remove(Canary.getServer().getOfflinePlayer(player.getName()));
        }
    }

    /**
     * For use from the Listener only
     *
     * @param server  Server to update
     * @param players Number of Players
     * @param liss    The Object of the Listener
     */
    static void setPlayerCountForServer(RemoteServer server, int players, BungeeCordListener liss) {
        if (lis == liss) {
            if (currentServer == server) {
                return;
            }
            serverPlayerCount.put(server, players);
        }
    }

    /**
     * Gets the last known amount of players on the given Server
     * Will return -1 if the server is Offline
     *
     * @param server What Server to check
     * @return Player Count for given Server
     */
    public int getServerPlayerCount(RemoteServer server) {
        if (currentServer != server) {
            Integer count = serverPlayerCount.get(server);
            if (count != null) {
                return count;
            } else {
                return -1;
            }
        } else {
            return Canary.getServer().getNumPlayersOnline();
        }
    }

    /**
     * For use from the Listener only
     * This Method handles the server name ALL, when ALL is handled it will remove from all other Server list missing players
     *
     * @param server  Server to update
     * @param players List of Players on Given Server
     * @param liss    The Object of the Listener
     */
    static void setPlayerList(RemoteServer server, LinkedList<String> players, BungeeCordListener liss) {
        if (lis == liss) {
            LinkedList<OfflinePlayer> serverplayers = new LinkedList<OfflinePlayer>();
            for (String playerr : players) {
                serverplayers.add(Canary.getServer().getOfflinePlayer(playerr));
            }
            playerList.put(server, serverplayers);

            if (server == all) {
                // Removes players that for some reason are still there when they should not be
                for (OfflinePlayer player : IPs.keySet()) {
                    if (!playerList.get(all).contains(player)) {
                        IPs.remove(player);
                    }
                }

                // Removes players from the list for an other server if they are not in the list for all
                for (RemoteServer key : playerList.keySet()) {
                    if (key != all) {
                        for (OfflinePlayer player : playerList.get(key)) {
                            if (!playerList.get(all).contains(player)) {
                                playerList.get(key).remove(player);
                            }
                        }
                    }
                }

            } else {

                // Adds players that are known to be on other servers to the all list!
                for (OfflinePlayer player : playerList.get(server)) {
                    if (playerList.get(all) != null) {
                        if (!playerList.get(all).contains(player)) {
                            playerList.get(all).add(player);
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets a OfflinePlayer LinkedList of Players for the given Server or a empty List
     *
     * @param server What Server to check
     * @return LinkedList of OfflinePlayers for the given Server
     */
    public LinkedList<OfflinePlayer> getServerPlayerList(RemoteServer server) {
        LinkedList<OfflinePlayer> ren = new LinkedList<OfflinePlayer>();
        if (playerList.get(server) != null) {
            ren = playerList.get(server);
        }
        return ren;
    }

    /**
     * For use from the Listener only
     *
     * @param servers String LinkedList of Server Names
     * @param liss    The Object of the Listener
     */
    static void setServerList(LinkedList<RemoteServer> servers, BungeeCordListener liss) {
        if (lis == liss) {
            serverList = servers;
            for (RemoteServer server : serverList) {
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
    public LinkedList<RemoteServer> getServerList() {
        return serverList;
    }

    /**
     * For use from the Listener only
     *
     * @param server Server to update
     * @param liss   The Object of the Listener
     */
    static void setCurrentServerName(RemoteServer server, BungeeCordListener liss) {
        if (lis == liss) {
            // Only update if changed!
            if (!currentServer.equals(server)) {
                UtilConfigManager.getConfig().getBungeeCordConfig().setServerName((currentServer = server).getServerName());
            }
        }
    }

    /**
     * Gets the name for this server
     *
     * @return The Name for this Server
     */
    public RemoteServer getCurrentServerName() {
        return currentServer;
    }

    /**
     * This will disconnect the Player from this server and send them to a different server if it is online
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param player Player Object of who to send to a new server
     * @param server What server to send to
     * @return true if the packet was sent, false if the packet was not sent
     */
    public boolean sendPlayertoServer(Player player, RemoteServer server) {
        if (!serverList.contains(server) || currentServer == server || server == all) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            // May add check of if this server is known to be online before trying to send packet
            out.writeUTF(server.getServerName());
        } catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        return Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    /**
     * Will send a CustomPayload Packet to the given server as any connected player
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param server     What server to send to.
     * @param subCnannel What channel to send over
     * @param data       What data to pass
     * @return true if the packet was sent, false if the packet was not sent
     */
    public boolean sendMessageToServer(RemoteServer server, String subCnannel, String data) {
        if (!serverList.contains(server) || currentServer.equals(server)) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            out.writeUTF(server.getServerName());
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
     * Will return true if any packet was sent, false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param server
     * @param subCnannel
     * @param data
     * @return
     */
    public boolean sendMessageToServerAsAllPlayers(RemoteServer server, String subCnannel, String data) {
        if (!serverList.contains(server) || currentServer == server) {
            return false;
        }
        if (Canary.getServer().getNumPlayersOnline() == 0) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server.getServerName());
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
     * Will return true if the packet was sent, false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if the player is not connected to the BungeeCord Server
     *
     * @param server
     * @param subCnannel
     * @param data
     * @param player
     * @return
     */
    public boolean sendMessageToServerAsPlayer(RemoteServer server, String subCnannel, String data, Player player) {
        if (!serverList.contains(server) || currentServer.equals(server)) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server.getServerName());
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
