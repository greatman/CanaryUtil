/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:21:56 AM
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

    void reload() {
        bungeecordConfig.reload();

        loadData();

        CanaryUtil.coustomPacket().reloadBungeeCord();
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

    public void setIsEnabled(boolean state) {
        bungeecordConfig.setBoolean(enabledString, isEnabled = state);
        bungeecordConfig.save(); // Time to Save
        CanaryUtil.coustomPacket().reloadBungeeCord();
    }

    public void setPollTime(long time) {
        bungeecordConfig.setLong(pollTimeString, pollTime = time);
        bungeecordConfig.save(); // Time to Save
        CanaryUtil.coustomPacket().reloadBungeeCord();
    }

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
