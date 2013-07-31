/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 24, 2013 7:57:09 AM
 */

package net.larry1123.lib.customPacket;

import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.config.UtilConfigManager;
import net.larry1123.lib.task.UpdateBungeeInfo;

public class CoustomPacket {

    /**
     * Config Manager
     */
    private static UtilConfigManager config = UtilConfigManager.getConfig();
    /**
     * The currently running BungeeCord manager
     */
    private BungeeCord bungeecord;
    private CanaryUtil plugin;

    /**
     * Gets the Currently running BungeeCord manager, may be the online or offline version
     * 
     * Warning may return Null if the Util is not enabled yet!!!!
     * 
     * @return Current BungeeCord Manager
     */
    public BungeeCord getBungeeCord() {
        return bungeecord;
    }

    public CoustomPacket(CanaryUtil plugin) {
        this.plugin = plugin;
        if (config.getBungeeCordConfig().isEnabled()) {
            bungeecord = new BungeeCord(this.plugin);
            UpdateBungeeInfo.startUpdater();
        } else {
            new BungeeCordless(this.plugin);
        }
    }

    /**
     * Will start BungeeCord functions if the config allows or stops BungeeCord functions if running if needed to be
     */
    public void reloadBungeeCord() {
        if (config.getBungeeCordConfig().isEnabled()) {
            if (bungeecord instanceof BungeeCordless) {
                bungeecord = new BungeeCord(this.plugin);
            }
            UpdateBungeeInfo.reloadUpdater();
        } else {
            if (!(bungeecord instanceof BungeeCordless)) {
                new BungeeCordless(this.plugin);
            }
            UpdateBungeeInfo.endUpdater();
        }
    }

}
