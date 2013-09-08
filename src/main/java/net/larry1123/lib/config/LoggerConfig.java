/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:19 AM
 */

package net.larry1123.lib.config;

import net.larry1123.lib.task.FileSpliterUpdater;

public class LoggerConfig implements ConfigBase {

    public static enum Splits {

        NONE("None"), //
        DAY("Day"), //
        HOUR("Hour"), //
        MONTH("Month"), //
        WEEK("Week");

        private final String ths;

        Splits(String type) {
            ths = type;
        }

        public String getValue() {
            return ths;
        }

        public static Splits getFromString(String type) {
            for (Splits t : Splits.values()) {
                if (t.getValue().toLowerCase().equals(type.toLowerCase())) {
                    return t;
                }
            }
            return NONE;
        }

    }

    private final ConfigFile configManager;

    @ConfigFeild(name = "Logger-Path", comments = "This defines where the log files will be placed.")
    private String logger_Path = "pluginlogs/";

    @ConfigFeild(name = "Logger-Split", comments = { "If left blank it will default no spliting", "None|Hour|Day|Week|Month" })
    private String logsplit = "None";

    @ConfigFeild(name = "Logger-FileType", comments = "The FileType with out the leading '.'")
    private String logFileType = "log";

    @ConfigFeild(name = "Logger-CurrentSplit", comments = "Do not change this, used to keep track of splits over reloads and restarts")
    private String currentsplit = "";

    @ConfigFeild(name = "Paste-Enabled", comments = "Allows plugins to post errors to https://paste.larry1123.net/")
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
        FileSpliterUpdater.reloadUpdater();
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

    /**
     * Returns is the Server is allowed to send info to https://paste.larry1123.net/
     * 
     * @return true is allowed false not allowed
     */
    public boolean isPasteingAllowed() {
        return pasteSend;
    }

    /**
     * Get the Settings for how to Split Log Files
     * 
     * @return Current setting
     */
    public Splits getSplit() {
        return Splits.getFromString(logsplit);
    }

    /**
     * Gets the Currently set time stamp for Log files if Splitting is enabled
     * May be null if it has not been used
     * 
     * @return Currently used timestamp
     */
    public String getCurrentSplit() {
        return currentsplit;
    }

    /**
     * To be used by Logger Only Do not Change
     * 
     * @param current
     */
    public void setCurrentSplit(String current) {
        boolean change = !currentsplit.equals(current);
        currentsplit = current;
        configManager.save(); // Time to Save
        if (change) {
            FileSpliterUpdater.reloadUpdater();
        }
    }

    /**
     * Get what to set the File Type as
     * 
     * @return String of the file type to use
     */
    public String getFileType() {
        if (logFileType.startsWith(".")) {
            logFileType = logFileType.substring(2);
        }
        if (!logFileType.equals("")) {
            return this.logFileType;
        } else {
            return "log";
        }
    }

}
