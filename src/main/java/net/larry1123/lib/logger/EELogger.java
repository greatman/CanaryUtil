/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jul 27, 2013 11:45:19 PM
 */

package net.larry1123.lib.logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import net.canarymod.logger.Logman;
import net.larry1123.lib.config.LoggerConfig;
import net.larry1123.lib.config.UtilConfigManager;

public class EELogger extends Logman {

    private static final LoggerConfig config = UtilConfigManager.getConfig().getLoggerConfig();

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
        return config.getLoggerPath();
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

    public final static String fileHandlerError = log.addLoggerLevel("ElecEntertainmentLogger", "FileHandler");

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
        FileManager.setUpFile(this, logpath);
        if (log != null) {
            setParent(log);
        }
    }

    private EELogger(String name, EELogger parent) {
        super(parent.getName() + ":" + name);
        path = parent.path;
        logpath = path + parent.getName() + ":" + name;
        FileManager.setUpFile(this, logpath);
        setParent(parent);
    }

    /**
     * Creates a LoggerLevel for this Logger
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     * 
     * @param levelName
     * @return
     */
    public String addLoggerLevel(String levelName) {
        return LoggerLevels.getLoggerLevel(levelName, this).getID();
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     * 
     * @param levelName
     * @param prefix
     * @return
     */
    public String addLoggerLevel(String levelName, String prefix) {
        return LoggerLevels.getLoggerLevel(levelName, prefix, this).getID();
    }

    /**
     * Creates a LoggerLevel for this Logger and saves it to a Log file
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] {Message}
     * 
     * @param levelName
     * @return
     */
    public String addLoggerLevelWFile(String levelName) {
        LoggerLevel lvl = LoggerLevels.getLoggerLevel(levelName, this);
        String levelPath = logpath + "-" + levelName;
        FileManager.setUpFile(this, lvl, levelPath);
        return lvl.getID();
    }

    /**
     * Creates a LoggerLevel for this Logger with a prefix and saves it to a Log file
     * Makes the Log look like this: [{LoggerName}] [{LevelName}] [{Prefix}] {Message}
     * 
     * @param levelName
     * @param prefix
     * @return
     */
    public String addLoggerLevelWFile(String levelName, String prefix) {
        LoggerLevel lvl = LoggerLevels.getLoggerLevel(levelName, prefix, this);
        String levelPath = logpath + "-" + levelName;
        FileManager.setUpFile(this, lvl, levelPath);
        return lvl.getID();
    }

    /**
     * Get the LoggerLevel for the given name.
     * 
     * @param name
     * @return
     */
    public LoggerLevel getLoggerLevel(String name) {
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
     * Remove a LoggerLevel
     * 
     * @param name
     *            LoggerLevel's name
     */
    public void removeLoggerLevel(String name) {
        removeLoggerLevel(getLoggerLevel(name));
    }

    /**
     * Remove a LoggerLevel
     * 
     * @param name
     *            LoggerLevel's name
     */
    public void removeLoggerLevel(LoggerLevel lvl) {
        FileManager.removeLoggerLevel(lvl);
        LoggerLevels.removeLoggerLevel(lvl);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the Level Warning
     * 
     * @param message
     *            Message to be Logged
     * @param thrown
     *            Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public boolean logStacktraceToPasteBin(String message, Throwable thrown) {
        return logStacktraceToPasteBin(Level.WARNING, message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the LoggerLevel Given
     * 
     * @param lvl
     *            String of the LoggerLevel's name to throw with
     * @param message
     *            Message to be Logged
     * @param thrown
     *            Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public void logStacktraceToPasteBin(String lvl, String message, Throwable thrown) {
        logStacktraceToPasteBin(LoggerLevels.getLoggerLevel(lvl), message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the LoggerLevel Given
     * 
     * @param lvl
     *            Object of the LoggerLevel to throw with
     * @param message
     *            Message to be Logged
     * @param thrown
     *            Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public boolean logStacktraceToPasteBin(LoggerLevel lvl, String message, Throwable thrown) {
        if (!lvl.getPrefix().isEmpty()) {
            message = "[" + lvl.getPrefix() + "] " + message;
        }
        return logStacktraceToPasteBin(lvl, message, thrown);
    }

    /**
     * Will Log a StackTrace and Post it on to http://paste.larry1123.net/
     * Will return true if it was able to post and false if it was not able to post
     * Throws with the Level given
     * 
     * @param lvl
     *            The Level to be thrown with
     * @param message
     *            Message to be Logged
     * @param thrown
     *            Throwable Error To be logged
     * @return True if paste was made of stacktrace false if it failed for any reason
     */
    public boolean logStacktraceToPasteBin(Level lvl, String message, Throwable thrown) {
        log(lvl, message, thrown);

        if (config.isPasteingAllowed()) {
            try {
                URL url = new URL("http://paste.larry1123.net/");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = "paste_data=" + "[" + lvl.getName() + "] " + message + "\n" + org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(thrown);
                urlParameters += "&";
                urlParameters += "paste_lang=Java";
                urlParameters += "&";
                urlParameters += "api_submit=true";
                urlParameters += "&";
                urlParameters += "mode=xml";
                urlParameters += "&";
                urlParameters += "paste_expire=0";
                urlParameters += "&";
                urlParameters += "paste_project=" + this.getName();
                if (!config.getUserName().equals("")) {
                    urlParameters += "&";
                    urlParameters += "paste_user=" + config.getUserName();
                }

                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                if (responseCode == 200) {
                    return response.toString().contains("<id>");
                } else {
                    return false;
                }

            } catch (MalformedURLException e) {
                log.log(Level.SEVERE, "Failed to send: Malformed", e);
                return false;
            } catch (IOException e) {
                log.log(Level.SEVERE, "Failed to send: IOException", e);
                return false;
            }
        } else {
            return false;
        }
    }

}
