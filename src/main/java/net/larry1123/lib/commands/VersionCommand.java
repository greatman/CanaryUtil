package net.larry1123.lib.commands;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.lib.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

public class VersionCommand implements Command {

    private final CommandData command = new CommandData(new String[] {"help"}, new String[] {"canary.super.canaryutil.help", "canary.command.super.canaryutil.help"}, "TODO", "TODO");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public VersionCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command.setParent(utilCommands.baseCommand.getCommandData());
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message(utilcommands.getOwner().getName() + " Version: " + utilcommands.getOwner().getVersion());
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
