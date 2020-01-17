package com.dehys.taxesplugin.config;

public class UpdateRatio extends Config{

    public long get(){
        return updateRatio;
    }

    public UpdateRatio set(long l){
        updateRatio = l;
        return this;
    }

    public void save(){
        plugin.getConfig().set("updateRatio", updateRatio);
        plugin.saveConfig();
    }

}
