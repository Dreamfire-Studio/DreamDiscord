package com.dreamfirestudios.dreamfirediscord.DreamfireVariableTest;

import com.dreamfirestudios.dreamCore.DreamfireJava.PulseAutoRegister;
import com.dreamfirestudios.dreamCore.DreamfirePersistentData.PersistentDataTypes;
import com.dreamfirestudios.dreamCore.DreamfireVariable.DreamfireVariableTest;
import com.dreamfirestudios.dreamfirediscord.Enum.InventoryItems;

import java.util.ArrayList;
import java.util.List;

@PulseAutoRegister
public class DreamfireDiscordInventoryItemVariableTest implements DreamfireVariableTest {
    @Override
    public PersistentDataTypes PersistentDataType() { return PersistentDataTypes.STRING; }

    @Override
    public boolean IsType(Object variable) {
        try{
            var test = InventoryItems.valueOf(variable.toString());
            return true;
        }catch (IllegalArgumentException ignored){ return false; }
    }

    @Override
    public List<Class<?>> ClassTypes() {
        var data = new ArrayList<Class<?>>();
        data.add(InventoryItems.class);
        data.add(InventoryItems[].class);
        return data;
    }

    @Override
    public Object SerializeData(Object serializedData) {
        return serializedData.toString();
    }

    @Override
    public Object DeSerializeData(Object serializedData) {
        return InventoryItems.valueOf(serializedData.toString());
    }

    @Override
    public Object ReturnDefaultValue() { return InventoryItems.BlankTile; }

    @Override
    public List<String> TabData(List<String> list, String s) {
        var data = new ArrayList<String>();
        for(var x : InventoryItems.values()) if(x.name().contains(s)) data.add(x.name());
        return data;
    }
}