package net.larry1123.util.api.plugin.commands;

import net.canarymod.commandsys.Command;

import java.lang.annotation.Annotation;

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
        String[] aliases = new String[data.getAliases().length + 1];
        int index = 0;
        for (String alias : data.getAliases()) {
            aliases[index++] = alias;
        }
        aliases[index] = "" + data.getCommandUID();
        this.aliases = aliases;
        this.permissions = data.getPermissions();
        this.description = data.getDescription();
        this.toolTip = data.getToolTip();
        this.parent = data.getParent();
        this.helpLookup = data.getHelpLookup();
        this.searchTerms = data.getSearchTerms();
        this.min = data.getMin();
        this.max = data.getMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Annotation> annotationType() {
        return Command.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] aliases() {
        return this.aliases;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] permissions() {
        return this.permissions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String description() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toolTip() {
        return this.toolTip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String parent() {
        return this.parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String helpLookup() {
        return this.helpLookup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] searchTerms() {
        return this.searchTerms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int min() {
        return this.min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int max() {
        return this.max;
    }

    @Override
    public String tabCompleteMethod() {
        return null;
    }

}
