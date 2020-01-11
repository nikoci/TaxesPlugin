package com.dehys.taxesplugin.taxing;

import com.dehys.taxesplugin.Plugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

                    if(plugin.getConfig().getBoolean("taxAmountEnabled")){ //s
                        tm.taxPlayersByAmount(Arrays.asList(Bukkit.getOfflinePlayers()), plugin.getConfig().getDouble("taxAmount"));
                    }else{
                        tm.taxPlayersByRate(Arrays.asList(Bukkit.getOfflinePlayers()), plugin.getConfig().getDouble("taxRate") / 100);
                    }

                    plugin.getConfig().set("taxTimes" , plugin.getConfig().getInt("taxTimes")+1);
                    plugin.saveConfig();
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[TAX] " + ChatColor.GRAY + "Took" + ChatColor.RED + " -"+plugin.getConfig().getDouble("taxRate")+" " + ChatColor.GRAY + "from all registered players! " + ChatColor.GREEN + "#" + plugin.getConfig().getInt("taxTimes"));
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
