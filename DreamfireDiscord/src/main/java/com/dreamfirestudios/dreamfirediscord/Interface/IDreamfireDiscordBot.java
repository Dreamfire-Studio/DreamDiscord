package com.dreamfirestudios.dreamfirediscord.Interface;

import com.dreamfirestudios.dreamfirediscord.Objects.DiscordBotWrapper;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.util.List;

public interface IDreamfireDiscordBot {
    String discordBotToken();
    Activity discordBotActivity();
    MemberCachePolicy discordMemberCachePolicy();
    List<GatewayIntent> discordBotIntents();
    boolean autoReconnect();
    default void onBotReady(DiscordBotWrapper discordBotWrapper){}
    default void onServerState(DiscordBotWrapper discordBotWrapper, boolean state){}
    default void onMessageReceived(boolean isBot, String channelID, String messageContent, String authorName){}
}
