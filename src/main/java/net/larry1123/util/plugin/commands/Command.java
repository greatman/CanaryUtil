/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 8:00:34 AM
 */

package net.larry1123.util.plugin.commands;

import net.visualillusionsent.utils.LocaleHelper;

public interface Command extends CommandExecute {

    /**
     * The Command's info pack
     *
     * @return The CommandData holding the commands data
     */
    public CommandData getCommandData();

    /**
     * @return localehelper for translating meta info
     */
    public LocaleHelper getTranslator();

    /**
     * Tells if this Command will override a command that is registered
     *
     * @return If this Command will override an other command
     */
    public boolean isForced();

    /**
     * If the Command has been registered or not
     *
     * @return true if command is registered, false otherwise
     */
    public boolean isloaded();

    /**
     * Sets if the command is registered or not
     *
     * @param loadedness
     */
    public void setloadded(boolean loadedness);

}
