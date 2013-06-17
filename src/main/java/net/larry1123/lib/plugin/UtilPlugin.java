/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:25:34 AM
 */

package net.larry1123.lib.plugin;

import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.larry1123.lib.logger.EELogger;

public abstract class UtilPlugin extends Plugin {

    protected EELogger logger = EELogger.getLogger(getName());

    protected String defultLoggerPath = logger.path;

    protected String pluginLoggerLevel = getLogger().addLoggerLevelWFile(getName(), defultLoggerPath + getName());

    public String addSubLoggerLevel(String prefix) {
        return getLogger().addLoggerLevelWFile(pluginLoggerLevel, prefix, defultLoggerPath + prefix);
    }

    public void enableFailed() {
        getLogger().logSevere("Plugin Could not be Enabled!");
    }

    public void enableFailed(String reason) {
        getLogger().logSevere("Plugin Could not be Enabled, because" + reason);
    }

    public EELogger getLogger() {
        return logger;
    }

    @Override
    public Logman getLogman() {
        return getLogger();
    }

}
