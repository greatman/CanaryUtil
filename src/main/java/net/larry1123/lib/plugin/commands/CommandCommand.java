/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:26:04 AM
 */

package net.larry1123.lib.plugin.commands;

import java.lang.annotation.Annotation;
import net.canarymod.commandsys.Command;
import net.larry1123.lib.logger.EELogger;

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
        index = 0;
        for (String alias : aliases) {
            EELogger.getLogger("UtilLogger").info(index++ + " \"" + alias + "\"");
        }
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
