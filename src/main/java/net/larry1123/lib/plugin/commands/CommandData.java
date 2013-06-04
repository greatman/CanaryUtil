package net.larry1123.lib.plugin.commands;

import java.util.UUID;


public class CommandData {

    private final String[] aliases;
    private final String[] permissions;
    private final String description;
    private final String toolTip;
    private String parent = "";
    private String helpLookup = "";
    private String[] searchTerms = {" "};
    private int min = 1;
    private int max = -1;

    private final UUID commandID = UUID.randomUUID();

    /**
     * 
     * @param aliases
     * @param permissions
     * @param description
     * @param toolTip
     */
    public CommandData(String[] aliases, String[] permissions, String description, String toolTip) {
        this.aliases = aliases;
        this.permissions = permissions;
        this.description = description;
        this.toolTip = toolTip;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setParent(UUID parent) {
        this.parent = "" + parent;
    }

    public void setParent(CommandData parent) {
        this.parent = "" + parent.getCommandUID();
    }

    public void setHelpLookup(String helpLookup) {
        this.helpLookup = helpLookup;
    }

    public void setSearchTerms(String[] searchTerms) {
        this.searchTerms = searchTerms;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getParent() {
        return parent;
    }

    public String getHelpLookup() {
        return helpLookup;
    }

    public String[] getSearchTerms() {
        return searchTerms;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getDescription() {
        return description;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getToolTip() {
        return toolTip;
    }

    public UUID getCommandUID() {
        return commandID;
    }

}
