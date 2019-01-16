/*package me.dehys.Taxes;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDate;
import java.time.LocalTime;

public class m extends JavaPlugin {
    private Economy econ;
    private long currentTime;

    public void onEnable()
    {
        if (!setupEconomy())
        {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new BukkitRunnable()
        {
            public void run()
            {
                LocalDate current1 = LocalDate.now();
                LocalTime current2 = LocalTime.now();

                double Times = m.this.getConfig().getDouble("Times");
                m.this.getConfig().set("Times", Double.valueOf(++Times));
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[TAX] " + ChatColor.GRAY + "Took" + ChatColor.RED + " -20% " + ChatColor.GRAY + "from all registered players! " + ChatColor.GREEN + "#" + Times);
                OfflinePlayer[] arrayOfOfflinePlayer;
                int j = (arrayOfOfflinePlayer = Bukkit.getOfflinePlayers()).length;
                for (int i = 0; i < j; i++)
                {
                    OfflinePlayer offplayer = arrayOfOfflinePlayer[i];
                    double balance = m.this.econ.getBalance(offplayer);
                    double total = balance * 0.2D;

                    m.this.econ.withdrawPlayer(offplayer, total);
                    m.this.getConfig().set("lastTaken", current1.getDayOfYear() + "(" + current1.getDayOfWeek() + ")" + "  -  " + current2.getHour() + ":" + current2.getMinute());
                }
            }
        }.runTaskTimer(this, 0L, 1728000L);
        this.currentTime = ((Long)getConfig().get("currentTime")).longValue();
    }

    private boolean setupEconomy()
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        this.econ = ((Economy)rsp.getProvider());
        return this.econ != null;
    }

    public void onDisable()
    {
        getConfig().set("currentTime", Long.valueOf(System.currentTimeMillis()));
        saveConfig();
    }

    public boolean onCommand(CommandSender cs, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player)cs;
        if (!(cs instanceof Player))
        {
            cs.sendMessage(ChatColor.RED + "Only players can use this!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("taxes"))
        {
            cs.sendMessage(ChatColor.GOLD + "[Taxes] " + ChatColor.WHITE + "information:");
            cs.sendMessage(ChatColor.GRAY + "Tax-Rate: " + ChatColor.YELLOW + "20%");
            cs.sendMessage(ChatColor.GRAY + "Interval: " + ChatColor.YELLOW + "Monthly");
            cs.sendMessage(ChatColor.GRAY + "Income: " + ChatColor.YELLOW + "null");
        }
        return true;
    }
}
*/