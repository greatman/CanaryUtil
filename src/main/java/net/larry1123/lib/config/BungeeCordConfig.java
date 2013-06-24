/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:56:09 AM
 */

package net.larry1123.lib.config;

import net.canarymod.config.Configuration;
import net.larry1123.lib.CanaryUtil;
import net.visualillusionsent.utils.PropertiesFile;

public class BungeeCordConfig {

    private final String enabledString = "BungeeCord-enabled";
    private final String pollTimeString = "BungeeCord-pollTime";
    private final String serverString = "BungeeCord-ServerName";

    private final boolean enableDefult = false;
    private final long pollTimeDefult = 100;
    private final String serverNameDefult = "Server";

    private PropertiesFile bungeecordConfig;
    private boolean isEnabled = enableDefult;
    private long pollTime = pollTimeDefult;
    private String serverName = serverNameDefult;

    BungeeCordConfig(String plugin) {
        bungeecordConfig = Configuration.getPluginConfig(plugin, "BungeeCord");
        loadData();
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        bungeecordConfig.reload();
        loadData();
        CanaryUtil.coustomPacket().reloadBungeeCord();
    }

    /**
     * Gets the state of BungeeCord use.
     * 
     * @return If BungeeCord functions are enabled
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Gets how long to wait between Polling a BungeeCord Server
     * 
     * @return wait time for polling
     */
    public long getPollTime() {
        return pollTime;
    }

    /**
     * Gets what the Name of this Server is.
     * 
     * @return Gets the Server's Name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Will Enable or disable BungeeCord Functions
     * 
     * @param state
     *            true to start, false to stop
     */
    public void setIsEnabled(boolean state) {
        bungeecordConfig.setBoolean(enabledString, isEnabled = state);
        bungeecordConfig.save(); // Time to Save
        CanaryUtil.coustomPacket().reloadBungeeCord();
    }

    /**
     * Sets the wait between Polling a BungeeCord Server
     * 
     * @param time
     *            the number of ms to wait
     */
    public void setPollTime(long time) {
        bungeecordConfig.setLong(pollTimeString, pollTime = time);
        bungeecordConfig.save(); // Time to Save
        CanaryUtil.coustomPacket().reloadBungeeCord();
    }

    /**
     * Sets this Server's name
     * Mainly for use with out BungeeCord Running
     * 
     * @param name
     *            Name of this server
     */
    public void setServerName(String name) {
        bungeecordConfig.setString(serverName, serverName = name);
        bungeecordConfig.save(); // Time to Save
        CanaryUtil.coustomPacket().reloadBungeeCord();
    }

    private void loadData() {
        /**
         * Time to setup the file and read the settings
         */
        isEnabled = bungeecordConfig.getBoolean(enabledString, enableDefult);
        bungeecordConfig.addComment(enabledString, "This sets if the Util will try to talk to a BungeeCord server or not");
        pollTime = bungeecordConfig.getLong(pollTimeString, pollTimeDefult);
        bungeecordConfig.addComment(pollTimeString, "This sets how many ms between when the Util will send packets to BungeeCord");
        serverName = bungeecordConfig.getString(serverString, serverNameDefult);
        bungeecordConfig.addComment(serverString, "This is only used if BungeeCord is disabled, and as a defult if no players have yet connected");

        bungeecordConfig.save(); // Time to Save
    }

}
