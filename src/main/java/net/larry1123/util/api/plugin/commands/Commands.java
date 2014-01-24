package net.larry1123.util.api.plugin.commands;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandOwner;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.List;

public class Commands {

    /**
     * Will Register a Command with different parts
     *
     * @param data       Replacement for @Command
     * @param owner      Command owner
     * @param translator localehelper for translating meta info
     * @param execute    Class holding method to execute Command when called
     * @param force      If Command will override an other command
     * @throws CommandDependencyException
     */
    public void registerCommand(CommandData data, CommandOwner owner, LocaleHelper translator, final CommandExecute execute, boolean force) throws CommandDependencyException {
        CanaryCommand ccommand = new CanaryCommand(new CommandCommand(data), owner, translator) {

            @Override
            protected void execute(MessageReceiver caller, String[] parameters) {
                execute.execute(caller, parameters);
            }

            @Override
            protected List<String> tabComplete(MessageReceiver messageReceiver, String[] args) {
                return execute.tabComplete(messageReceiver, args);
            }

        };
        Canary.commands().registerCommand(ccommand, owner, force);
    }

    /**
     * Will Register a Command that has all given data in one Command Class
     *
     * @param command Command pack
     * @param owner   Command owner
     * @throws CommandDependencyException
     */
    public void registerCommand(Command command, CommandOwner owner) throws CommandDependencyException {
        registerCommand(command.getCommandData(), owner, command.getTranslator(), command, command.isForced());
        command.setloadded(true);
    }

    /**
     * Will unregister a given Command
     *
     * @param data The Command's data pack
     * @return true if the command was removed, false otherwise.
     */
    public boolean unregisterCommand(CommandData data) {
        if (!data.getParent().equals("")) {
            return Canary.commands().unregisterCommand(data.getParent() + "." + data.getCommandUID());
        } else {
            return Canary.commands().unregisterCommand("" + data.getCommandUID());
        }
    }

    /**
     * Will unregister a given Command
     *
     * @param command The Command pack to remove
     * @return true if the command was removed, false otherwise.
     */
    public boolean unregisterCommand(Command command) {
        if (unregisterCommand(command.getCommandData())) {
            command.setloadded(true);
            return true;
        } else {
            return false;
        }
    }

}
