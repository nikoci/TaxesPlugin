package com.dehys.taxesplugin.config;

public class TaxRate extends Config{

    public int get(){
        return taxRate;
    }

    public TaxRate set(int i){
        taxRate = i;
        return this;
    }

    public void save(){
        plugin.getConfig().set("taxRate", taxRate);
        plugin.saveConfig();
    }

}
