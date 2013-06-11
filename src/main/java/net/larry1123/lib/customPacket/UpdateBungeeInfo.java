package net.larry1123.lib.customPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.config.UtilConfig;

public class UpdateBungeeInfo extends ServerTask {

    private static UtilConfig config = UtilConfig.getConfig();

    private static UpdateBungeeInfo ticksystem;
    private static TaskOwner plugin;

    public static void setPlugin(CanaryUtil plugin) {
        UpdateBungeeInfo.plugin = plugin;
    }

    public static void startUpdater() {
        if (config.getBungeeCordConfig().isEnabled()) {
            ticksystem = new UpdateBungeeInfo(plugin, config.getBungeeCordConfig().getPollTime());
        }
    }

    public static void endUpdater() {
        if (ticksystem != null) {
            ServerTaskManager.removeTask(ticksystem);
        }
    }

    public static void reloadUpdater() {
        if (config.getBungeeCordConfig().isEnabled()) {
            if (ticksystem != null) {
                ServerTaskManager.removeTask(ticksystem);
            }
            ticksystem = new UpdateBungeeInfo(plugin, config.getBungeeCordConfig().getPollTime());
        }
    }

    private UpdateBungeeInfo(TaskOwner owner, long delay) {
        this(owner, delay, true);
    }

    private UpdateBungeeInfo(TaskOwner owner, long delay, boolean continuous) {
        super(owner, delay, continuous);
    }

    @Override
    public void run() {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        // Update Player IPs
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

        boolean once = false;
        for (Player player : Canary.getServer().getPlayerList()) {
            if (once) {
                break;
            }
            once = true;
            // Update ServerList
            {
                b = new ByteArrayOutputStream();
                out = new DataOutputStream(b);
                try {
                    out.writeUTF("GetServers");
                } catch (IOException e) {
                    // Can't happen man
                }
                Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
            }

            LinkedList<String> servers = (LinkedList<String>) CanaryUtil.coustomPacket().getBungeeCord().getServerList().clone();
            servers.add("ALL");
            for (String server : servers) {
                // Update playerList for each server
                {
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
                // Update Playercount for each server
                {

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
            }

            // Update Current Server's name
            {
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
    }
}

