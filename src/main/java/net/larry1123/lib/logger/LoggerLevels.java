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
     * @param levelName Name to give the level
     * @return String name of the Logger
     */
    public static String addInternalLoggerLevel(String levelName) {
        return addLoggerLevel(BaseString + levelName);
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * 
     * @param levelName Name to give the level
     * @return String ID of the LoggerLevel
     */
    private static String addLoggerLevel(String levelName) {
        return addLoggerLevel(levelName, "");
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * 
     * @param levelName Name to give the level
     * @param prefix The Prefix to give Messages with said given LoggerLevel
     * @return String ID of the LoggerLevel
     */
    private static String addLoggerLevel(String levelName, String prefix) {
        String name = levelName + "-" + prefix;
        LoggerLevels.put(name, new LoggerLevel(levelName, prefix, name));
        return name;
    }

    /**
     * This will add a Logger Level and return the name of the Level with in LoggerLevels
     * 
     * @param levelName Name to give the level
     * @param prefix The Prefix to give Messages with said given LoggerLevel
     * @param logger Logger to have Level Tied to
     * @return ID of the LoggerLevel
     */
    private static String addLoggerLevel(String levelName, String prefix, EELogger logger) {
        String name;
        if (!prefix.equals("")) {
            name = logger.getName() + ":" + levelName + "-" + prefix;
        } else {
            name = logger.getName() + ":" + levelName;
        }
        LoggerLevel lvl = new LoggerLevel(levelName, prefix, name);
        LoggerLevels.put(name, lvl);
        return name;
    }

    /**
     * Returns the Logger Level if there is one by this name or makes one
     * 
     * @param name Name of LoggerLevel to get
     * @return LoggerLevel The LoggerLevel that as been Gotten
     */
    public static LoggerLevel getLoggerLevel(String name) {
        if (LoggerLevels.containsKey(name)) {
            return LoggerLevels.get(name);
        } else {
            return LoggerLevels.get(addLoggerLevel(name));
        }
    }

    /**
     * Makes or returns a LoggerLevel
     * 
     * @param levelName Name to give the level
     * @param prefix The Prefix to give Messages with said given LoggerLevel
     * @return The LoggerLevel that as been Gotten
     */
    public static LoggerLevel getLoggerLevel(String levelName, String prefix) {
        String name = levelName + "-" + prefix;
        if (LoggerLevels.containsKey(name)) {
            return LoggerLevels.get(name);
        } else {
            return LoggerLevels.get(addLoggerLevel(levelName, prefix));
        }
    }

    /**
     * Makes or returns a LoggerLevel
     * 
     * @param levelName Name to give the level
     * @param logger Logger to have Level Tied to
     * @return The LoggerLevel that as been Gotten
     */
    public static LoggerLevel getLoggerLevel(String levelName, EELogger logger) {
        return getLoggerLevel(levelName, "", logger);
    }

    /**
     * Makes or returns a LoggerLevel
     * 
     * @param levelName Name to give the level
     * @param prefix The Prefix to give Messages with said given LoggerLevel
     * @param logger Logger to have Level Tied to
     * @return The LoggerLevel that as been Gotten
     */
    public static LoggerLevel getLoggerLevel(String levelName, String prefix, EELogger logger) {
        String name;
        if (!prefix.equals("")) {
            name = logger.getName() + ":" + levelName + "-" + prefix;
        } else {
            name = logger.getName() + ":" + levelName;
        }
        if (LoggerLevels.containsKey(name)) {
            return LoggerLevels.get(name);
        } else {
            return LoggerLevels.get(addLoggerLevel(levelName, prefix, logger));
        }
    }

    /**
     * This will Remove a LoggerLevel from being able to be tracked and returned by this Level Manager
     * 
     * @param name ID of the Logger to remove
     */
    public static void removeLoggerLevel(String name) {
        LoggerLevels.remove(name);
    }

    /**
     * This will Remove a LoggerLevel from being able to be tracked and returned by this Level Manager
     * 
     * @param lvl Logger to remove
     */
    public static void removeLoggerLevel(LoggerLevel lvl) {
        removeLoggerLevel(lvl.getID());
    }

}
