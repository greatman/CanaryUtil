package net.larry1123.lib.plugin.commands;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandOwner;
import net.visualillusionsent.utils.LocaleHelper;

public class Commands {

    public void registerCommand(CommandData data, CommandOwner owner, LocaleHelper translator, final CommandExecute execute, boolean force) throws CommandDependencyException {
        // UtilCommand command = new UtilCommand(data, owner, translator, execute);
        CanaryCommand ccommand = new CanaryCommand(new CommandCommand(data), owner, translator) {

            @Override
            protected void execute(MessageReceiver caller, String[] parameters) {
                execute.execute(caller, parameters);
            }

        };
        Canary.commands().registerCommand(ccommand, owner, force);
    }

}
