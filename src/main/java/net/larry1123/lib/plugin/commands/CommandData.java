package net.larry1123.lib.plugin.commands;

public class CommandData {

    public final String[] aliases;
    public final String[] permissions;
    public final String description;
    public final String toolTip;
    public String parent = "";
    public String helpLookup = "";
    public String[] searchTerms = {" "};
    public int min = 1;
    public int max = -1;

    CommandData(String[] aliases, String[] permissions, String description, String toolTip) {
        this.aliases = aliases;
        this.permissions = permissions;
        this.description = description;
        this.toolTip = toolTip;
    }

}
