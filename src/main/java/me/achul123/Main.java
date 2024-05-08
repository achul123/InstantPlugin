package me.achul123;

import me.achul123.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    @Override
    public void onEnable() {
        Utilities.defaultConfigSave();
        getCommand("ping").setExecutor(new ping());
        getCommand("skull").setExecutor(new Skull());
        getCommand("ipweather").setExecutor(new ipWeather());
        getCommand("invsee").setExecutor(new invsee(this));
        getCommand("fly").setExecutor(new fly());
        getCommand("ipreload").setExecutor(new ipReload());
        getCommand("seenip").setExecutor(new seenip());
        getCommand("modinfo").setExecutor(new modinfo());
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        getLogger().info("Plugin Enabled !");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled !");
    }
}


