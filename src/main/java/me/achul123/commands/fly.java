package me.achul123.commands;

import me.achul123.Utilities;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = Utilities.configGet();
        // Vérifie si la commande a été exécutée par un joueur
        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getString("only-players").replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        // Vérifie si le joueur a la permission de voler
        if (!player.hasPermission("ip.fly")) {
            player.sendMessage(config.getString("no-permission").replace("&", "§"));
            return true;
        }

        // Vérifie si un joueur cible a été spécifié
        Player target = player;
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(config.getString("invalid-player").replace("&", "§"));
                return true;
            }
        }

        // Vérifie si le joueur est déjà en vol
        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);
            String message = config.getString("flying-disabled").replace("&", "§");
            message = PlaceholderAPI.setPlaceholders(target, message);
            player.sendMessage(message);
        } else {
            target.setAllowFlight(true);
            target.setFlying(true);
            String message = config.getString("flying-enabled").replace("&", "§");
            message = PlaceholderAPI.setPlaceholders(target, message);
            player.sendMessage(message);
        }

        return true;
    }

}
