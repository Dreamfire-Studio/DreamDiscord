package com.dreamfirestudios.dreamfirediscord.Enum;

import com.dreamfirestudios.dreamfirediscord.DreamfireDiscord;

public enum Messages {
    ConsoleEnabledSystem(String.format("#7fff36%s has been enabled!", DreamfireDiscord.GetDreamfireDiscord().getName())),
    ConsoleDisableSystem(String.format("#7fff36%s has been disabled!", DreamfireDiscord.GetDreamfireDiscord().getName())),
    PlayerReloadedConfig(String.format("#7fff36%s configs have been reloaded!", DreamfireDiscord.GetDreamfireDiscord().getName())),
    PlayerSerlizedItem("#7fff36You have added a item to the serialized items: #ffffff%s"),
    PlayerResetConfig(String.format("#7fff36%s configs have been reset!", DreamfireDiscord.GetDreamfireDiscord().getName())),
    SystemIsntEnabled(String.format("#7fff36%s Isn't Enabled!", DreamfireDiscord.GetDreamfireDiscord().getName()));

    public final String message;
    Messages(String message){
        this.message = message;
    }
}
