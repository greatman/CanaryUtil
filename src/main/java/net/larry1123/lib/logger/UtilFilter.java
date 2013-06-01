package net.larry1123.lib.logger;

import java.util.LinkedList;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

final class UtilFilter implements Filter {

    private final LinkedList<LoggerLevel> allowed = new LinkedList<LoggerLevel>();
    private boolean all = false;

    public void addLogLevel(String name) {
        allowed.add(EELogger.getLoggerLevel(name));
    }

    @Override
    public boolean isLoggable(LogRecord rec) {

        if (all) {
            return true;
        }

        Level level = rec.getLevel();

        if (level instanceof LoggerLevel) {
            LoggerLevel handle = (LoggerLevel) level;
            if (allowed.contains(handle)) {
                return true;
            }
        }
        return false;
    }

    public void setLogAll(boolean state) {
        all = state;
    }

    public void removeLogLevel(String name) {
        allowed.remove(EELogger.getLoggerLevel(name));
    }

}
