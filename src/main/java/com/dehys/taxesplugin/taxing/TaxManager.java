package com.dehys.taxesplugin.taxing;

import com.dehys.taxesplugin.Plugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class TaxManager {
    private Economy economy;
    private Plugin plugin;
    public TaxManager(Plugin plugin, Economy economy){
        this.economy = economy;
        this.plugin = plugin;
    }

    public TaxManager taxPlayer(OfflinePlayer offlinePlayer, double taxPrice){
        Player player = (Player) offlinePlayer;
        economy.withdrawPlayer(offlinePlayer, taxPrice);

        player.sendMessage(ChatColor.GOLD + "[TAX] " + ChatColor.GREEN + "You were taxed " + plugin.getConfig().getDouble("taxRate") + "%. New balance: " + ChatColor.YELLOW + "$" + economy.getBalance(offlinePlayer));

        return this;
    }


    public TaxManager taxPlayersByAmount(List<OfflinePlayer> offlinePlayers, double taxPrice){
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            economy.withdrawPlayer(offlinePlayer, taxPrice);
        }
        return this;
    }

    public TaxManager taxPlayersByRate(List<OfflinePlayer> offlinePlayers, double taxRate){
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            double balance = economy.getBalance(offlinePlayer);
            economy.withdrawPlayer(offlinePlayer, (balance*taxRate));
        }
        return this;
    }




    private void appendMessage(String message){
    }
}
