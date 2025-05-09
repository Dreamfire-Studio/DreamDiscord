package com.dreamfirestudios.dreamfirediscord.SmartInvs;

import com.dreamfirestudios.dreamCore.DreamfireSmartInvs.ClickableItem;
import com.dreamfirestudios.dreamfirediscord.Enum.InventoryItems;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordInventoryItems;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordSerilizableItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class DreamfireDiscordSmartInvsItems {
    public static void SerializedItem(Player player, String itemID, Function<ItemStack, ItemStack> testFunction, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer) {
        DreamfireDiscordSerilizableItems.ReturnStaticAsync(DreamfireDiscordSerilizableItems.class, craftLegendsCoreSerilizableItems -> {
            var itemStack = testFunction.apply(craftLegendsCoreSerilizableItems.GetItemStack(itemID).clone());
            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }

    public static void InventoryItemWithFeedback(Player player, InventoryItems inventoryItems, Function<ItemStack, ItemStack> testFunction, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer) {
        DreamfireDiscordInventoryItems.ReturnStaticAsync(DreamfireDiscordInventoryItems.class, craftLegendsCoreInventoryItems -> {
            var itemStack = testFunction.apply(craftLegendsCoreInventoryItems.GetValue(inventoryItems).clone());
            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }

    public static void InventoryItem(Player player, InventoryItems inventoryItems, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer) {
        DreamfireDiscordInventoryItems.ReturnStaticAsync(DreamfireDiscordInventoryItems.class, craftLegendsCoreInventoryItems -> {
            var itemStack = craftLegendsCoreInventoryItems.GetValue(inventoryItems).clone();
            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }

    public static void SystemEnabled(Player player, boolean isEnabled, Consumer<ClickableItem> clickableItemConsumer, BiConsumer<Player, InventoryClickEvent> inventoryClickEventConsumer){
        DreamfireDiscordInventoryItems.ReturnStaticAsync(DreamfireDiscordInventoryItems.class, craftLegendsCoreInventoryItems -> {
            var itemStack = craftLegendsCoreInventoryItems.GetValue(InventoryItems.SystemEnabled).clone();
            clickableItemConsumer.accept(ClickableItem.of(itemStack, event -> inventoryClickEventConsumer.accept(player, event)));
        }, Throwable::printStackTrace);
    }
}
