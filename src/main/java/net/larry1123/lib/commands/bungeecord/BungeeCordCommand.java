/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:21:10 AM
 */

package net.larry1123.lib.commands.bungeecord;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.lib.commands.UtilCommands;
import net.larry1123.lib.plugin.commands.Command;
import net.larry1123.lib.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

public class BungeeCordCommand implements Command {

    private final String[] aliases = new String[]{ "bungeecord", "cord" };

    private final CommandData command;
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public BungeeCordCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command = new CommandData(
                aliases,
                new String[]{ "canary.super.canaryutil.bungeecord", "canary.command.super.canaryutil.bungeecord" },
                "TODO",
                "/" + utilcommands.baseCommand.getCommandData().getAliases()[0] + " " + aliases[0] + " <set|reload>"
                );
        command.setParent(utilcommands.baseCommand.getCommandData());
        command.setMax(1);
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message("/" + utilcommands.baseCommand.getCommandData().getAliases()[0] + " " + aliases[0] + " <set|reload>");
    }

    @Override
    public CommandData getCommandData() {
        return command;
    }

    @Override
    public LocaleHelper getTranslator() {
        return translator;
    }

    @Override
    public boolean isForced() {
        return false;
    }

    @Override
    public boolean isloaded() {
        return loaded;
    }

    @Override
    public void setloadded(boolean loadedness) {
        loaded = loadedness;
    }

}
