package net.larry1123.lib.plugin.commands;

import java.lang.annotation.Annotation;

import net.canarymod.commandsys.Command;

public class CommandCommand implements Command {

    public final String[] aliases;
    public final String[] permissions;
    public final String description;
    public final String toolTip;
    public final String parent;
    public final String helpLookup;
    public final String[] searchTerms;
    public final int min;
    public final int max;

    CommandCommand(CommandData data) {
        this.aliases = data.aliases;
        this.permissions = data.permissions;
        this.description = data.description;
        this.toolTip = data.toolTip;
        this.parent = data.parent;
        this.helpLookup = data.helpLookup;
        this.searchTerms = data.searchTerms;
        this.min = data.min;
        this.max = data.max;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Command.class;
    }

    @Override
    public String[] aliases() {
        return this.aliases;
    }

    @Override
    public String[] permissions() {
        return this.permissions;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String toolTip() {
        return this.toolTip;
    }

    @Override
    public String parent() {
        return this.parent;
    }

    @Override
    public String helpLookup() {
        return this.helpLookup;
    }

    @Override
    public String[] searchTerms() {
        return this.searchTerms;
    }

    @Override
    public int min() {
        return this.min;
    }

    @Override
    public int max() {
        return this.max;
    }

}
