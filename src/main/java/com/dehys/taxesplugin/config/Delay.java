package com.dehys.taxesplugin.config;

public class Delay extends Config{

    public int get(){
        return delay;
    }

    public Delay set(int i){
        delay = i;
        return this;
    }

    public void save(){
        plugin.getConfig().set("delay", delay);
        plugin.saveConfig();
    }

}
