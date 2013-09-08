/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:54:14 AM
 */

package net.larry1123.lib;

import net.canarymod.commandsys.CommandOwner;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.lib.commands.UtilCommands;
import net.larry1123.lib.customPacket.CoustomPacket;
import net.larry1123.lib.plugin.UtilPlugin;
import net.larry1123.lib.plugin.commands.Commands;
import net.larry1123.lib.task.FileSpliterUpdater;
import net.larry1123.lib.task.UpdateBungeeInfo;

public class CanaryUtil extends UtilPlugin implements TaskOwner, CommandOwner {

    private static CoustomPacket coustompacket;
    private static final Commands commands = new Commands();

    /**
     * Warning may return Null if the Util is not enabled yet!!!!
     *
     * @return CoustomPacket Manager
     */
    public static CoustomPacket coustomPacket() {
        return coustompacket;
    }

    /**
     * @return Command Manager
     */
    public static Commands commands() {
        return commands;
    }

    /**
     * Plugin Disable Method
     */
    @Override
    public void disable() {
        ServerTaskManager.removeTasksForPlugin(this);
        getLogger().info("Plugin Disabled");
    }

    /**
     * Plugin Enable Method
     */
    @Override
    public boolean enable() {
        UpdateBungeeInfo.setPlugin(this);
        FileSpliterUpdater.setPlugin(this);
        FileSpliterUpdater.startUpdater();
        coustompacket = new CoustomPacket(this);
        new UtilCommands(this);
        getLogger().info("Plugin Enabled");
        return true;
    }

}
