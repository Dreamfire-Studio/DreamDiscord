package com.dreamfirestudios.dreamfirediscord.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticPulseConfig;
import com.dreamfirestudios.dreamConfig.SaveableObjects.SaveableHashmap;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@PulseAutoRegister
public class DreamfireDiscordSerilizableItems extends StaticPulseConfig<DreamfireDiscordSerilizableItems> {
    @Override
    public JavaPlugin mainClass() {return DreamfireDiscord.GetDreamfireDiscord();}

    public SaveableHashmap<String, ItemStack> itemStackSaveableHashmap = new SaveableHashmap<>(String.class, ItemStack.class);

    public void AddItemStack(String id, ItemStack itemStack){
        itemStackSaveableHashmap.getHashMap().put(id, itemStack);
    }

    public ItemStack GetItemStack(String id){
        return itemStackSaveableHashmap.getHashMap().getOrDefault(id, null);
    }
}
