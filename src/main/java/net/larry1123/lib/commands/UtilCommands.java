package net.larry1123.lib.commands;

import net.canarymod.commandsys.CommandDependencyException;
import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.logger.EELogger;
import net.larry1123.lib.plugin.UtilPlugin;

public class UtilCommands {

    public final Command baseCommand = new BaseCommand(this);
    // SubCommands
    public final Command versionCommand = new VersionCommand(this);

    private final UtilPlugin owner;

    public UtilCommands(UtilPlugin owner) {
        this.owner = owner;
        regCommand(baseCommand);
        regCommand(versionCommand);
    }

    public UtilPlugin getOwner() {
        return owner;
    }

    private void regCommand(Command command) {
        try {
            CanaryUtil.commands().registerCommand(command.getCommandData(), owner, command.getTranslator(), command, command.isForced());
        } catch (CommandDependencyException e) {
            EELogger.getLogger("CanaryUtil").logCustom("Commands", "Failed to add command: " + command.getCommandData().getAliases()[0], e);
        }
    }

}
