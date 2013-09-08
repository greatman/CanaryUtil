/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:59:11 AM
 */

package net.larry1123.lib.task;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.config.UtilConfigManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class UpdateBungeeInfo extends ServerTask {

    /**
     * ConfigManager
     */
    private static final UtilConfigManager config = UtilConfigManager.getConfig();
    /**
     * Current Updater
     */
    private static UpdateBungeeInfo ticksystem;
    /**
     * The plugin that will own the updater
     */
    private static TaskOwner plugin;

    /**
     * This is to be only used for internal uses
     *
     * @param plugin
     */
    public static void setPlugin(CanaryUtil plugin) {
        UpdateBungeeInfo.plugin = plugin;
    }

    /**
     * Starts the updater polling if the config will allow
     */
    public static void startUpdater() {
        if (config.getBungeeCordConfig().isEnabled()) {
            if (ticksystem == null) {
                ticksystem = new UpdateBungeeInfo(plugin, config.getBungeeCordConfig().getPollTime());
                ServerTaskManager.addTask(ticksystem);
            }
        }
    }

    /**
     * Stops the updater polling
     */
    public static void endUpdater() {
        if (ticksystem != null) {
            ServerTaskManager.removeTask(ticksystem);
            ticksystem = null;
        }
    }

    /**
     * Will start the updater if the config allows or stops the updater if running and needed to be
     */
    public static void reloadUpdater() {
        if (config.getBungeeCordConfig().isEnabled()) {
            if (ticksystem != null) {
                ServerTaskManager.removeTask(ticksystem);
            }
            startUpdater();
        } else {
            if (ticksystem != null) {
                ServerTaskManager.removeTask(ticksystem);
                ticksystem = null;
            }
        }
    }

    private UpdateBungeeInfo(TaskOwner owner, long delay) {
        this(owner, delay, true);
    }

    private UpdateBungeeInfo(TaskOwner owner, long delay, boolean continuous) {
        super(owner, delay, continuous);
    }

    /**
     * Sends Polls for BungeeCord Info over players
     */
    @Override
    public void run() {

        // Update Player IPs
        updateIPs();

        for (Player player : Canary.getServer().getPlayerList()) {

            // Update Server List
            updateServerList(player);

            @SuppressWarnings ("unchecked")
            LinkedList<String> servers = (LinkedList<String>) CanaryUtil.coustomPacket().getBungeeCord().getServerList().clone();
            servers.add("ALL");
            for (String server : servers) {
                // Update playerList for each server
                updatePlayerList(player, server);

                // Update Playercount for each server
                updatePlayerCount(player, server);

            }

            // Update Current Server's name
            updateCurrentServer(player);

        }
    }

    private void updateIPs() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        for (Player player : Canary.getServer().getPlayerList()) {
            b = new ByteArrayOutputStream();
            out = new DataOutputStream(b);
            try {
                out.writeUTF("IP");
            } catch (IOException e) {
                // Can't happen man
            }
            Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
        }
    }

    private void updateServerList(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("GetServers");
        } catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    private void updatePlayerList(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("PlayerList");
            out.writeUTF(server);
        } catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    private void updatePlayerCount(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("PlayerCount");
            out.writeUTF(server);
        } catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    private void updateCurrentServer(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("GetServer");
        } catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

}
