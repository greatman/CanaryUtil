/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:21:24 AM
 */

package net.larry1123.lib.commands.bungeecord;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.lib.chat.FontTools;
import net.larry1123.lib.commands.UtilCommands;
import net.larry1123.lib.config.UtilConfig;
import net.larry1123.lib.plugin.commands.Command;
import net.larry1123.lib.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

public class BungeeCordReloadCommand implements Command {

    private final CommandData command = new CommandData(new String[]{ "reload" }, new String[]{ "canary.super.canaryutil.bungeecord.reload", "canary.command.super.canaryutil.bungeecord.reload" }, "TODO reload", "TODO reload");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public BungeeCordReloadCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command.setParent(utilcommands.bungeecordCommand.getCommandData());
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        UtilConfig.getConfig().reloadBungeeCordConfig();
        caller.message(FontTools.ORANGE + FontTools.UNDERLINED + "BungeeCord Settings Updated!");
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
