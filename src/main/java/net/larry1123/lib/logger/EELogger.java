/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:59:26 AM
 */

package net.larry1123.lib.logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import net.canarymod.logger.Logman;
import net.larry1123.lib.config.UtilConfigManager;

public class EELogger extends Logman {

    private static final HashMap<String, FileHandler> fileHandlers = new HashMap<String, FileHandler>();

    private static final HashMap<FileHandler, UtilFilter> fileFilters = new HashMap<FileHandler, UtilFilter>();

    /**
     * Logger to log about Logging ... yea I know
     */
    public static final EELogger log = new EELogger("ElecEntertainmentLogger");

    /**
     * Gets the Path for Log files
     * 
     * @return
     */
    public static String getLogpath() {
        return UtilConfigManager.getConfig().getLoggerConfig().getLoggerPath();
    }

    /**
     * Holds All EELoggers
     */
    private final static HashMap<String, EELogger> loggers = new HashMap<String, EELogger>();

    static {
        log.setParent(Logger.getLogger("Minecraft-Server"));
        log.setLevel(Level.ALL);

        File logDir = new File(getLogpath());
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }

    private final static String fileHandlerError = log.addLoggerLevel("ElecEntertainmentLogger", "FileHandler");

    /**
     * Gets the EELogger for the given name
     * 
     * @param name
     *            Name of the Logger
     * @return
     */
    public static EELogger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            EELogger logman = new EELogger(name);
            loggers.put(logman.getName(), logman);
        }
        return loggers.get(name);
    }

    /**
     * Gets the EELogger for the given name as a sub of the given parent
     * 
     * @param name
     * @param parent
     * @return
     */
    public static EELogger getSubLogger(String name, EELogger parent) {
        if (!loggers.containsKey(parent.getName() + ":" + name)) {
            EELogger logman = new EELogger(name, parent);
            loggers.put(logman.getName(), logman);
        }
        return loggers.get(parent.getName() + ":" + name);
    }

    /**
     * This is the path for the log files of this logger
     */
    public final String path;
    public final String logpath;

    private EELogger(String name) {
        super(name);
        path = getLogpath() + name + "/";
        logpath = path + name;
        createDirectoryFromPath(logpath);
        try {
            FileHandler handler = gethandler(logpath + ".log");
            UtilFilter filter = fileFilters.get(handler);
            filter.setLogAll(true);
            handler.setFilter(filter);
        } catch (SecurityException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "SecurityException", e);
        } catch (IOException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "IOException", e);
        }
        if (log != null) {
            setParent(log);
        }
    }

    private EELogger(String name, EELogger parent) {
        super(parent.getName() + ":" + name);
        path = parent.path;
        logpath = path + parent.getName() + ":" + name;
        createDirectoryFromPath(logpath);
        try {
            FileHandler handler = gethandler(logpath + ".log");
            UtilFilter filter = fileFilters.get(handler);
            filter.setLogAll(true);
            handler.setFilter(filter);
        } catch (SecurityException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "SecurityException", e);
        } catch (IOException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "IOException", e);
        }
        setParent(parent);
    }

    /**
     * Creates a LoggerLevel for this Logger
     * 
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     * 
     * @param levelName
     * @return
     */
    public String addLoggerLevel(String levelName) {
        return LoggerLevels.addLoggerLevel(levelName, this);
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix
     * 
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     * 
     * @param levelName
     * @param prefix
     * @return
     */
    public String addLoggerLevel(String levelName, String prefix) {
        return LoggerLevels.addLoggerLevel(levelName, prefix, this);
    }

    /**
     * Creates a LoggerLevel for this Logger and saves it to a Log file
     * 
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     * 
     * @param levelName
     * @return
     */
    public String addLoggerLevelWFile(String levelName) {
        String name = LoggerLevels.addLoggerLevel(levelName, this);
        createDirectoryFromPath(path);
        String levelPath = logpath + "." + levelName;
        try {
            FileHandler handler = gethandler(levelPath + ".log");
            UtilFilter filter = fileFilters.get(handler);
            filter.addLogLevel(name);
            handler.setFilter(filter);
        } catch (SecurityException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "SecurityException", e);
        } catch (IOException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "IOException", e);
        }
        return name;
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix and saves it to a Log file
     * 
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     * 
     * @param levelName
     * @param prefix
     * @return
     */
    public String addLoggerLevelWFile(String levelName, String prefix) {
        String name = LoggerLevels.addLoggerLevel(levelName, prefix, this);
        createDirectoryFromPath(path);
        String levelPath = logpath + "." + levelName + "-" + prefix;
        try {
            FileHandler handler = gethandler(levelPath + ".log");
            UtilFilter filter = fileFilters.get(handler);
            filter.addLogLevel(name);
            handler.setFilter(filter);
        } catch (SecurityException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "SecurityException", e);
        } catch (IOException e) {
            EELogger.log.logCustom(EELogger.fileHandlerError, "IOException", e);
        }
        return name;
    }

    private FileHandler gethandler(String pathName) throws SecurityException,
    IOException {

        if (!fileHandlers.containsKey(pathName)) {

            FileHandler handler = new FileHandler(pathName, true);
            UtilsLogFormat lf = new UtilsLogFormat();
            UtilFilter uf = new UtilFilter();
            fileFilters.put(handler, uf);
            handler.setFilter(uf);
            handler.setLevel(Level.ALL);
            handler.setFormatter(lf);
            handler.setEncoding("UTF-8");

            this.addHandler(handler);

            fileHandlers.put(pathName, handler);
        }
        return fileHandlers.get(pathName);

    }

    /**
     * Get the LoggerLevel for the given name.
     * 
     * @param name
     * @return
     */
    public static LoggerLevel getLoggerLevel(String name) {
        return LoggerLevels.getLoggerLevel(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(LogRecord logRecord) {
        Level level = logRecord.getLevel();
        String msg = logRecord.getMessage();

        if (level instanceof LoggerLevel) {
            LoggerLevel handle = (LoggerLevel) level;
            if (!handle.getPrefix().isEmpty()) {
                logRecord.setMessage("[" + handle.getPrefix() + "] " + msg);
            }
        }
        super.log(logRecord);
    }

    /**
     * Used to Log a Custom Logger Level
     * 
     * @param lvl
     *            The LoggerLevel object to use
     * @param msg
     *            Message to be Logged with Level
     */
    public void logCustom(LoggerLevel lvl, String msg) {
        log(lvl, msg);
    }

    /**
     * Used to Log a Custom Logger Level with a StackTrace
     * 
     * @param lvl
     *            The LoggerLevel object to use
     * @param msg
     *            Message to be Logged with Level
     * @param thrown
     *            The Throwable Error
     */
    public void logCustom(LoggerLevel lvl, String msg, Throwable thrown) {
        log(lvl, msg, thrown);
    }

    /**
     * Used to Log a Custom Logger Level
     * 
     * @param lvl
     *            The name of the LoggerLevel to use
     * @param msg
     *            Message to be Logged with Level
     */
    public void logCustom(String lvl, String msg) {
        log(LoggerLevels.getLoggerLevel(lvl), msg);
    }

    /**
     * Used to Log a Custom Logger Level with a StackTrace
     * 
     * @param lvl
     *            The name of the LoggerLevel to use
     * @param msg
     *            Message to be Logged with Level
     * @param thrown
     *            The Throwable Error
     */
    public void logCustom(String lvl, String msg, Throwable thrown) {
        log(LoggerLevels.getLoggerLevel(lvl), msg, thrown);
    }

    /**
     * TODO Change this to be per Logger
     * 
     * @param name
     */
    public void removeLoggerLevel(String name) {
        LoggerLevels.removeLoggerLevel(name);
        if (fileHandlers.containsKey(name)) {
            removeHandler(fileHandlers.remove(name));
        }
    }

    private void createDirectoryFromPath(String path) {
        File logDir = new File(path.substring(0, path.lastIndexOf('/')));
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }

}
