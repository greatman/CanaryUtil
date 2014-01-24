package net.larry1123.util.commands.bungeecord;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.api.plugin.commands.CommandData;
import net.larry1123.util.commands.UtilCommands;
import net.larry1123.util.config.UtilConfigManager;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.List;

public class BungeeCordSetCommand implements Command {

    private static final UtilConfigManager config = UtilConfigManager.getConfig();

    private final CommandData command = new CommandData(new String[]{"set"}, new String[]{"canary.super.canaryutil.bungeecord.set", "canary.command.super.canaryutil.bungeecord.set"}, "TODO set", "TODO set");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public BungeeCordSetCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command.setParent(utilcommands.bungeecordCommand.getCommandData());
        command.setMin(2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters[2].toLowerCase().equals("enabled")) {
            if (parameters.length > 2) {
                config.getBungeeCordConfig().setIsEnabled(Boolean.parseBoolean(parameters[3]));
            } else {
                caller.message(getCommandPath() + "enabled <true:false>");
            }
        } else if (parameters[2].toLowerCase().equals("time") || parameters[2].toLowerCase().equals("polltime")) {
            if (parameters.length > 2) {
                config.getBungeeCordConfig().setPollTime(Integer.parseInt(parameters[3]));
            } else {
                caller.message(getCommandPath() + "");
            }
        } else if (parameters[2].toLowerCase().equals("server")) {
            if (parameters.length > 2) {
                config.getBungeeCordConfig().setServerName(parameters[3]);
            } else {
                caller.message("");
            }
        } else {
            caller.message(getCommandPath() + "<enabled:polltime:server>");
        }
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

    private String getCommandPath() {
        return "/" + utilcommands.baseCommand.getCommandData().getAliases()[0] + " "
                + utilcommands.bungeecordCommand.getCommandData().getAliases()[0] +
                command.getAliases()[0];
    }

}
