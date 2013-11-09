package net.larry1123.util.api.plugin;

import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.larry1123.util.logger.EELogger;
import net.larry1123.util.logger.EELogman;

public abstract class UtilPlugin extends Plugin {

    private final EELogger logger = EELogger.getLogger(getName());
    private final EELogman loggerWrap = new EELogman(logger);

    protected final String defultLoggerPath = getLogger().path;

    /**
     * Retrieves a SubLogger
     *
     * @param name Name to name the subLogger
     * @return The SubLogger
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
     * The Logman returned is a wrapper of EELogger
     */
    @Override
    public Logman getLogman() {
        return loggerWrap;
    }

}
