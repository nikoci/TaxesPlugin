package me.dehys.Taxes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Taxes {
    private Main main;
    private FileConfiguration config = main.getConfig();

    private long delay;

    public Taxes(Main main){
        this.main = main;



        main.getServer().getScheduler().scheduleSyncDelayedTask(main, (Runnable) new BukkitRunnable() {
            int seconds = 0;

            @Override
            public void run() {
                Bukkit.getServer().broadcastMessage(ChatColor.GRAY+(seconds+"")+ChatColor.GREEN+" second.");
                seconds++;
            }
        }.runTaskTimer(main, 0, 20));
    }


    private boolean taxPlayer(OfflinePlayer player){

        return true;
    }

    private boolean taxPlayers(List<OfflinePlayer> players){
        for(OfflinePlayer player : players){

        }
        return true;
    }
}
