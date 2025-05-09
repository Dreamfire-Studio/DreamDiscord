
package com.dreamfirestudios.dreamfirediscord.ServerCommand;

import com.dreamfirestudios.dreamCommand.Interface.PCMethod;
import com.dreamfirestudios.dreamCommand.Interface.PCSignature;
import com.dreamfirestudios.dreamCommand.ServerCommand;
import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamfirediscord.API.DreamfireDiscordAPI;
import com.dreamfirestudios.dreamfirediscord.Enum.Messages;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordMessages;
import com.dreamfirestudios.dreamfirediscord.PulseConfig.DreamfireDiscordConfig;
import org.bukkit.command.CommandSender;

import java.util.LinkedHashMap;

@PulseAutoRegister
public class DreamfireDiscordServerCommand extends ServerCommand {

    public DreamfireDiscordServerCommand() {
        super("dreamfirediscord", false);
    }

    @Override
    public void NoMethodFound(CommandSender commandSender, String s, String[] strings) {}
    @Override
    public String helpMenuPrefix(CommandSender commandSender) {return "";}
    @Override
    public LinkedHashMap<String, String> helpMenuFormat(CommandSender commandSender, LinkedHashMap<String, String> linkedHashMap) {return new LinkedHashMap<>();}
    @Override
    public String helpMenuSuffix(CommandSender commandSender) {return "";}

    @PCMethod
    @PCSignature({})
    public void TimeStealCoreMethod(CommandSender commandSender){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToConsole(Messages.SystemIsntEnabled);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"enable"})
    public void TimeStealCoreEnableMethod(CommandSender commandSender, boolean state){
        DreamfireDiscordAPI.DreamfireDiscordEnableSystem(state);
        DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
            craftLegendsCoreMessages.SendMessageToConsole(state ? Messages.ConsoleEnabledSystem : Messages.ConsoleDisableSystem);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reset"})
    public void TimeStealCoreConfigsResetMethod(CommandSender commandSender){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireDiscordAPI.DreamfireDiscordResetConfigs();
            DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToConsole(Messages.PlayerResetConfig);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }

    @PCMethod
    @PCSignature({"configs", "reload"})
    public void TimeStealCoreConfigsReloadMethod(CommandSender commandSender){
        DreamfireDiscordConfig.ReturnStaticAsync(DreamfireDiscordConfig.class, craftLegendsCoreConfig -> {
            if(!craftLegendsCoreConfig.systemEnabled) return;
            DreamfireDiscordAPI.DreamfireDiscordResetConfigs();
            DreamfireDiscordMessages.ReturnStaticAsync(DreamfireDiscordMessages.class, craftLegendsCoreMessages -> {
                craftLegendsCoreMessages.SendMessageToConsole(Messages.PlayerReloadedConfig);
            }, Throwable::printStackTrace);
        }, Throwable::printStackTrace);
    }
}

