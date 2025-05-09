package com.dreamfirestudios.dreamfirediscord.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticEnumPulseConfig;
import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamCore.DreamfireLuckPerms.DreamfireLuckPerms;
import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;
import com.dreamfirestudios.dreamfirediscord.Enum.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


@PulseAutoRegister
public class DreamfireDiscordPermissions extends StaticEnumPulseConfig<DreamfireDiscordPermissions, Permissions, String> {

    @Override
    public JavaPlugin mainClass() {return DreamfireDiscord.GetDreamfireDiscord();}
    @Override
    protected Class<Permissions> getKeyClass() {return Permissions.class;}
    @Override
    protected Class<String> getValueClass() {return String.class;}
    @Override
    protected String getDefaultValueFor(Permissions craftLegendsCorePermission) {return craftLegendsCorePermission.permission;}

    public boolean DoesPlayerHavePermission(Permissions permission, Player player, boolean sendError){
        if(permission == null || player == null) return false;
        var user = DreamfireLuckPerms.getUser(player);
        var permissionState = DreamfireLuckPerms.hasPermission(user, getDefaultValueFor(permission));
        if(!permissionState && sendError) DreamfireChat.SendMessageToPlayer(player, permission.error);
        return permissionState;
    }
}

