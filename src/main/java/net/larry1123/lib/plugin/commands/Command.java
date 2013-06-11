package net.larry1123.lib.plugin.commands;

import net.visualillusionsent.utils.LocaleHelper;

public interface Command extends CommandExecute {

    public CommandData getCommandData();

    public LocaleHelper getTranslator();

    public boolean isForced();

    public boolean isloaded();

    public void setloadded(boolean loadedness);

}
