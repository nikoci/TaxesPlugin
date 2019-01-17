package me.dehys.TaxesPlugin.Taxing;

import me.dehys.TaxesPlugin.Config;
import me.dehys.TaxesPlugin.Hook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class Taxes {
    private Config con;


    public Taxes(Hook hook, Economy economy){
        this.con = new Config(hook);

        long updateRatio = con.updateRatio();
        int delay = con.delay();

        TaxManager tm = new TaxManager(hook, economy);

        hook.getServer().getScheduler().scheduleSyncDelayedTask(hook, (Runnable) new BukkitRunnable() {


            @Override
            public void run() {
                if(checkCurrentState() >= delay){
                    resetCurrentState();
                    tm.taxPlayers(Arrays.asList(Bukkit.getServer().getOfflinePlayers()));
                }else{
                    addCurrentState();
                }
            }


        }.runTaskTimer(hook, 0, updateRatio));
    }





    private void addCurrentState(){
        con.setCurrentState(con.currentState()+1);
    }

    private void resetCurrentState(){
        con.setCurrentState(0);
    }

    private int checkCurrentState(){
        return con.currentState();
    }
}
