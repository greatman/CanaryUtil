package net.larry1123.util.api.plugin.commands;

import net.canarymod.chat.MessageReceiver;

import java.util.List;

public interface CommandExecute {

    /**
     * The Method that is used to process the use of a command
     *
     * @param caller     Who used the command.
     * @param parameters What was contained in the command
     */
    public void execute(MessageReceiver caller, String[] parameters);

    /**
     * Called when a AutoComplete is asked for
     *
     * @param messageReceiver the {@link net.canarymod.chat.MessageReceiver} using tabComplete
     * @param args the current arguments of the command
     * @return list of possible completions
     */
    public List<String> tabComplete(MessageReceiver messageReceiver, String[] args);

}
