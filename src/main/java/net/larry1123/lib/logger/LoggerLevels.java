/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:59:53 AM
 */

package net.larry1123.lib.logger;

import java.util.HashMap;

public class LoggerLevels {

    private final static HashMap<String, LoggerLevel> LoggerLevels = new HashMap<String, LoggerLevel>();
    private final static String BaseString = "ElecEntertainmentLogger-";

    static {
        addInternalLoggerLevel("LoggerLevelFunctions-FailedToCreateLoggerLevel");
        addInternalLoggerLevel("LoggerLevelFunctions-FailedToFindLoggerLevel");
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * To be used by the Logging System only
     * 
     * @param levelName
     * @return String name of the Logger
     */
    public static String addInternalLoggerLevel(String levelName) {
        return addLoggerLevel(BaseString + levelName);
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * 
     * @param levelName
     * @return String name of the LoggerLevel
     */
    public static String addLoggerLevel(String levelName) {
        return addLoggerLevel(levelName, "");
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * 
     * @param levelName
     * @param prefix
     * @return String name of the LoggerLevel
     */
    public static String addLoggerLevel(String levelName, String prefix) {
        String name = levelName + prefix;
        LoggerLevels.put(name, new LoggerLevel(levelName, prefix));
        return name;
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * 
     * @param levelName
     * @param logger
     * @return name of the LoggerLevel
     */
    public static String addLoggerLevel(String levelName, EELogger logger) {
        return addLoggerLevel(levelName, "", logger);
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * 
     * @param levelName
     * @param prefix
     * @param logger
     * @return name of the LoggerLevel
     */
    public static String addLoggerLevel(String levelName, String prefix, EELogger logger) {
        String name;
        if (!prefix.equals("")) {
            name = logger.getName() + ":" + levelName + "-" + prefix;
        } else {
            name = logger.getName() + ":" + levelName;
        }
        LoggerLevel lvl = new LoggerLevel(levelName, prefix);
        LoggerLevels.put(name, lvl);
        return name;
    }

    /**
     * Returns the Logger Level if there is one by this name or makes one
     * 
     * @param name
     * @return EE_LoggerLevel
     */
    public static LoggerLevel getLoggerLevel(String name) {
        if (LoggerLevels.containsKey(name)) {
            return LoggerLevels.get(name);
        } else {
            return LoggerLevels.get(addLoggerLevel(name));
        }
    }

    public static void removeLoggerLevel(String name) {
        LoggerLevels.remove(name);
    }

}
