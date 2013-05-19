package net.larry1123.lib.plugin.commands;

import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandOwner;
import net.visualillusionsent.utils.LocaleHelper;

public class Commands {

    public void registerCommand(CommandData data, CommandOwner owner, LocaleHelper translator, CommandExecute execute, boolean force) throws CommandDependencyException {
        UtilCommand command = new UtilCommand(data, owner, translator, execute);
        Canary.commands().registerCommand(command, owner, force);
    }

}
