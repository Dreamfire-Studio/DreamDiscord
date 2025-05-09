package com.dreamfirestudios.dreamfirediscord.Event;

import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class DreamfireDiscordReloadConfigEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public DreamfireDiscordReloadConfigEvent(){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, dreamfireDiscordConfig -> {
            if(dreamfireDiscordConfig.systemEnabled) return;
            Bukkit.getScheduler().runTask(DreamfireDiscord.GetDreamfireDiscord(), () -> {Bukkit.getPluginManager().callEvent(this);});
        }, Throwable::printStackTrace);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }
}
