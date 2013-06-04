package net.larry1123.lib.commands;

import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandOwner;
import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.logger.EELogger;

public class UtilCommands {

    public final Command baseCommand = new BaseCommand(this);
    // SubCommands
    public final Command helpCommand = new HelpCommand(this);

    private CommandOwner owner;

    public void registerCommands(CommandOwner owner) {
        this.owner = owner;
        regCommand(baseCommand);
        EELogger.getLogger("CanaryUtil").info("" + Canary.commands().hasCommand("" + baseCommand.getCommandData().getCommandUID()));
        regCommand(helpCommand);
    }

    public void regCommand(Command command) {
        try {
            CanaryUtil.commands().registerCommand(command.getCommandData(), owner, command.getTranslator(), command, command.isForced());
        } catch (CommandDependencyException e) {
            EELogger.getLogger("CanaryUtil").logCustom("Commands", "Failed to add command: " + command.getCommandData().getAliases()[0], e);
        }
    }

}
