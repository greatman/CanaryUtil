package net.larry1123.util.commands.bungeecord;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.api.plugin.commands.CommandData;
import net.larry1123.util.commands.UtilCommands;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.List;

public class BungeeCordCommand implements Command {

    private final String[] aliases = new String[]{"bungeecord", "cord"};

    private final CommandData command;
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public BungeeCordCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command = new CommandData(
                aliases,
                new String[]{"canary.super.canaryutil.bungeecord", "canary.command.super.canaryutil.bungeecord"},
                "TODO",
                "/" + utilcommands.baseCommand.getCommandData().getAliases()[0] + " " + aliases[0] + " <set|reload>"
        );
        command.setParent(utilcommands.baseCommand.getCommandData());
        command.setMax(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message("/" + utilcommands.baseCommand.getCommandData().getAliases()[0] + " " + aliases[0] + " <set|reload>");
    }

    @Override
    public List<String> tabComplete(MessageReceiver messageReceiver, String[] args) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandData getCommandData() {
        return command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocaleHelper getTranslator() {
        return translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForced() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isloaded() {
        return loaded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setloadded(boolean loadedness) {
        loaded = loadedness;
    }

}
