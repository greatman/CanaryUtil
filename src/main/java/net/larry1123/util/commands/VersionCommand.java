package net.larry1123.util.commands;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.api.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.List;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class VersionCommand implements Command {

    private final CommandData command = new CommandData(new String[]{"help"}, new String[]{"canary.super.canaryutil.help", "canary.command.super.canaryutil.help"}, "TODO", "TODO");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public VersionCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command.setParent(utilCommands.baseCommand.getCommandData());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message(getPlugin().getName() + " Version: " + getPlugin().getVersion());
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
