package net.larry1123.util.api.plugin.hooks;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.Hook;

public class BungeeCordPollHook extends Hook {

    private final Player player;

    public BungeeCordPollHook(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
