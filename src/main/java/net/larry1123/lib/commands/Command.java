package net.larry1123.lib.commands;

import net.larry1123.lib.plugin.commands.CommandData;
import net.larry1123.lib.plugin.commands.CommandExecute;
import net.visualillusionsent.utils.LocaleHelper;

public interface Command extends CommandExecute {

    public CommandData getCommandData();

    public LocaleHelper getTranslator();

    public boolean isForced();

    public boolean isloaded();

    public void setloadded(boolean loadedness);

}
