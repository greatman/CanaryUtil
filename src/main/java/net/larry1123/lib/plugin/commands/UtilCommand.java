/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:27:03 AM
 */

package net.larry1123.lib.plugin.commands;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandOwner;
import net.visualillusionsent.utils.LocaleHelper;

public class UtilCommand extends CanaryCommand {

    private final CommandExecute execute;

    public UtilCommand(CommandData data, CommandOwner owner, LocaleHelper translator, CommandExecute execute) {
        super(new CommandCommand(data), owner, translator);
        this.execute = execute;
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        this.execute.execute(caller, parameters);
    }

}
