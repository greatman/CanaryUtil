/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jul 27, 2013 11:44:43 PM
 */

package net.larry1123.lib.logger;

import java.util.LinkedList;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

final class UtilFilter implements Filter {

    private final LinkedList<LoggerLevel> allowed = new LinkedList<LoggerLevel>();
    private boolean all = false;

    /**
     * Adds a LoggerLevel to be reported to this Log File!
     *
     * @param lvl What Level to add
     */
    public void addLogLevel(LoggerLevel lvl) {
        allowed.add(lvl);
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

    /**
     * Will allow all Levels to be Logged to the log file
     *
     * @param state true to enable logging everything false to disable logging everything
     */
    public void setLogAll(boolean state) {
        all = state;
    }

    /**
     * Disallows a Level from being able to log into the Log File
     *
     * @param lvl Level to stop logging
     */
    public void removeLogLevel(LoggerLevel lvl) {
        allowed.remove(lvl);
    }

}
