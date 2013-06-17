/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:26:35 AM
 */

package net.larry1123.lib.plugin.commands;

import net.canarymod.chat.MessageReceiver;

public interface CommandExecute {

    public void execute(MessageReceiver caller, String[] parameters);

}
