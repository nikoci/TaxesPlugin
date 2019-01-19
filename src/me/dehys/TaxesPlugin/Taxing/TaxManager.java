package me.dehys.TaxesPlugin.Taxing;

import me.dehys.TaxesPlugin.Plugin;
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




    public boolean taxPlayer(OfflinePlayer offlinePlayer){
        Player player = (Player) offlinePlayer;
        if(plugin.getConfig().getBoolean("taxAmountEnabled")){
            economy.withdrawPlayer(offlinePlayer, plugin.getConfig().getDouble("taxAmount"));
        }else{
            economy.withdrawPlayer(offlinePlayer, (economy.getBalance(offlinePlayer)*(plugin.getConfig().getDouble("taxRate") / 100)));
        }

        player.sendMessage(ChatColor.GOLD + "[TAX] " + ChatColor.GREEN + "You were taxed " + plugin.getConfig().getDouble("taxRate") + "%. New balance: " + ChatColor.YELLOW + "$" + economy.getBalance(offlinePlayer));

        return true;
    }


    public boolean taxPlayers(List<OfflinePlayer> offlinePlayers){ //TaxPlayers with default value
        if(plugin.getConfig().getBoolean("taxAmountEnabled")){
            taxPlayersByAmount(offlinePlayers, plugin.getConfig().getDouble("taxAmount"));
        }else{
            taxPlayersByRate(offlinePlayers, plugin.getConfig().getDouble("taxRate") / 100);
        }
        return true;
    }






    private boolean taxPlayersByAmount(List<OfflinePlayer> offlinePlayers, double taxAmount){
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            economy.withdrawPlayer(offlinePlayer, taxAmount);
        }
        update();
        return true;
    }

    private boolean taxPlayersByRate(List<OfflinePlayer> offlinePlayers, double taxRate){
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            double balance = economy.getBalance(offlinePlayer);
            economy.withdrawPlayer(offlinePlayer, (balance*taxRate));
        }
        update();
        return true;
    }





    private boolean update(){
        plugin.getConfig().set("taxTimes" , plugin.getConfig().getInt("taxTimes")+1);
        plugin.saveConfig();
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[TAX] " + ChatColor.GRAY + "Took" + ChatColor.RED + " -"+plugin.getConfig().getDouble("taxRate")+" " + ChatColor.GRAY + "from all registered players! " + ChatColor.GREEN + "#" + plugin.getConfig().getInt("taxTimes"));
        return true;
    }
}
