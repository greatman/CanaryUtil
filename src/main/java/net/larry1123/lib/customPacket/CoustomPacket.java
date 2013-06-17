/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:23:35 AM
 */

package net.larry1123.lib.customPacket;

import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.config.UtilConfig;

public class CoustomPacket {

    private static UtilConfig config = UtilConfig.getConfig();

    private BungeeCord bungeecord;
    private CanaryUtil plugin;

    /**
     * Warning may return Null if the Util is not enabled yet!!!!
     * 
     * @return
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
