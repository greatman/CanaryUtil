package net.larry1123.lib.plugin.commands;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandOwner;
import net.visualillusionsent.utils.LocaleHelper;

public class Commands {

    public void registerCommand(CommandData data, CommandOwner owner, LocaleHelper translator, final CommandExecute execute, boolean force) throws CommandDependencyException {
        CanaryCommand ccommand = new CanaryCommand(new CommandCommand(data), owner, translator) {

            @Override
            protected void execute(MessageReceiver caller, String[] parameters) {
                execute.execute(caller, parameters);
            }

        };
        Canary.commands().registerCommand(ccommand, owner, force);
    }

    public void registerCommand(Command command, CommandOwner owner) throws CommandDependencyException {
        registerCommand(command.getCommandData(), owner, command.getTranslator(), command, command.isForced());
    }

    public boolean unregisterCommand(CommandData data) {
        if (!data.getParent().equals("")) {
            return Canary.commands().unregisterCommand(data.getParent() + "." + data.getCommandUID());
        } else {
            return Canary.commands().unregisterCommand("" + data.getCommandUID());
        }
    }

}
