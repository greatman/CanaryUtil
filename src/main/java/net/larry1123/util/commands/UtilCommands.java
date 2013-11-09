package net.larry1123.util.commands;

import net.canarymod.commandsys.CommandDependencyException;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.commands.bungeecord.BungeeCordCommand;
import net.larry1123.util.commands.bungeecord.BungeeCordReloadCommand;
import net.larry1123.util.commands.bungeecord.BungeeCordSetCommand;
import net.larry1123.util.logger.EELogger;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class UtilCommands {

    public final Command baseCommand;
    public final Command versionCommand;
    public final Command bungeecordCommand;
    public final Command bungeecordReloadCommand;
    public final Command bungeecordSetCommand;

    public UtilCommands() {
        {
            baseCommand = new BaseCommand(this);
            { // SubCommands BaseCommand
                versionCommand = new VersionCommand(this);
                bungeecordCommand = new BungeeCordCommand(this);
                { // SubCommands of BungeeCordCommand
                    bungeecordReloadCommand = new BungeeCordReloadCommand(this);
                    bungeecordSetCommand = new BungeeCordSetCommand(this);
                }
            }
        }
        regCommand(baseCommand);
        regCommand(versionCommand);
        regCommand(bungeecordCommand);
        regCommand(bungeecordReloadCommand);
        regCommand(bungeecordSetCommand);
    }

    private void regCommand(Command command) {
        try {
            CanaryUtil.commands().registerCommand(command, getPlugin());
            command.setloadded(true);
        } catch (CommandDependencyException e) {
            EELogger.getLogger("CanaryUtil").logCustom("Commands", "Failed to add command: " + command.getCommandData().getAliases()[0], e);
            command.setloadded(false);
        }
    }

}
