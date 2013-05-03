/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:12:20 AM
 */

package net.larry1123.lib;

import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.lib.customPacket.BungeeCord;
import net.larry1123.lib.plugin.UtilPlugin;

public class CanaryUtil extends UtilPlugin {

    public static class CoustomPacket {

	public String getRealPlayerIp(Player player) {
	    return BungeeCord.getRealPlayerIp(player);
	}

    }

    static CoustomPacket coustompacket = new CoustomPacket();

    public static CoustomPacket coustomPacket() {
	return coustompacket;
    }

    protected String version = "0.0.1";

    protected String author = "Larry1123";

    @Override
    public void disable() {
	logger.logCustom(pluginLoggerLevel, "Plugin Disabled");
	endLogger();
    }

    @Override
    public boolean enable() {
	logger.logCustom(pluginLoggerLevel, "Plugin Enabled");
	return true;
    }

}
