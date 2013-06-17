/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:19:37 AM
 */

package net.larry1123.lib;

import net.canarymod.commandsys.CommandOwner;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.lib.commands.UtilCommands;
import net.larry1123.lib.config.BungeeCordConfig;
import net.larry1123.lib.config.UtilConfig;
import net.larry1123.lib.customPacket.CoustomPacket;
import net.larry1123.lib.customPacket.UpdateBungeeInfo;
import net.larry1123.lib.plugin.UtilPlugin;
import net.larry1123.lib.plugin.commands.Commands;

public class CanaryUtil extends UtilPlugin implements TaskOwner, CommandOwner {

    private static UtilConfig config = UtilConfig.getConfig();

    private static CoustomPacket coustompacket;
    private static Commands commands = new Commands();

    private static boolean bungeecord_enabled = config.getBungeeCordConfig().isEnabled();

    /**
     * Warning may return Null if the Util is not enabled yet!!!!
     * 
     * @return
     */
    public static CoustomPacket coustomPacket() {
        return coustompacket;
    }

    public static Commands commands() {
        return commands;
    }

    public static BungeeCordConfig getBungeeCordConfig() {
        return config.getBungeeCordConfig();
    }

    @Override
    public void disable() {
        ServerTaskManager.removeTasksForPlugin(this);
        logger.info("Plugin Disabled");
    }

    @Override
    public boolean enable() {
        UpdateBungeeInfo.setPlugin(this);

        coustompacket = new CoustomPacket(this);
        new UtilCommands(this);

        logger.info("Plugin Enabled");
        return true;
    }

}
