package net.larry1123.lib.commands;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.lib.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

public class HelpCommand implements Command {

    private final CommandData command = new CommandData(new String[] {"help"}, new String[] {"canary.super.canaryutil.help", "canary.command.super.canaryutil.help"}, "TODO", "TODO");
    private final LocaleHelper translator = Translator.getInstance();

    private boolean loaded = false;

    public HelpCommand(UtilCommands utilCommands) {
        command.setParent(utilCommands.baseCommand.getCommandData().getAliases()[0]);
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
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message("CanaryUtil Help called");
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
