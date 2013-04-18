/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:13:56 AM
 */

package net.larry1123.lib.logger;

import java.util.logging.Level;

public class LoggerLevel extends Level {

    private static int baselvl = 10000;

    private static final long serialVersionUID = 912743220309496892L;

    private final static int genLevel() {
	baselvl++;
	return baselvl;
    }

    private String prefix = "";

    LoggerLevel(String name) {
	super(name, genLevel());
    }

    LoggerLevel(String name, String prefix) {
	super(name, genLevel());
	this.prefix = prefix;
    }

    public String getPrefix() {
	return prefix;
    }

}