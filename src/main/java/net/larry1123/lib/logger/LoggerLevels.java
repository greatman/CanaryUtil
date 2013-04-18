/**
 * @author ElecEntertainment
 * @team Larry1123, JoshtMathews, Sinzo, Xalbec
 * @lastedit Jan 21, 2013 3:46:40 PM
 */

package net.larry1123.lib.logger;

import java.util.HashMap;

public class LoggerLevels {

    public static HashMap<String, LoggerLevel> LoggerLevels = new HashMap<String, LoggerLevel>();
    public static String BaseString = "ElecEntertainmentLogger-";

    static {
	addInternalLoggerLevel("LoggerLevelFunctions-FailedToCreateLoggerLevel");
	addInternalLoggerLevel("LoggerLevelFunctions-FailedToFindLoggerLevel");
    }

    /**
     * 
     * This will add a Logger Level and return the name of the Level with in
     * LoggerLevels
     * 
     * To be used by the Logging System only
     * 
     * @param errorName
     * @return String name of the Logger
     */
    public static String addInternalLoggerLevel(String errorName) {
	String name = BaseString.concat(errorName);
	LoggerLevel lvl = new LoggerLevel(name);
	LoggerLevels.put(name, lvl);
	return name;
    }

    /**
     * 
     * This will add a Logger Level and return the name of the Level with in
     * LoggerLevels
     * 
     * @param errorName
     * @return String name of the Logger
     */
    public static String addLoggerLevel(String errorName) {
	String name = errorName;
	LoggerLevels.put(name, new LoggerLevel(name));
	return name;
    }

    /**
     * 
     * This will add a Logger Level and return the name of the Level with in
     * LoggerLevels
     * 
     * @param errorName
     * @param prefix
     * @return String name of the Logger
     */
    public static String addLoggerLevel(String errorName, String prefix) {
	String name = errorName.concat(prefix);
	LoggerLevel lvl = new LoggerLevel(name, prefix);
	LoggerLevels.put(name, lvl);
	return name;
    }

    /**
     * 
     * Returns the Logger Level if there is one by this name
     * 
     * @param name
     * @return EE_LoggerLevel
     */
    public static LoggerLevel getLoggerLevel(String name) {
	if (LoggerLevels.containsKey(name)) {
	    return LoggerLevels.get(name);
	}
	return LoggerLevels
		.get("ElecEntertainment-LoggerLevelFunctions-FailedToFindLoggerLevel-");
    }

    public static void removeLoggerLevel(String name) {
	LoggerLevels.remove(name);
    }

}
