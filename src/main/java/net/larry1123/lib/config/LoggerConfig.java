/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:22:14 AM
 */

package net.larry1123.lib.config;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

public class LoggerConfig {

    private final String pathString = "Logger-Path";

    private final String pathDefult = "pluginlogs/";

    private PropertiesFile loggerConfig;
    private String loggerPath = pathDefult;

    LoggerConfig(String plugin) {
        loggerConfig = Configuration.getPluginConfig(plugin, "Logger");
        loadData();
    }

    void reload() {
        loggerConfig.reload();
        loadData();
    }

    public String getLoggerPath() {
        return loggerPath;
    }

    /**
     * Sets the Path for loggers
     * 
     * @param path
     * @return
     */
    public void setLoggerPath(String path) {
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        loggerConfig.setString(pathString, loggerPath = path);
        loggerConfig.save(); // Time to Save
        // Will not change the location of Loggers that are running as it is but will move new ones!
    }

    private void loadData() {
        /**
         * Time to setup the file and read the settings
         */
        loggerPath = loggerConfig.getString(pathString, pathDefult);
        if (!loggerPath.endsWith("/")) {
            loggerPath = loggerPath + "/";
            loggerConfig.setString(pathString, loggerPath);
        }
        loggerConfig.addComment(pathString, "This defines where the log files will be placed");

        loggerConfig.save(); // Time to Save
    }

}
