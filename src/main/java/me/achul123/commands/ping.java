package me.achul123.commands;

import me.achul123.Utilities;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = Utilities.configGet();
        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage(Utilities.parsePlaceholders(player ,config.getString("only-players")));
            return true;
        }

        Player target = player;
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Utilities.parsePlaceholders(player ,config.getString("invalid-player")));
                return true;
            }
        }

        player.sendMessage(Utilities.parsePlaceholders(player ,config.getString("ping-message")));
        return false;
    }
}
