/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Aug 13, 2013 8:28:43 AM
 */
package net.larry1123.util.logger;

import net.larry1123.util.config.LoggerConfig;
import net.larry1123.util.config.UtilConfigManager;

public class LoggerSettings {

    private static final LoggerConfig config = UtilConfigManager.getConfig().getLoggerConfig();

    /**
     * Gets the current Log Path
     * 
     * @return Current Log Path
     */
    public String getLoggerPath() {
        return config.getLoggerPath();
    }

    /**
     * Sets the Path for loggers
     * Will not change the location of Loggers that are running as it is but will move new ones
     * 
     * @param path
     *            Local Path to place Log files
     */
    public void setLoggerPath(String path) {
        config.setLoggerPath(path);
    }

    /**
     * Returns is the Server is allowed to send info to https://paste.larry1123.net/
     * 
     * @return true is allowed false not allowed
     */
    public boolean isPasteingAllowed() {
        return config.isPasteingAllowed();
    }

    /**
     * TODO
     * 
     * @param state
     */
    public void setPasteingAllowed(boolean state) {
        // XXX
    }

    /**
     * Returns the User Name to post paste as.
     * 
     * @return Returns the User Name to post paste as.
     */
    public String getUserName() {
        return config.getUserName();
    }

    /**
     * TODO
     * 
     * @param name
     */
    public void setUserName(String name) {
        // XXX
    }

    /**
     * Get the Settings for how to Split Log Files
     * 
     * @return Current setting
     */
    public FileSplits getSplit() {
        return config.getSplit();
    }

    /**
     * Gets the Currently set time stamp for Log files if Splitting is enabled
     * May be null if it has not been used
     * 
     * @return Currently used timestamp
     */
    public String getCurrentSplit() {
        return config.getCurrentSplit();
    }

    /**
     * To be used by Logger Only Do not Change
     * 
     * @param current
     */
    public void setCurrentSplit(String current) {
        config.setCurrentSplit(current);
    }

    /**
     * Get what to set the File Type as
     * 
     * @return String of the file type to use
     */
    public String getFileType() {
        return config.getFileType();
    }

    /**
     * TODO
     * 
     * @param type
     */
    public void setFileType(String type) {
        // XXX
    }

    /**
     * TODO
     *
     * @return
     */
    public String getParentLogger() {
        return "Minecraft-Server";
    }

}
