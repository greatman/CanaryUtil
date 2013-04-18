/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:14:21 AM
 */

package net.larry1123.lib.plugin;

import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.larry1123.lib.logger.EELogger;
import net.larry1123.lib.logger.EELogger.EECLogger;

public abstract class UtilPlugin extends Plugin {

    protected String defultLoggerPath = EELogger.logPath + getName() + "/"
	    + getName();
    protected String pluginLogger;
    protected EECLogger logger = EELogger.getLogger(getName());

    public String addSubLoggerLevel(String prefix) {
	return getLogger().addLoggerLevelWFile(pluginLogger, prefix,
		getName() + "/" + prefix);
    }

    @Override
    public void disable() {
	// TODO Auto-generated method stub
	endLogger();
    }

    @Override
    public boolean enable() {
	startLogger();
	// TODO Auto-generated method stub
	return false;
    }

    public void enableFailed() {
	getLogger().logCustom(pluginLogger, "Plugin Could not be Enabled!");
	getLogger().removeLoggerLevel(pluginLogger);
    }

    public void enableFailed(String reason) {
	getLogger().logCustom(pluginLogger,
		"Plugin Could not be Enabled, because" + reason);
	getLogger().removeLoggerLevel(pluginLogger);
    }

    public void endLogger() {
	getLogger().removeLoggerLevel(pluginLogger);
    }

    public EECLogger getLogger() {
	return logger;
    }

    @Override
    public Logman getLogman() {
	return getLogger();
    }

    public void startLogger() {
	pluginLogger = getLogger().addLoggerLevelWFile(getName(),
		defultLoggerPath);
    }

}
