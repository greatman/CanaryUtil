package net.larry1123.lib.config;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

public class LoggerConfig {

    private PropertiesFile loggerConfig;
    private String loggerPath;

    public LoggerConfig(String plugin) {
        loggerConfig = Configuration.getPluginConfig(plugin, "Logger");
        /**
         * Time to setup the file and read the settings
         */
        {
            loggerPath = loggerConfig.getString("Logger-Path", "pluginlogs/");
            loggerConfig.addComment("Logger-Path", "Be sure to have the line end with /");

            loggerConfig.save();
        }
    }

    public String getLoggerPath() {
        return loggerPath;
    }

}
