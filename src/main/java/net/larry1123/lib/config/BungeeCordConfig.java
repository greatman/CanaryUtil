package net.larry1123.lib.config;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

public class BungeeCordConfig {

    private PropertiesFile bungeecordConfig;
    private boolean isEnabled;
    private long pollTime;
    private String serverName;

    public BungeeCordConfig(String plugin) {
        bungeecordConfig = Configuration.getPluginConfig(plugin, "BungeeCord");
        /**
         * Time to setup the file and read the settings
         */
        {
            isEnabled = bungeecordConfig.getBoolean("BungeeCord-enabled", false);
            pollTime = bungeecordConfig.getLong("BungeeCord-pollTime", 100);
            serverName = bungeecordConfig.getString("BungeeCord-ServerName", "Server");

            bungeecordConfig.save(); // Time to Save
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public long getPollTime() {
        return pollTime;
    }

    public String getServerName() {
        return serverName;
    }

}
