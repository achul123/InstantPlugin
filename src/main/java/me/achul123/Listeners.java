package me.achul123;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean onJoinEnabled = config.getBoolean("join-quit-message.enabled");
        if (onJoinEnabled) {
            String message = Utilities.parsePlaceholders(event.getPlayer(), config.getString("join-quit-message.join-message"));
            if (message == null) {
                Bukkit.getLogger().severe("No join message was set in the config !");
                return;
            }
            event.setJoinMessage(message);
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean onLeaveEnabled = config.getBoolean("join-quit-message.enabled");
        if (onLeaveEnabled) {
            String message = Utilities.parsePlaceholders(event.getPlayer(), config.getString("join-quit-message.leave-message"));
            if (message == null) {
                Bukkit.getLogger().severe("No leave message was set in the config !");
                return;
            }
            event.setQuitMessage(message);
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean chatFormatEnabled = config.getBoolean("chat-message.enabled");
        if (chatFormatEnabled) {
            String tosend = Utilities.parsePlaceholders(event.getPlayer(), config.getString("chat-message.player-chat"));
            if (tosend == null) {
                Bukkit.getLogger().severe("No chat message was set in the config !");
                return;
            }
            tosend = tosend.replaceAll("\\%", "$0$0");
            event.setFormat(tosend);
        }
    }
}
