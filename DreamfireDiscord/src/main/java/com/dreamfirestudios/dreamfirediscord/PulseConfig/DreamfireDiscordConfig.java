package com.dreamfirestudios.dreamfirediscord.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticPulseConfig;
import com.dreamfirestudios.dreamConfig.Interface.StorageComment;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamfirediscord.Event.DreamfireDiscordEnableSystemEvent;
import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

@PulseAutoRegister
public class DreamfireDiscordConfig extends StaticPulseConfig<DreamfireDiscordConfig> {
    @Override
    public JavaPlugin mainClass() {return DreamfireDiscord.GetDreamfireDiscord();}
    @StorageComment("WARNING: SYSTEM WONT RUN IF FALSE!")
    public boolean systemEnabled = true;
    @StorageComment("Display debugs in the console logs for changes in this config!")
    public boolean debugConfig = false;

    public void ToggleSystemEnabled(Consumer<DreamfireDiscordConfig> onSuccess, boolean newState){
        new DreamfireDiscordEnableSystemEvent(systemEnabled, newState);
        systemEnabled = newState;
        SaveConfig(onSuccess, Throwable::printStackTrace);
    }
}
