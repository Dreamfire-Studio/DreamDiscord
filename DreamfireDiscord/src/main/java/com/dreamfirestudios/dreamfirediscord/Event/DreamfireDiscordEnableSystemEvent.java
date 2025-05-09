package com.dreamfirestudios.dreamfirediscord.Event;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class DreamfireDiscordEnableSystemEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final boolean oldState;
    private final boolean newState;

    public DreamfireDiscordEnableSystemEvent(boolean oldState, boolean newState){
        this.oldState = oldState;
        this.newState = newState;
        Bukkit.getPluginManager().callEvent(this);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public HandlerList getHandlers() {
        return handlers;
    }
}
