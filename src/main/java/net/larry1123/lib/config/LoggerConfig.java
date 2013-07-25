/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:19 AM
 */

package net.larry1123.lib.config;


public class LoggerConfig implements ConfigBase {

    private final String pathDefult = "pluginlogs/";
    private final String apiDefult = "";

    private ConfigFile configManager;

    @ConfigFeild( comments = "This defines where the log files will be placed." )
    private String Logger_Path = pathDefult;

    @ConfigFeild
    private String Paste_API_Key = apiDefult;

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
     * @return Current Log Path
     */
    public String getLoggerPath() {
        return Logger_Path;
    }

    /**
     * COMMING SOON!
     * @return API KEY
     */
    public String getAPIKey() {
        return Paste_API_Key;
    }

    /**
     * Sets the Path for loggers
     * 
     * Will not change the location of Loggers that are running as it is but will move new ones
     * 
     * @param path Local Path to place Log files
     */
    public void setLoggerPath(String path) {
        Logger_Path = path;
        configManager.save(); // Time to Save
    }

}
