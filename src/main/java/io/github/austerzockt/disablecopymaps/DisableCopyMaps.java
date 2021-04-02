package io.github.austerzockt.disablecopymaps;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableCopyMaps extends JavaPlugin implements Listener, CommandExecutor {
    public final String NBTTAG = "disable_copy";

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("disablecopy")) {
            if (sender instanceof Player && sender.hasPermission("disablecopy.command")) {
                Player p = (Player) sender;
                ItemStack item = p.getInventory().getItemInMainHand();
                if (!item.getType().equals(Material.FILLED_MAP)) {
                    p.sendMessage(ChatColor.RED + "Invalid Item");
                    return true;
                }
                NBTItem ni = new NBTItem(item, true);
                ni.setBoolean(NBTTAG, true);


            }
        }
        return true;
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        if (event.getInventory().getResult() == null) return;
        ItemStack item = event.getInventory().getResult();
        if (item.getType().equals(Material.FILLED_MAP)) {
            NBTItem nbtItem = new NBTItem(item, true);
            if (nbtItem.hasKey(NBTTAG) && nbtItem.getBoolean(NBTTAG)) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
            }

        }

    }
}



