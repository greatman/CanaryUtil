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
            bungeecordConfig.addComment("BungeeCord-enabled", "This sets if the Util will try to talk to a BungeeCord server or not");
            pollTime = bungeecordConfig.getLong("BungeeCord-pollTime", 100);
            bungeecordConfig.addComment("BungeeCord-pollTime", "This sets how many ms between when the Util will send packets to BungeeCord");
            serverName = bungeecordConfig.getString("BungeeCord-ServerName", "Server");
            bungeecordConfig.addComment("BungeeCord-ServerName", "This is only used if BungeeCord is disabled, and as a defult if no players have yet connected");

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
