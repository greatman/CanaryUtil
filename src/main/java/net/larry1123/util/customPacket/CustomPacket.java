package net.larry1123.util.customPacket;

import net.larry1123.util.config.UtilConfigManager;
import net.larry1123.util.task.UpdateBungeeInfo;

public class CustomPacket {

    /**
     * Config Manager
     */
    private static final UtilConfigManager config = UtilConfigManager.getConfig();
    /**
     * The currently running BungeeCord manager
     */
    private BungeeCord bungeecord;

    /**
     * Gets the Currently running BungeeCord manager, may be the online or offline version
     *
     * @return Current BungeeCord Manager
     */
    public BungeeCord getBungeeCord() {
        return bungeecord;
    }

    public CustomPacket() {
        if (config.getBungeeCordConfig().isEnabled()) {
            bungeecord = new BungeeCord();
            UpdateBungeeInfo.startUpdater();
        } else {
            new BungeeCordless();
        }
    }

    /**
     * Will start BungeeCord functions if the config allows or stops BungeeCord functions if running if needed to be
     */
    public void reloadBungeeCord() {
        if (config.getBungeeCordConfig().isEnabled()) {
            if (bungeecord instanceof BungeeCordless) {
                bungeecord = new BungeeCord();
            }
            UpdateBungeeInfo.reloadUpdater();
        } else {
            if (!(bungeecord instanceof BungeeCordless)) {
                new BungeeCordless();
            }
            UpdateBungeeInfo.endUpdater();
        }
    }

}
