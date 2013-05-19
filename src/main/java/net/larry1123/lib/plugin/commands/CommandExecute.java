package net.larry1123.lib.plugin.commands;

import net.canarymod.chat.MessageReceiver;

public interface CommandExecute {

    public void execute(MessageReceiver caller, String[] parameters);

}
