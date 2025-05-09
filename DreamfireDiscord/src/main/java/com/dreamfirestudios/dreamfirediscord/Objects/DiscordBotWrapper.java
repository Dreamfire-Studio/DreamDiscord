package com.dreamfirestudios.dreamfirediscord.Objects;

import com.dreamfirestudios.dreamCore.DreamfireChat.DreamfireChat;
import com.dreamfirestudios.dreamfirediscord.Enum.CategorySearchType;
import com.dreamfirestudios.dreamfirediscord.Enum.TextChannelSearchType;
import com.dreamfirestudios.dreamfirediscord.Interface.IDreamfireDiscordBot;
import com.dreamfirestudios.dreamfirediscord.Listener.DiscordBotListener;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DiscordBotWrapper {
    private final JDA jda;
    private final IDreamfireDiscordBot iDreamfireDiscordBot;

    public DiscordBotWrapper(IDreamfireDiscordBot iDreamfireDiscordBot){
        jda = JDABuilder.createDefault(iDreamfireDiscordBot.discordBotToken())
                .addEventListeners(new DiscordBotListener(this))
                .setActivity(iDreamfireDiscordBot.discordBotActivity())
                .enableIntents(iDreamfireDiscordBot.discordBotIntents())
                .setMemberCachePolicy(iDreamfireDiscordBot.discordMemberCachePolicy())
                .setAutoReconnect(iDreamfireDiscordBot.autoReconnect())
                .build();
        this.iDreamfireDiscordBot = iDreamfireDiscordBot;
        iDreamfireDiscordBot.onServerState(this, true);
        DreamfireChat.SendMessageToConsole(String.format("&8Registered IDreamfireDiscordBot: %s", iDreamfireDiscordBot.getClass().getSimpleName()));
    }

    public IDreamfireDiscordBot GetIDreamfireDiscordBot(){
        return iDreamfireDiscordBot;
    }

    public Category getCategoryByNameOrId(String id, CategorySearchType categorySearchType) {
        if (categorySearchType == CategorySearchType.ID) return jda.getCategoryById(id);
        else  return jda.getCategoriesByName(id, true).stream().findFirst().orElse(null);
    }

    public TextChannel getTextChannelByName(String id, TextChannelSearchType textChannelSearchType) {
        if(textChannelSearchType == TextChannelSearchType.ID ) return jda.getTextChannelById(id);
        else return jda.getTextChannelsByName(id, true).stream().findFirst().orElse(null);
    }

    public void ShutdownConnection(){
        iDreamfireDiscordBot.onServerState(this,false);
        jda.shutdown();
    }
}
