package me.achul123.commands;

import me.achul123.Main;
import me.achul123.Utilities;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class modinfo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            return false;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
        if (offlinePlayer == null || !offlinePlayer.hasPlayedBefore()) {
            sender.sendMessage(ChatColor.RED + "Impossible to find the player " + args[0]);
            return true;
        }

        String name = offlinePlayer.getName();
        sender.sendMessage("Checking user profile...");
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(Main.class), () -> {
            try {
                URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(2000);
                connection.setReadTimeout(2000);
                boolean isPremium = false;
                if (connection.getResponseCode() == 200) {
                    isPremium = true;
                }
                else {
                    isPremium = false;
                }

                String premiumStatus = isPremium ? "yes" : "no";
                YamlConfiguration config = Utilities.configGet();
                List<String> lines = config.getStringList("modinfo");
                if (lines == null) {
                    sender.sendMessage("Config is not valid");
                    return;
                }
                String message = new String();
                for (String line : lines){
                    message = message.concat(PlaceholderAPI.setPlaceholders(offlinePlayer, line).replace("%premium%", premiumStatus).replace("&", "§") + "\n");
                }
                message = message.substring(0, message.length()-1);
                sender.sendMessage(message);
            } catch (IOException e) {
                sender.sendMessage("Error checking if the user is premium");
                throw new RuntimeException(e);
            }
        });
        return true;
    }
}
