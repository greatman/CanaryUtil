/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:30 AM
 */

package net.larry1123.lib.config;

public class UtilConfigManager {

    private static final UtilConfigManager config = new UtilConfigManager();

    private final String pluginName = "CanaryUtil";
    private final BungeeCordConfig bungeecordConfig;
    private final LoggerConfig loggerConfig;

    /**
     * Gets the Config Manager
     *
     * @return Config Manager
     */
    public static UtilConfigManager getConfig() {
        return config;
    }

    private UtilConfigManager() {
        bungeecordConfig = new BungeeCordConfig(pluginName);
        loggerConfig = new LoggerConfig(pluginName);
    }

    /**
     * Gets the Config for BungeeCord
     *
     * @return Config Manager for BungeeCord
     */
    public BungeeCordConfig getBungeeCordConfig() {
        return bungeecordConfig;
    }

    /**
     * Reloads the Config of the BungeeCord Config Manager
     */
    public void reloadBungeeCordConfig() {
        bungeecordConfig.reload();
    }

    /**
     * Gets the Config for the Logger
     *
     * @return Config Manager for Logger
     */
    public LoggerConfig getLoggerConfig() {
        return loggerConfig;
    }

    /**
     * Reloads the Config of the Logger Config Manager
     */
    public void reloadLoggerConfig() {
        loggerConfig.reload();
    }

}
