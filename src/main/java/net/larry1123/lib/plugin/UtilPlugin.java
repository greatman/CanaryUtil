/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 8:00:21 AM
 */

package net.larry1123.lib.plugin;

import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.larry1123.lib.logger.EELogger;

public abstract class UtilPlugin extends Plugin {

    private final EELogger logger = EELogger.getLogger(getName());

    protected final String defultLoggerPath = logger.path;

    /**
     * Retrieves a SubLogger
     *
     * @param name
     * @return
     */
    public EELogger getSubLogger(String name) {
        return EELogger.getSubLogger(name, getLogger());
    }

    /**
     * Logs that this Plugin failed to start
     */
    public void enableFailed() {
        getLogger().logSevere("Plugin Could not be Enabled!");
    }

    /**
     * Logs that this Plugin failed to start and why
     *
     * @param reason The Reason that the plugin failed to start
     */
    public void enableFailed(String reason) {
        getLogger().logSevere("Plugin Could not be Enabled, because" + reason);
    }

    /**
     * Gets the EELogger of the current Plugin
     *
     * @return This Plugins EELogger
     */
    public EELogger getLogger() {
        return logger;
    }

    /**
     * {@inheritDoc}
     * <p/>
     * The Logman returned is a casted EELogger
     */
    @Override
    public Logman getLogman() {
        return getLogger();
    }

}
