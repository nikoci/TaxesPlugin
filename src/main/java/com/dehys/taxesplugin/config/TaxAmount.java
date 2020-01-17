package com.dehys.taxesplugin.config;

public class TaxAmount extends Config{

    public double get(){
        return taxAmount;
    }

    public TaxAmount set(double d){
        taxAmount = d;
        return this;
    }

    public boolean isEnabled(){
        return taxAmountEnabled;
    }

    public TaxAmount enable(){
        taxAmountEnabled = true;
        return this;
    }

    public void save(){
        plugin.getConfig().set("taxAmount", taxAmount);
        plugin.getConfig().set("taxAmountEnabled", taxAmountEnabled);
        plugin.saveConfig();
    }

}
