/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:25:16 AM
 */

package net.larry1123.lib.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

final class UtilsLogFormat extends SimpleFormatter {

    private final SimpleDateFormat dateform = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm:ss");
    private final String linesep = System.getProperty("line.separator");

    @Override
    public final String format(LogRecord rec) {

        Level level = rec.getLevel();
        String msg = rec.getMessage();

        StringBuilder message = new StringBuilder();
        message.append(dateform.format(rec.getMillis()) + " ");

        if (level instanceof LoggerLevel) {
            LoggerLevel handle = (LoggerLevel) level;
            if (!handle.getPrefix().equals("")) {
                message.append("[" + handle.getPrefix() + "] " + msg);
            } else {
                message.append(msg);
            }
        } else {
            message.append("[" + level.getName() + "] " + rec.getMessage());
        }

        message.append(linesep);
        if (rec.getThrown() != null) {
            StringWriter stringwriter = new StringWriter();
            rec.getThrown().printStackTrace(new PrintWriter(stringwriter));
            message.append(stringwriter.toString());
        }
        return message.toString();
    }
}
