package net.larry1123.util;

import net.canarymod.commandsys.CommandOwner;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.util.api.plugin.UtilPlugin;
import net.larry1123.util.api.plugin.commands.Commands;
import net.larry1123.util.commands.UtilCommands;
import net.larry1123.util.config.UtilConfigManager;
import net.larry1123.util.customPacket.CustomPacket;
import net.larry1123.util.task.FileSpliterUpdater;

public class CanaryUtil extends UtilPlugin implements TaskOwner, CommandOwner {

    private static CanaryUtil plugin;
    private static CustomPacket customPacket;
    private static final Commands commands = new Commands();

    static {
        EEUtils.setLoggerSettings(UtilConfigManager.getConfig().getLoggerConfig());
    }

    private UtilCommands commandsManager;

    /**
     * Warning may return Null if the Util is not enabled yet!!!!
     *
     * @return CustomPacket Manager
     */
    public static CustomPacket getCustomPacket() {
        return customPacket;
    }

    public static CanaryUtil getPlugin() {
        return plugin;
    }

    /**
     * @return Command Manager
     */
    public static Commands commands() {
        return commands;
    }

    public CanaryUtil() {
        plugin = this;
    }

    /**
     * Plugin Disable Method
     */
    @Override
    public void disable() {
        // Stop Tasks!
        ServerTaskManager.removeTasksForPlugin(this);
        // Log that the plugin was Disabled
        getLogger().info("Plugin Disabled");
    }

    /**
     * Plugin Enable Method
     */
    @Override
    public boolean enable() {
        // Start checker for splitting logging files
        FileSpliterUpdater.startUpdater();
        // Make the custom packet manager object
        customPacket = new CustomPacket();
        // Start up the Command manager
        commandsManager = new UtilCommands();
        // Log that the Plugin was Enabled
        getLogger().info("Plugin Enabled");
        // Hey everything worked lets return true so Canary knows that too
        return true;
    }

}
