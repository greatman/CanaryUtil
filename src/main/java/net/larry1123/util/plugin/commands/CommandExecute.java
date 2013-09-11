/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 8:01:07 AM
 */

package net.larry1123.util.plugin.commands;

import net.canarymod.chat.MessageReceiver;

public interface CommandExecute {

    /**
     * The Method that is used to process the use of a command
     *
     * @param caller     Who used the command.
     * @param parameters What was contained in the command
     */
    public void execute(MessageReceiver caller, String[] parameters);

}
