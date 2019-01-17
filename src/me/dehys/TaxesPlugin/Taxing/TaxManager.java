package me.dehys.TaxesPlugin.Taxing;

import me.dehys.TaxesPlugin.Config;
import me.dehys.TaxesPlugin.Hook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class TaxManager {
    private Economy economy;
    private Config con;
    public TaxManager(Hook hook, Economy economy){
        this.economy = economy;
        this.con = new Config(hook);
    }




    public boolean taxPlayer(OfflinePlayer offlinePlayer){
        Player player = (Player) offlinePlayer;
        if(con.taxAmountEnabled()){
            economy.withdrawPlayer(offlinePlayer, con.taxAmount());
        }else{
            economy.withdrawPlayer(offlinePlayer, economy.getBalance(offlinePlayer)*con.taxRate());
        }
        return true;
    }


    public boolean taxPlayers(List<OfflinePlayer> offlinePlayers){ //TaxPlayers with default value
        if(con.taxAmountEnabled()){
            taxPlayers(offlinePlayers, con.taxAmount());
        }else{
            taxPlayers(offlinePlayers, con.taxRate());
        }
        return true;
    }


    private boolean taxPlayers(List<OfflinePlayer> offlinePlayers, int taxRate){
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            double balance = economy.getBalance(offlinePlayer);
            economy.withdrawPlayer(offlinePlayer, (balance*taxRate));
        }
        con.setTaxTimes(con.taxTimes()+1);
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[TAX] " + ChatColor.GRAY + "Took" + ChatColor.RED + " -"+con.taxRate()*100+" " + ChatColor.GRAY + "from all registered players! " + ChatColor.GREEN + "#" + con.taxTimes());
        return true;
    }


    private boolean taxPlayers(List<OfflinePlayer> offlinePlayers, double taxAmount){
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            economy.withdrawPlayer(offlinePlayer, taxAmount);
        }
        con.setTaxTimes(con.taxTimes()+1);
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[TAX] " + ChatColor.GRAY + "Took" + ChatColor.RED + " -"+con.taxRate()*100+" " + ChatColor.GRAY + "from all registered players! " + ChatColor.GREEN + "#" + con.taxTimes());
        return true;
    }
}
