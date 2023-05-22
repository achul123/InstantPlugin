package me.achul123;

import me.achul123.commands.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Utilities.defaultConfigSave();
        getCommand("ping").setExecutor(new ping(this));
        getCommand("skull").setExecutor(new Skull());
        getCommand("ipweather").setExecutor(new IpWeather(this));
        getCommand("invsee").setExecutor(new invsee(this));
        getCommand("fly").setExecutor(new fly());
        getCommand("ipreload").setExecutor(new IpReload());
        getCommand("seenip").setExecutor(new seenip());
        getCommand("modinfo").setExecutor(new modinfo());
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Le plugin est activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("Le plugin est désactivé !");
    }

    // message join
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean onJoinEnabled = config.getBoolean("join-quit-message.enabled");
        if (onJoinEnabled) {
            String message = config.getString("join-quit-message.join-message");
            if (message == null) {
                Bukkit.getLogger().severe("No join message was set in the config !!!!!!! DumbASS");
            }
            message = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);
            message = message.replace("&", "§");
            event.setJoinMessage(message);
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean onLeaveEnabled = config.getBoolean("join-quit-message.enabled");
        if (onLeaveEnabled) {
            String message = config.getString("join-quit-message.leave-message");
            if (message == null) {
                Bukkit.getLogger().severe("No leave message was set in the config !!!!!!! DumbASS");
                return;
            }
            message = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);
            message = message.replace("&", "§");
            event.setQuitMessage(message);
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean chatFormatEnabled = config.getBoolean("chat-message.enabled");
        if (chatFormatEnabled) {
            String tosend = config.getString("chat-message.playerchat");
            if (tosend == null) {
                Bukkit.getLogger().severe("No chat message was set in the config !!!!!!! DumbASS");
                return;
            }
            tosend = PlaceholderAPI.setPlaceholders(event.getPlayer(), tosend);
            tosend = tosend.replace("&", "§").replace("%message%", event.getMessage());
            tosend = tosend.replaceAll("\\%", "$0$0");
            event.setFormat(tosend);
        }
    }
}


