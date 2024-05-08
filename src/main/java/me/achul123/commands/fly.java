package me.achul123.commands;

import jdk.internal.classfile.impl.Util;
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
        Player player = (Player) sender;
        if (sender == null) {
            sender.sendMessage(Utilities.parsePlaceholders(player ,config.getString("only-players")));
            return true;
        }

        if (!player.hasPermission("ip.fly")) {
            player.sendMessage(Utilities.parsePlaceholders(player ,config.getString("no-permission")));
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

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);
            String message = Utilities.parsePlaceholders(player ,config.getString("flying-disabled"));
            player.sendMessage(message);
        } else {
            target.setAllowFlight(true);
            target.setFlying(true);
            String message = Utilities.parsePlaceholders(player ,config.getString("flying-enabled"));
            player.sendMessage(message);
        }

        return true;
    }

}
