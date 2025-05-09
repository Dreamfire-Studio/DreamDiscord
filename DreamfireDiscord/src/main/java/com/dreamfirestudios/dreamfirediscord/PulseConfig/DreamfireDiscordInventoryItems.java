package com.dreamfirestudios.dreamfirediscord.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticEnumPulseConfig;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamfirediscord.Enum.InventoryItems;
import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@PulseAutoRegister
public class DreamfireDiscordInventoryItems extends StaticEnumPulseConfig<DreamfireDiscordInventoryItems, InventoryItems, ItemStack> {
    @Override
    public JavaPlugin mainClass() {return DreamfireDiscord.GetDreamfireDiscord();}
    @Override
    protected Class<InventoryItems> getKeyClass () {return InventoryItems.class;}
    @Override
    protected Class<ItemStack> getValueClass () {return ItemStack.class;}
    @Override
    protected ItemStack getDefaultValueFor (InventoryItems craftLegendsCoreInventoryItem) {return craftLegendsCoreInventoryItem.ReturnItemStack();}
}