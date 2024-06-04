package net.zaskamlen.itemsapi;

import net.zaskamlen.itemsapi.items.DefaultItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemsAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new DefaultItem(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
