/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:19 AM
 */

package net.larry1123.lib.config;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

public class LoggerConfig {

    private final String pathString = "Logger-Path";
    private final String apiString = "Paste-API-Key";

    private final String pathDefult = "pluginlogs/";
    private final String apiDefult = "";

    private PropertiesFile loggerConfig;
    private String loggerPath = pathDefult;
    private String apiKey = apiDefult;

    LoggerConfig(String plugin) {
        loggerConfig = Configuration.getPluginConfig(plugin, "Logger");
        loadData();
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        loggerConfig.reload();
        loadData();
    }

    /**
     * Gets the current Log Path
     * @return Current Log Path
     */
    public String getLoggerPath() {
        return loggerPath;
    }

    /**
     * COMMING SOON!
     * @return API KEY
     */
    public String getAPIKey() {
        return apiKey;
    }

    /**
     * Sets the Path for loggers
     * 
     * Will not change the location of Loggers that are running as it is but will move new ones
     * 
     * @param path Local Path to place Log files
     */
    public void setLoggerPath(String path) {
        loggerConfig.setString(pathString, loggerPath = path);
        loggerConfig.save(); // Time to Save
    }

    private void loadData() {
        /**
         * Time to setup the file and read the settings
         */
        loggerPath = loggerConfig.getString(pathString, pathDefult);
        loggerConfig.addComment(pathString, "This defines where the log files will be placed");

        apiKey = loggerConfig.getString(apiString, apiDefult);
        loggerConfig.addComment(apiString, "CommingSoon");

        loggerConfig.save(); // Time to Save

    }

}
