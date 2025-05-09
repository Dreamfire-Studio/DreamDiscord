package com.dreamfirestudios.dreamfirediscord.PulseConfig;

import com.dreamfirestudios.dreamConfig.Abstract.StaticEnumPulseConfig;
import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireMessage;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;
import com.dreamfirestudios.dreamfirediscord.Enum.Messages;
import com.dreamfirestudios.dreamfirediscord.Enum.Permissions;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@PulseAutoRegister
public class DreamfireDiscordMessages extends StaticEnumPulseConfig<DreamfireDiscordMessages, Messages, String> {
    @Override
    public JavaPlugin mainClass() {return DreamfireDiscord.GetDreamfireDiscord();}
    @Override
    protected Class<Messages> getKeyClass() { return Messages.class;}
    @Override
    protected Class<String> getValueClass() {return String.class; }
    @Override
    protected String getDefaultValueFor(Messages craftLegendsCoreMessage) { return craftLegendsCoreMessage.message; }

    public void SendMessageToBroadcast(Messages message, Object... objects){
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects));
        DreamfireChat.BroadcastMessage(PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }

    public void SendMessageToPlayerPermission(Messages message, Permissions nexusCorePermission, Object... objects){
        DreamfireDiscordPermissions.ReturnStaticAsync(DreamfireDiscordPermissions.class, craftLegendsCorePermissions -> {
            for(var player : Bukkit.getOnlinePlayers()){
                if(!craftLegendsCorePermissions.DoesPlayerHavePermission(nexusCorePermission, player, false)) continue;
                SendMessageToPlayer(message, player, objects);
            }
        }, Throwable::printStackTrace);
    }

    public void SendMessageToPlayer(Messages message, Player player, Object... objects){
        if(player == null || message == null) return;
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects), player);
        DreamfireChat.SendMessageToPlayer(player, PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }

    public void SendMessageToContext(Messages message, Player player, ConversationContext conversationContext, Object... objects){
        if(player == null || message == null) return;
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects), player);
        conversationContext.getForWhom().sendRawMessage(PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }

    public void SendMessageToConsole(Messages message, Object... objects){
        if(message == null) return;
        var storedMessage = getDefaultValueFor(message);
        if(storedMessage.isEmpty()) return;
        var cleanedMessage = DreamfireMessage.formatMessage(String.format(storedMessage, objects));
        DreamfireChat.SendMessageToConsole(PlainTextComponentSerializer.plainText().serialize(cleanedMessage));
    }
}
