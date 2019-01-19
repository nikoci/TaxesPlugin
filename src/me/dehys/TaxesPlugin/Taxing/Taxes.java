package me.dehys.TaxesPlugin.Taxing;

import me.dehys.TaxesPlugin.Plugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class Taxes {
    private Plugin plugin;

    public Taxes(Plugin plugin, Economy economy){
        this.plugin = plugin;

        long updateRatio = plugin.getConfig().getLong("updateRatio");
        int delay = plugin.getConfig().getInt("delay");

        TaxManager tm = new TaxManager(plugin, economy);

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, (Runnable) new BukkitRunnable() {


            @Override
            public void run() {
                if(getCurrentState() >= delay){
                    resetCurrentState();
                    tm.taxPlayers(Arrays.asList(Bukkit.getServer().getOfflinePlayers()));
                }else{
                    addCurrentState();
                }
            }


        }.runTaskTimer(plugin, 0, updateRatio));
    }





    private void addCurrentState(){
        plugin.getConfig().set("currentState", plugin.getConfig().getInt("currentState")+1);
        plugin.saveConfig();
    }

    private void resetCurrentState(){
        plugin.getConfig().set("currentState", 0);
        plugin.saveConfig();
    }

    private int getCurrentState(){
        return plugin.getConfig().getInt("currentState");
    }
}
