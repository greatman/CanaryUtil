/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:12:56 AM
 */

package net.larry1123.lib.customPacket;

import java.util.Arrays;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.channels.ChannelListener;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.hook.player.DisconnectionHook;
import net.canarymod.plugin.PluginListener;
import net.larry1123.lib.CanaryUtil;
import net.larry1123.lib.logger.EELogger;

public final class BungeeCordListener extends ChannelListener implements
PluginListener {

    private final EELogger logger = EELogger.getLogger(CanaryUtil.class.getClass().getSimpleName());

    @HookHandler
    public void addToChannle(ConnectionHook hook) {
        BungeeCord.addPlayerIp(hook.getPlayer(), hook.getPlayer().getIP(), this);
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord","IP".getBytes(), hook.getPlayer());
    }

    @Override
    public void onChannelInput(String channel, Player player, byte[] byteStream) {
        String data = Arrays.toString(byteStream);
        player.sendMessage(data);
        if (data.startsWith("IP")) {
            logger.logCustom(CanaryUtil.class.getClass().getSimpleName(), "Message gotten on BungeeCord: " + data.substring(2));
            BungeeCord.addPlayerIp(player, data.substring(2), this);
        }
    }

    @HookHandler
    public void removeToChannle(DisconnectionHook hook) {
        Canary.channels().unregisterClient("BungeeCord", hook.getPlayer().getNetServerHandler());
        BungeeCord.removePlayerIp(hook.getPlayer(), this);
    }

}
