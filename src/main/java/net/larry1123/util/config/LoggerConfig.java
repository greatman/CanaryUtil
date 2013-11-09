package net.larry1123.util.config;

import net.larry1123.util.logger.FileSplits;
import net.larry1123.util.logger.LoggerSettings;
import net.larry1123.util.task.FileSpliterUpdater;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class LoggerConfig implements ConfigBase, LoggerSettings {

    private final ConfigFile configManager;

    @ConfigField(name = "Logger-Path", comments = "This defines where the log files will be placed.")
    private String logger_Path = "pluginlogs/";

    @ConfigField(name = "Logger-Split", comments = {"If left blank it will default no spliting", "None|Hour|Day|Week|Month"})
    private String logSplit = "None";

    @ConfigField(name = "Logger-FileType", comments = "The FileType with out the leading '.'")
    private String logFileType = "log";

    @ConfigField(name = "Logger-CurrentSplit", comments = "Do not change this, used to keep track of splits over reloads and restarts")
    private String currentSplit = "";

    @ConfigField(name = "Paste-Enabled", comments = "Allows plugins to post errors to https://paste.larry1123.net/")
    private boolean pasteSend = true;

    @ConfigField(name = "Paste-UserName", comments = "Set the Name to post Paste as if enabled.")
    private String pasteUserName = "";

    public LoggerConfig(String plugin) {
        configManager = new ConfigFile(this, plugin, "Logger");
    }

    LoggerConfig() {
        configManager = new ConfigFile(this, getPlugin(), "Logger");
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        configManager.reload();
        FileSpliterUpdater.reloadUpdater();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggerPath() {
        return logger_Path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserName() {
        return pasteUserName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserName(String name) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoggerPath(String path) {
        logger_Path = path;
        configManager.save(); // Time to Save
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPastingAllowed() {
        return pasteSend;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPastingAllowed(boolean state) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSplits getSplit() {
        return FileSplits.getFromString(logSplit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentSplit() {
        return currentSplit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSplit(String current) {
        boolean change = !currentSplit.equals(current);
        currentSplit = current;
        configManager.save(); // Time to Save
        if (change) {
            FileSpliterUpdater.reloadUpdater();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileType(String type) {
        // TODO add command to change setting
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParentLogger() {
        return "Minecraft-Server";
    }

}
