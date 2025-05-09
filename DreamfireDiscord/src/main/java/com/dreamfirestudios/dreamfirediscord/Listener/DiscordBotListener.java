package com.dreamfirestudios.dreamfirediscord.Listener;

import com.dreamfirestudios.dreamfirediscord.Interface.IDreamfireDiscordBot;
import com.dreamfirestudios.dreamfirediscord.Objects.DiscordBotWrapper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class DiscordBotListener extends ListenerAdapter {
    private final DiscordBotWrapper discordBotWrapper;

    public DiscordBotListener(DiscordBotWrapper discordBotWrapper){
        this.discordBotWrapper =discordBotWrapper;
    }

    @Override
    public void onReady(ReadyEvent event) {
        discordBotWrapper.GetIDreamfireDiscordBot().onBotReady(discordBotWrapper);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        var isBot = event.getAuthor().isBot();
        var messageContent = event.getMessage().getContentRaw();
        var authorName = event.getAuthor().getName();
        var channelID = event.getChannel().getId();
        discordBotWrapper.GetIDreamfireDiscordBot().onMessageReceived(isBot, channelID, messageContent, authorName);
    }
}
