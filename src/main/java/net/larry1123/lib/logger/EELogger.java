/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:13:43 AM
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
import net.larry1123.lib.config.UtilConfig;

public class EELogger extends Logman {

    private static final HashMap<String, FileHandler> fileHandlers = new HashMap<String, FileHandler>();

    public static final EELogger log;

    public static String getLogpath() {
        return UtilConfig.getConfig().getLoggerConfig().getLoggerPath();
    }

    private final static HashMap<String, EELogger> loggers = new HashMap<String, EELogger>();

    static {
        log = new EELogger("ElecEntertainmentLogger");
        log.setParent(Logger.getLogger("Minecraft-Server"));
        log.setLevel(Level.ALL);

        File logDir = new File(getLogpath());
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }

    private final static String LoggerError = log.addLoggerLevel(
            "ElecEntertainmentLogger", "FileHandler");

    public static EELogger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            EELogger logman = new EELogger(name);
            logman.setParent(log);
            loggers.put(name, logman);
        }
        return loggers.get(name);
    }

    private final HashMap<FileHandler, UtilFilter> fileFilters = new HashMap<FileHandler, UtilFilter>();

    /**
     * This is the path for the log files of this logger
     */
    public final String path;

    public EELogger(String name) {
        super(name);

        path = getLogpath() + name + "/";

        String logpath = path + name;

        File logDir = new File(logpath.substring(0, logpath.lastIndexOf('/')));
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        try {
            FileHandler handler = gethandler(logpath + ".log");
            UtilFilter filter = fileFilters.get(handler);
            filter.setLogAll(true);
            handler.setFilter(filter);
        } catch (SecurityException e) {
            EELogger.log.logCustom(EELogger.LoggerError, "SecurityException", e);
        } catch (IOException e) {
            EELogger.log.logCustom(EELogger.LoggerError, "IOException", e);
        }
    }

    public String addLoggerLevel(String errorName) {
        return LoggerLevels.addLoggerLevel(errorName);
    }

    public String addLoggerLevel(String errorName, String prefix) {
        return LoggerLevels.addLoggerLevel(errorName, prefix);
    }

    public String addLoggerLevelWFile(String errorName, String pathName) {
        String name = LoggerLevels.addLoggerLevel(errorName);

        File logDir = new File(pathName.substring(0, pathName.lastIndexOf('/')));
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        try {
            FileHandler handler = gethandler(pathName + ".log");
            UtilFilter filter = fileFilters.get(handler);
            filter.addLogLevel(name);
            handler.setFilter(filter);
        } catch (SecurityException e) {
            EELogger.log.logCustom(EELogger.LoggerError, "SecurityException", e);
        } catch (IOException e) {
            EELogger.log.logCustom(EELogger.LoggerError, "IOException", e);
        }
        return name;
    }

    public String addLoggerLevelWFile(String errorName, String prefix,
            String pathName) {
        String name = LoggerLevels.addLoggerLevel(errorName, prefix);

        File logDir = new File(pathName.substring(0, pathName.lastIndexOf('/')));
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        try {
            FileHandler handler = gethandler(pathName + ".log");
            UtilFilter filter = (UtilFilter) handler.getFilter();
            filter.addLogLevel(name);
            handler.setFilter(filter);
        } catch (SecurityException e) {
            EELogger.log.logCustom(EELogger.LoggerError, "SecurityException", e);
        } catch (IOException e) {
            EELogger.log.logCustom(EELogger.LoggerError, "IOException", e);
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

    public static LoggerLevel getLoggerLevel(String name) {
        return LoggerLevels.getLoggerLevel(name);
    }

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

    public void logCustom(LoggerLevel lvl, String msg) {
        log(lvl, msg);
    }

    public void logCustom(LoggerLevel lvl, String msg, Throwable thrown) {
        log(lvl, msg, thrown);
    }

    public void logCustom(String lvl, String msg) {
        log(LoggerLevels.getLoggerLevel(lvl), msg);
    }

    public void logCustom(String lvl, String msg, Throwable thrown) {
        log(LoggerLevels.getLoggerLevel(lvl), msg, thrown);
    }

    public void removeLoggerLevel(String name) {
        LoggerLevels.removeLoggerLevel(name);
        if (fileHandlers.containsKey(name)) {
            removeHandler(fileHandlers.remove(name));
        }
    }

}
