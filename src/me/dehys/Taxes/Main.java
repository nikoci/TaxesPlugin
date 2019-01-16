package me.dehys.Taxes;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        if(!setupEconomy()){ //Bro you don't have a fucking economy plugin? dafuq you doin esse?
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new Taxes(this); //Actually getting somewhere...

    }


    //Makes sure Vault and any kind of economy plugin is installed.
    private boolean setupEconomy()
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        return true;
    }
}
