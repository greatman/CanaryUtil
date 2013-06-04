/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:12:20 AM
 */

package net.larry1123.lib;

import net.canarymod.commandsys.CommandOwner;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.lib.commands.UtilCommands;
import net.larry1123.lib.config.UtilConfig;
import net.larry1123.lib.customPacket.BungeeCord;
import net.larry1123.lib.customPacket.BungeeCordless;
import net.larry1123.lib.customPacket.UpdateBungeeInfo;
import net.larry1123.lib.plugin.UtilPlugin;
import net.larry1123.lib.plugin.commands.Commands;

public class CanaryUtil extends UtilPlugin implements TaskOwner, CommandOwner {

    private static BungeeCord coustompacket;
    private static Commands commands = new Commands();
    private static boolean bungeecord_enabled = false;
    private static long bungeecord_pollTime = 100;
    private static String bungeecord_ServerName = "Server";

    private UtilConfig config = UtilConfig.getConfig();

    public UpdateBungeeInfo ticksystem;

    public static BungeeCord coustomPacket() {
        return coustompacket;
    }

    public static Commands commands() {
        return commands;
    }

    public static boolean isBungeeCordEnabled() {
        return bungeecord_enabled;
    }

    public static long getBungeeCordPollTime() {
        return bungeecord_pollTime;
    }

    public static String getBungeeCordServerName() {
        return bungeecord_ServerName;
    }

    protected String version = "0.0.1";

    protected String author = "Larry1123";

    @Override
    public void disable() {
        ServerTaskManager.removeTasksForPlugin(this);
        logger.info("Plugin Disabled");
    }

    @Override
    public boolean enable() {
        bungeecord_enabled = config.getBungeeCordConfig().isEnabled();
        bungeecord_pollTime = config.getBungeeCordConfig().getPollTime();
        bungeecord_ServerName = config.getBungeeCordConfig().getServerName();

        if (CanaryUtil.bungeecord_enabled) {
            coustompacket = new BungeeCord(this);
            ticksystem = new UpdateBungeeInfo(this, bungeecord_pollTime);
            ServerTaskManager.addTask(ticksystem);
        } else {
            coustompacket = new BungeeCordless(this);
        }

        new UtilCommands().registerCommands(this);

        logger.info("Plugin Enabled");
        return true;
    }

}
