/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:22:30 AM
 */

package net.larry1123.lib.config;

import net.larry1123.lib.CanaryUtil;

public class UtilConfig {

    private static UtilConfig config = new UtilConfig("CanaryUtil");

    private String pluginName;
    private BungeeCordConfig bungeecordConfig;
    private LoggerConfig loggerConfig;

    private CanaryUtil plugin;

    public static UtilConfig getConfig() {
        return config;
    }

    /**
     * Internal Util use only!
     * 
     * @param util
     */
    public void startReload(CanaryUtil util) {
        if (plugin == null) {
            plugin = util;
        }
    }

    private UtilConfig(String plugin) {
        pluginName = plugin;
        bungeecordConfig = new BungeeCordConfig(pluginName);
        loggerConfig = new LoggerConfig(pluginName);
    }

    /**
     * Gets the Config for
     * 
     * @return
     */
    public BungeeCordConfig getBungeeCordConfig() {
        return bungeecordConfig;
    }

    public void reloadBungeeCordConfig() {
        bungeecordConfig.reload();
    }

    public LoggerConfig getLoggerConfig() {
        return loggerConfig;
    }

    public void reloadLoggerConfig() {
        loggerConfig.reload();
    }

}
