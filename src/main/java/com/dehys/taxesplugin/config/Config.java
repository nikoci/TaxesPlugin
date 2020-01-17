package com.dehys.taxesplugin.config;

import com.dehys.taxesplugin.Plugin;

public class Config {

    static Plugin plugin;

    static int delay;
    static long updateRatio;
    static int taxRate;
    static boolean taxAmountEnabled;
    static double taxAmount;


    public Config(){
    }

    public void setup(Plugin plugin){
        Config.plugin = plugin;
        delay = plugin.getConfig().getInt("delay");
        updateRatio =  plugin.getConfig().getLong("updateRatio");
        taxRate =  plugin.getConfig().getInt("taxRate");
        taxAmountEnabled =  plugin.getConfig().getBoolean("taxAmountEnabled");
        taxAmount = plugin.getConfig().getDouble("taxAmount");
    }

    public void save(){
        plugin.getConfig().set("delay", delay);
        plugin.getConfig().set("updateRatio", updateRatio);
        plugin.getConfig().set("taxRate", taxRate);
        plugin.getConfig().set("taxAmountEnabled", taxAmountEnabled);
        plugin.getConfig().set("taxAmount", taxAmount);
        plugin.saveConfig();
    }

    public Delay delay(){
        return new Delay();
    }

    public UpdateRatio updateRatio(){
        return new UpdateRatio();
    }

    public TaxRate taxRate(){
        return new TaxRate();
    }

    public TaxAmount taxAmount(){
        return new TaxAmount();
    }


    /*
    public int currentState(){ return plugin.getConfig().getInt("currentState");}
    public void setCurrentState(int i){ plugin.getConfig().set("currentState", i); save();}

    public int taxTimes(){ return plugin.getConfig().getInt("taxTimes");}
    public void setTaxTimes(int i){ plugin.getConfig().set("taxTimes", i); save();}
     */
}
