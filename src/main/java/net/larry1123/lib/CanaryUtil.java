/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:12:20 AM
 */

package net.larry1123.lib;

import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.lib.customPacket.BungeeCord;
import net.larry1123.lib.customPacket.UpdateBungeeInfo;
import net.larry1123.lib.plugin.UtilPlugin;
import net.larry1123.lib.plugin.commands.Commands;

public class CanaryUtil extends UtilPlugin implements TaskOwner {

    static BungeeCord coustompacket;
    static Commands commands = new Commands();

    public UpdateBungeeInfo ticksystem = new UpdateBungeeInfo(this, 100);

    public static BungeeCord coustomPacket() {
        return coustompacket;
    }

    public static Commands commands() {
        return commands;
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
        coustompacket = new BungeeCord(this);
        ServerTaskManager.addTask(ticksystem);
        logger.info("Plugin Enabled");
        return true;
    }

}
