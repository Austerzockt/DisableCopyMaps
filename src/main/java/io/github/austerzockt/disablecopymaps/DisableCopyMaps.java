package io.github.austerzockt.disablecopymaps;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableCopyMaps extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        if (event.getInventory().getResult() == null) return;
        if(event.getInventory().getResult().getType().equals(Material.FILLED_MAP)) {
            event.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }
}
