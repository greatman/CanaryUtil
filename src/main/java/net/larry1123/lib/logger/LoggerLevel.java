/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:59:37 AM
 */

package net.larry1123.lib.logger;

import java.util.logging.Level;

public class LoggerLevel extends Level {

    private static int baselvl = 10000;
    private static final long serialVersionUID = 912743220309496892L;
    private final String id;

    private static int genLevel() {
        baselvl++;
        return baselvl;
    }

    private final String prefix;

    LoggerLevel(String name, String id) {
        this(name, "", id);
    }

    LoggerLevel(String name, String prefix, String id) {
        super(name, genLevel());
        this.prefix = prefix;
        this.id = id;
    }

    /**
     * Get the Prefix of the Level
     * 
     * @return
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Gets the ID that is given to this LoggerLevel so the Manager can retrieve the right Level later
     * 
     * @return This Level's ID
     */
    public String getID() {
        return id;
    }

}