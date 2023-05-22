package me.achul123.commands;

import me.achul123.Main;
import me.achul123.Utilities;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ping implements CommandExecutor {
    private final Main main;

    public ping(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = Utilities.configGet();
        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getString("only-players").replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        Player target = player;
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(config.getString("invalid-player").replace("&", "§"));
                return true;
            }
        }

        String message = config.getString("ping-message").replace("&", "§");
        message = PlaceholderAPI.setPlaceholders(target, message);
        player.sendMessage(message);


        return false;
    }
}
