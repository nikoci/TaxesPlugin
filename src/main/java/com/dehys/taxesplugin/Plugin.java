package com.dehys.taxesplugin;

import com.dehys.taxesplugin.taxing.Taxes;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private Economy economy;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        if(!setupEconomy()){
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new Taxes(this, economy);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (getServer().getPluginManager().getPlugin("Vault") == null || rsp == null) {
            return false;
        }
        this.economy = ((Economy)rsp.getProvider());
        return this.economy != null;
    }
}
