package net.larry1123.util.commands;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.api.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.List;

public class MusicCommand implements Command {

    private final CommandData command = new CommandData(new String[]{"music"}, new String[]{"canaryutil.music"}, "TODO", "TODO");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public MusicCommand(UtilCommands utilcommands) {
        this.utilcommands = utilcommands;
    }

    @Override
    public CommandData getCommandData() {
        return null;
    }

    @Override
    public LocaleHelper getTranslator() {
        return null;
    }

    @Override
    public boolean isForced() {
        return false;
    }

    @Override
    public boolean isloaded() {
        return false;
    }

    @Override
    public void setloadded(boolean loadedness) {
        loaded = loadedness;
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        //
    }

    @Override
    public List<String> tabComplete(MessageReceiver messageReceiver, String[] args) {
        return null;
    }
}
