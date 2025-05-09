package com.dreamfirestudios.dreamfirediscord;

import com.dreamfirestudios.dreamCommand.DreamCommand;
import com.dreamfirestudios.dreamCore.DreamfireJava.DreamfireClassAPI;
import com.dreamfirestudios.dreamCore.DreamfireJava.DreamfireJavaAPI;
import com.dreamfirestudios.dreamfirediscord.API.DreamfireDiscordAPI;
import com.dreamfirestudios.dreamfirediscord.DreamfireVariableTest.DreamfireDiscordInventoryItemVariableTest;
import com.dreamfirestudios.dreamfirediscord.DreamfireVariableTest.DreamfireDiscordMessageVariableTest;
import com.dreamfirestudios.dreamfirediscord.DreamfireVariableTest.DreamfireDiscordPermissionVariableTest;
import com.dreamfirestudios.dreamfirediscord.Interface.IDreamfireDiscordBot;
import com.dreamfirestudios.dreamfirediscord.Objects.DiscordBotWrapper;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

public final class DreamfireDiscord extends JavaPlugin {
    private static DreamfireDiscord instance;
    public static DreamfireDiscord GetDreamfireDiscord(){return instance;}
    private final LinkedHashMap<String, DiscordBotWrapper> discordBotWrappers = new LinkedHashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        DreamfireClassAPI.RegisterPulseVariableTest(instance, new DreamfireDiscordMessageVariableTest());
        DreamfireClassAPI.RegisterPulseVariableTest(instance, new DreamfireDiscordInventoryItemVariableTest());
        DreamfireClassAPI.RegisterPulseVariableTest(instance, new DreamfireDiscordPermissionVariableTest());
        DreamfireDiscordAPI.DreamfireDiscordReloadConfigs();
        DreamfireClassAPI.RegisterClasses(this);
        DreamCommand.RegisterRaw(this);
    }

    @Override
    public void onDisable() {
        discordBotWrappers.values().forEach(DiscordBotWrapper::ShutdownConnection);
    }

    public static DiscordBotWrapper GetDiscordBotWrapper(IDreamfireDiscordBot iDreamfireDiscordBot){
        return instance.discordBotWrappers.getOrDefault(iDreamfireDiscordBot.getClass().getSimpleName(), null);
    }

    public void RegisterClasses(JavaPlugin javaPlugin){
        try { RegisterClassesRaw(javaPlugin); }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) { throw new RuntimeException(e);}
    }

    public void RegisterClassesRaw(JavaPlugin javaPlugin) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(var autoRegisterClass : DreamfireJavaAPI.getAutoRegisterClasses(javaPlugin)){
            if(IDreamfireDiscordBot.class.isAssignableFrom(autoRegisterClass)) RegisterDiscordBot((IDreamfireDiscordBot) autoRegisterClass.getConstructor().newInstance());
        }
    }

    private void RegisterDiscordBot(IDreamfireDiscordBot pulseDiscordBot){
        var storedDiscordBotWrapper = discordBotWrappers.getOrDefault(pulseDiscordBot.getClass().getSimpleName(), null);
        if(storedDiscordBotWrapper != null) return;
        discordBotWrappers.put(pulseDiscordBot.getClass().getSimpleName(), new DiscordBotWrapper(pulseDiscordBot));
    }
}