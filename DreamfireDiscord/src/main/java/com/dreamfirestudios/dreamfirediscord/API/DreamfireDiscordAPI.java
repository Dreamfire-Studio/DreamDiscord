package com.dreamfirestudios.dreamfirediscord.API;

;
import com.dreamfirestudios.dreamConfig.DreamConfig;
import com.dreamfirestudios.dreamConfig.Enum.StorageType;
import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;
import com.dreamfirestudios.dreamfirediscord.Event.DreamfireDiscordReloadConfigEvent;
import com.dreamfirestudios.dreamfirediscord.Event.DreamfireDiscordResetConfigEvent;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordConfig;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordSerilizableItems;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class DreamfireDiscordAPI {
    public static void DreamfireDiscordEnableSystem(boolean state){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            craftLegendsCoreConfig.systemEnabled = state;
            craftLegendsCoreConfig.SaveConfig(iPulseConfig -> {}, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    public static void DreamfireDiscordEnableSystem(){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            craftLegendsCoreConfig.systemEnabled = !craftLegendsCoreConfig.systemEnabled;
            craftLegendsCoreConfig.SaveConfig(iPulseConfig -> {}, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    public static void SerializeItem(String id, ItemStack itemStack){
        DreamfireDiscordSerilizableItems.ReturnStaticAsync(DreamfireDiscordSerilizableItems.class, craftLegendsCoreSerilizableItems -> {
            craftLegendsCoreSerilizableItems.AddItemStack(id, itemStack);
            craftLegendsCoreSerilizableItems.SaveConfig(iPulseConfig -> {}, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    public static void DreamfireDiscordResetConfigs(){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamConfig.GetDreamfireConfig().RegisterStatic(DreamfireDiscord.GetDreamfireDiscord(), true);
            new DreamfireDiscordResetConfigEvent();
        }, Throwable::printStackTrace);
    }

    public static void DreamfireDiscordReloadConfigs(){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamConfig.GetDreamfireConfig().RegisterStatic(DreamfireDiscord.GetDreamfireDiscord(), true);
            new DreamfireDiscordReloadConfigEvent();
        }, Throwable::printStackTrace);
    }
}