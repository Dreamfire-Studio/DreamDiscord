package com.dreamfirestudios.dreamfirediscord.PlayerCommand;

import com.dreamfirestudios.dreamCommand.Enums.TabType;
import com.dreamfirestudios.dreamCommand.Interface.PCAutoTab;
import com.dreamfirestudios.dreamCommand.Interface.PCMethod;
import com.dreamfirestudios.dreamCommand.Interface.PCSignature;
import com.dreamfirestudios.dreamCommand.Interface.PCTab;
import com.dreamfirestudios.dreamCommand.PlayerCommand;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamfirediscord.API.DreamfireDiscordAPI;
import com.dreamfirestudios.dreamfirediscord.Enum.Messages;
import com.dreamfirestudios.dreamfirediscord.Enum.Permissions;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordConfig;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordMessages;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordPermissions;
import com.dreamfirestudios.dreamfirediscord.SmartInvs.DreamfireDiscordCoreMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.UUID;

@PulseAutoRegister
public class DreamfireDiscordPlayerCommand extends PlayerCommand {
    public DreamfireDiscordPlayerCommand() {
        super("dreamfirediscord", false);
    }
    @Override
    public void NoMethodFound(Player player, String s, String[] strings) {}
    @Override
    public String helpMenuPrefix(Player player) {
        return "";
    }
    @Override
    public LinkedHashMap<String, String> helpMenuFormat(Player player, LinkedHashMap<String, String> linkedHashMap) {return new LinkedHashMap<>();}
    @Override
    public String helpMenuSuffix(Player player) {
        return "";
    }

    @PCMethod
    @PCSignature({})
    public void TimeStealCoreMethod(UUID playerID){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireDiscordPermissions.ReturnStaticAsync(DreamfireDiscordPermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(Permissions.AdminConsole, player, true)) return;
            new DreamfireDiscordCoreMenu(player);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"enable"})
    @PCAutoTab(pos = 1)
    public void TimeStealCoreEnableMethod(UUID playerID, boolean state){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireDiscordPermissions.ReturnStaticAsync(DreamfireDiscordPermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(Permissions.EnableSystem, player, true)) return;
            DreamfireDiscordAPI.DreamfireDiscordEnableSystem(state);
            DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToPlayer(state ? Messages.ConsoleEnabledSystem : Messages.ConsoleDisableSystem, player);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"serialize"})
    @PCTab(pos = 1, type = TabType.PureData, data = "ITEM ID")
    public void TimeStealCoreSerializeItemMethod(UUID playerUUID, String itemName){
        var player = Bukkit.getPlayer(playerUUID);
        if(player == null) return;
        DreamfireDiscordPermissions.ReturnStaticAsync(DreamfireDiscordPermissions.class, craftLegendsCorePermissions -> {
            if(!craftLegendsCorePermissions.DoesPlayerHavePermission(Permissions.SerializeItem, player, true)) return;
            DreamfireDiscordAPI.SerializeItem(itemName, player.getInventory().getItemInMainHand());
            DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToPlayer(Messages.PlayerSerlizedItem, player, itemName);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reset"})
    public void TimeStealCoreConfigsResetMethod(UUID playerID){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireDiscordPermissions.ReturnStaticAsync(DreamfireDiscordPermissions.class, craftLegendsCorePermissions -> {
                if(!craftLegendsCorePermissions.DoesPlayerHavePermission(Permissions.ResetConfigs, player, true)) return;
                DreamfireDiscordAPI.DreamfireDiscordResetConfigs();
                DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
                    craftLegendsCoreMessages.SendMessageToPlayer(Messages.PlayerResetConfig, player);
                }, Throwable::printStackTrace);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reload"})
    public void TimeStealCoreReloadMethod(UUID playerID){
        var player = Bukkit.getPlayer(playerID);
        if(player == null) return;
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireDiscordPermissions.ReturnStaticAsync(DreamfireDiscordPermissions.class, craftLegendsCorePermissions -> {
                if(!craftLegendsCorePermissions.DoesPlayerHavePermission(Permissions.ReloadConfigs, player, true)) return;
                DreamfireDiscordAPI.DreamfireDiscordResetConfigs();
                DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
                    craftLegendsCoreMessages.SendMessageToPlayer(Messages.PlayerReloadedConfig, player);
                }, Throwable::printStackTrace);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }
}
