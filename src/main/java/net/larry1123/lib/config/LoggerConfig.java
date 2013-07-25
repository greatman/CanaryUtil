/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:19 AM
 */

package net.larry1123.lib.config;

public class LoggerConfig implements ConfigBase {

    private ConfigFile configManager;

    @ConfigFeild(comments = "This defines where the log files will be placed.")
    private String logger_Path = "pluginlogs/";

    @ConfigFeild(name = "Paste-Enabled", comments = "TODO")
    private boolean pasteSend = true;

    @ConfigFeild(name = "Paste-UserName", comments = "Set the Name to post Paste as if enabled.")
    private String pasteUserName = "";

    LoggerConfig(String plugin) {
        configManager = new ConfigFile(this, plugin, "Logger");
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        configManager.reload();
    }

    /**
     * Gets the current Log Path
     * 
     * @return Current Log Path
     */
    public String getLoggerPath() {
        return logger_Path;
    }

    /**
     * Returns the User Name to post paste as.
     * 
     * @return Returns the User Name to post paste as.
     */
    public String getUserName() {
        return pasteUserName;
    }

    /**
     * Sets the Path for loggers
     * Will not change the location of Loggers that are running as it is but will move new ones
     * 
     * @param path
     *            Local Path to place Log files
     */
    public void setLoggerPath(String path) {
        logger_Path = path;
        configManager.save(); // Time to Save
    }

    public boolean isPasteingAllowed() {
        return pasteSend;
    }

}
