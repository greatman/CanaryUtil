package net.larry1123.lib.customPacket;

import java.util.HashMap;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.channels.ChannelListener;
import net.canarymod.plugin.Plugin;

public final class BungeeCord {

    private static HashMap<Player, String> IPs = new HashMap<Player, String>();
    private static Plugin plugin = new BungeeCordPlugin();
    private static ChannelListener lis = new BungeeCordListener();

    static {
	Canary.hooks().registerListener(new BungeeCordListener(), plugin);
	Canary.channels().registerListener(plugin, "BungeeCord", lis);
    }

    static void addPlayerIp(Player player, String Ip, BungeeCordListener liss) {
	if (lis == liss) {
	    IPs.put(player, Ip);
	}
    }

    public static String getRealPlayerIp(Player player) {
	if (IPs.containsKey(player)) {
	    return IPs.get(player);
	} else {
	    return player.getIP();
	}
    }

    static void removePlayerIp(Player player, BungeeCordListener liss) {
	if (lis == liss) {
	    IPs.remove(player);
	}
    }

}
