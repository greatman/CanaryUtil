/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 8:00:55 AM
 */

package net.larry1123.lib.plugin.commands;

import java.util.UUID;

public class CommandData {

    private final String[] aliases;
    private final String[] permissions;
    private final String description;
    private final String toolTip;
    private String parent = "";
    private String helpLookup = "";
    private String[] searchTerms = { " " };
    private int min = 1;
    private int max = -1;

    private final UUID commandID = UUID.randomUUID();

    public CommandData(String[] aliases, String[] permissions, String description, String toolTip) {
        this.aliases = aliases;
        this.permissions = permissions;
        this.description = description;
        this.toolTip = toolTip;
    }

    /**
     * Sets the Parent to a string
     * 
     * @param parent
     *            Parent chain as String
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * Sets the Parent to another command by CommandData UID + Parent Chain
     * 
     * @param parent
     *            CommandData to set as parent
     */
    public void setParent(CommandData parent) {
        if (parent.getParent() == "") {
            setParent("" + parent.getCommandUID());
        } else {
            setParent(parent.getParent() + "." + parent.getCommandUID());
        }
    }

    /**
     * xplicitly define a name with which the command will be registered
     * at the help system. If this is empty (default), all aliases will be registered.
     * Otherwise only this name will be registered. <br>
     * Use it for registering sub-command helps to avoid name conflicts
     * 
     * @param helpLookup
     */
    public void setHelpLookup(String helpLookup) {
        this.helpLookup = helpLookup;
    }

    /**
     * Set specific terms for looking up this command in help search
     * 
     * @param searchTerms
     *            String[] holding the Terms
     */
    public void setSearchTerms(String[] searchTerms) {
        this.searchTerms = searchTerms;
    }

    /**
     * Set the min amount of parameters
     * 
     * @param min
     *            Min amount of parameters
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Set the max amounts of parameters.
     * 
     * @param max
     *            The max amounts of parameters. -1 for infinite amount
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * The parent command, for creating sub-command structures
     * 
     * @return String of the Parent chain
     */
    public String getParent() {
        return parent;
    }

    /**
     * Explicitly define a name with which the command will be registered
     * at the help system. If this is empty (default), all aliases will be registered.
     * Otherwise only this name will be registered. <br>
     * Use it for registering sub-command helps to avoid name conflicts
     * 
     * @return
     */
    public String getHelpLookup() {
        return helpLookup;
    }

    /**
     * Specifies specific terms for looking up this command in help search
     * 
     * @return
     */
    public String[] getSearchTerms() {
        return searchTerms;
    }

    /**
     * Min amount of parameters
     * 
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     * The max amounts of parameters.
     * 
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     * What does this command do?
     * This will be displayed in a help context.
     * Note: This string will be pushed through the translator that is attached to this command.
     * If it finds a respective translation, it will output that instead
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * A list of permissions to use this command.
     * If you specify more than one, only one of them is needed to execute the command
     * 
     * @return
     */
    public String[] getPermissions() {
        return permissions;
    }

    /**
     * The command's names
     * 
     * @return
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * The tip to display when command parsing failed.
     * This may also be displayed when help for this command
     * was specifically requested
     * 
     * @return
     */
    public String getToolTip() {
        return toolTip;
    }

    /**
     * Get the UUID of the Command
     * 
     * @return The UUID for this Command
     */
    public UUID getCommandUID() {
        return commandID;
    }

}
