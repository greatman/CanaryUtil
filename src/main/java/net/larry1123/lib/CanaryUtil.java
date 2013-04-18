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

    public static CoustomPacket coustomPacket() {
	return coustompacket;
    }

    protected String version = "0.0.1";

    protected String author = "Larry1123";

    static CoustomPacket coustompacket = new CoustomPacket();

    @Override
    public void disable() {
	getLogger().logCustom(pluginLogger, "Plugin Disabled");
	endLogger();

    }

    @Override
    public boolean enable() {
	startLogger();
	getLogger().logCustom(pluginLogger, "Plugin Enabled");
	return true;
    }

}
