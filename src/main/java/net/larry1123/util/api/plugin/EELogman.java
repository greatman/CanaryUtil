package net.larry1123.util.api.plugin;

import net.canarymod.logger.Logman;
import net.larry1123.util.logger.EELogger;

import java.util.logging.LogRecord;

/**
 * To be Refactored to work with log4j and still use the EELogger
 *
 * @author Larry1123
 * @since 11/8/13 - 2:26 AM
 */
public class EELogman extends Logman {

    private final EELogger logger;

    /**
     * NOT to be used!!
     *
     * @param name N/A
     */
    public EELogman(String name) {
        super(name);
        logger = EELogger.getLogger(name);
    }

    /**
     * Makes a wrapper for an EELogger to work as an Logman
     *
     * @param logger The logger to br wrapped
     */
    public EELogman(EELogger logger) {
        super(logger.getName());
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    public void logInfo(String message) {
        getLogger().logInfo(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logWarning(String message) {
        getLogger().logWarning(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logSevere(String message) {
        getLogger().logSevere(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logDebug(String message) {
        getLogger().logDebug(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logDerp(String message) {
        getLogger().logDerp(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logPluginDebug(String message) {
        getLogger().logPluginDebug(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logStacktrace(String message, Throwable thrown) {
        getLogger().logStacktrace(message, thrown);
    }

    private EELogger getLogger() {
        return logger;
    }

}
