/**
 * @author ElecEntertainment
 * @team Larry1123, JoshtMathews, Sinzo, Xalbec
 * @lastedit Jan 21, 2013 3:46:21 PM
 */

package net.larry1123.lib.logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import net.canarymod.logger.Logman;

public class EELogger {

    public static class EECLogger extends Logman {

	private final String name;

	public EECLogger(String name) {
	    super(name);
	    this.name = name;
	}

	public String addLoggerLevel(String errorName) {
	    return LoggerLevels.addLoggerLevel(errorName);
	}

	public String addLoggerLevel(String errorName, String prefix) {
	    return LoggerLevels.addLoggerLevel(errorName, prefix);
	}

	public LoggerLevel getLoggerLevel(String name) {
	    return LoggerLevels.getLoggerLevel(name);
	}

	@Override
	public void log(LogRecord logRecord) {
	    Level level = logRecord.getLevel();
	    String msg = logRecord.getMessage();
	    if (level instanceof LoggerLevel) {
		LoggerLevel handle = (LoggerLevel) level;
		if (!handle.getPrefix().equals("")) {
		    logRecord.setMessage(" [" + handle.getName() + "] ["
			    + handle.getPrefix() + "] " + msg);
		} else {
		    logRecord.setMessage(" [" + handle.getName() + "] " + msg);
		}
	    } else {
		logRecord.setMessage(new StringBuilder("[").append(name).append("] ").append(logRecord.getMessage()).toString());
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
	}

    }

    public static final EECLogger log;

    static {
	log = new EECLogger("ElecEntertainmentLogger");
	log.setParent(Logger.getLogger("Minecraft-Server"));
	log.setLevel(Level.ALL);
    }

    public static String addLoggerLevel(String errorName) {
	return LoggerLevels.addLoggerLevel(errorName);
    }

    public static String addLoggerLevel(String errorName, String prefix) {
	return LoggerLevels.addLoggerLevel(errorName, prefix);
    }

    public static EECLogger getLogger(String name) {
	return (EECLogger) Logman.getLogman(name);
    }

    public static LoggerLevel getLoggerLevel(String name) {
	return LoggerLevels.getLoggerLevel(name);
    }

    public static void removeLoggerLevel(String name) {
	LoggerLevels.removeLoggerLevel(name);
    }

}
