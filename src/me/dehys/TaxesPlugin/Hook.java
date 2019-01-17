package me.dehys.TaxesPlugin;

import me.dehys.TaxesPlugin.Taxing.Taxes;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Hook extends JavaPlugin {

    private Economy economy;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        if(!setupEconomy()){ //Bro you don't have a fucking economy plugin? dafuq you doin esse?
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new Taxes(this, economy); //Actually getting somewhere...
    }


    //Makes sure Vault and any kind of economy plugin is installed.
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (getServer().getPluginManager().getPlugin("Vault") == null || rsp == null) {
            return false;
        }
        this.economy = ((Economy)rsp.getProvider());
        return this.economy != null;
    }
}
