/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:20:32 AM
 */

package net.larry1123.lib.commands;

import net.canarymod.commandsys.CommandDependencyException;
import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.commands.bungeecord.BungeeCordCommand;
import net.larry1123.lib.commands.bungeecord.BungeeCordReloadCommand;
import net.larry1123.lib.commands.bungeecord.BungeeCordSetCommand;
import net.larry1123.lib.logger.EELogger;
import net.larry1123.lib.plugin.UtilPlugin;
import net.larry1123.lib.plugin.commands.Command;

public class UtilCommands {

    public final Command baseCommand;
    public final Command versionCommand;
    public final Command bungeecordCommand;
    public final Command bungeecordReloadCommand;
    public final Command bungeecordSetCommand;

    private final UtilPlugin owner;

    public UtilCommands(UtilPlugin owner) {
        this.owner = owner;
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

    public UtilPlugin getOwner() {
        return owner;
    }

    private void regCommand(Command command) {
        try {
            CanaryUtil.commands().registerCommand(command, getOwner());
            command.setloadded(true);
        } catch (CommandDependencyException e) {
            EELogger.getLogger("CanaryUtil").logCustom("Commands", "Failed to add command: " + command.getCommandData().getAliases()[0], e);
            command.setloadded(false);
        }
    }

}
