package net.larry1123.util.api.plugin.commands;

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
