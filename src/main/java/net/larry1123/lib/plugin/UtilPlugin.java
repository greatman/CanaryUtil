/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:14:21 AM
 */

package net.larry1123.lib.plugin;

import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.larry1123.lib.logger.EELogger;

public abstract class UtilPlugin extends Plugin {

    protected String defultLoggerPath = EELogger.logPath + getName() + '/'
	    + getName();
    protected String pluginLoggerLevel;
    protected EELogger logger = EELogger.getLogger(getName());

    public String addSubLoggerLevel(String prefix) {
	return getLogger().addLoggerLevelWFile(pluginLoggerLevel, prefix,
		getName() + "/" + prefix);
    }

    /**
     * CanaryMod will call this upon disabling this plugin
     */
    @Override
    public void disable() {
	// TODO Auto-generated method stub
	endLogger();
    }

    /**
     * CanaryMod will call this upon enabling this plugin
     * 
     * @return {@code true} to signal successful enable; {@code false} if known
     *         to be unable to run
     */
    @Override
    public boolean enable() {
	startLogger();
	// TODO Auto-generated method stub
	return false;
    }

    public void enableFailed() {
	getLogger().logCustom(pluginLoggerLevel, "Plugin Could not be Enabled!");
	getLogger().removeLoggerLevel(pluginLoggerLevel);
    }

    public void enableFailed(String reason) {
	getLogger().logCustom(pluginLoggerLevel,
		"Plugin Could not be Enabled, because" + reason);
	getLogger().removeLoggerLevel(pluginLoggerLevel);
    }

    public void endLogger() {
	getLogger().removeLoggerLevel(pluginLoggerLevel);
    }

    public EELogger getLogger() {
	return logger;
    }

    @Override
    public Logman getLogman() {
	return getLogger();
    }

    public void startLogger() {
	pluginLoggerLevel = getLogger().addLoggerLevelWFile(getName(),
		defultLoggerPath);
    }

}
