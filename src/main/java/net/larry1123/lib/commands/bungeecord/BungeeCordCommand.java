package net.larry1123.lib.commands.bungeecord;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.lib.commands.UtilCommands;
import net.larry1123.lib.plugin.commands.Command;
import net.larry1123.lib.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

public class BungeeCordCommand implements Command {

    private final CommandData command = new CommandData(new String[] {"bungeecord" , "cord"}, new String[] {"canary.super.canaryutil.bungeecord", "canary.command.super.canaryutil.bungeecord"}, "TODO", "TODO");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public BungeeCordCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command.setParent(utilcommands.baseCommand.getCommandData());
        command.setMax(1);
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message("TODO");
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
