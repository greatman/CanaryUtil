/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:25:49 AM
 */

package net.larry1123.lib.plugin.commands;

import net.visualillusionsent.utils.LocaleHelper;

public interface Command extends CommandExecute {

    public CommandData getCommandData();

    public LocaleHelper getTranslator();

    public boolean isForced();

    public boolean isloaded();

    public void setloadded(boolean loadedness);

}
