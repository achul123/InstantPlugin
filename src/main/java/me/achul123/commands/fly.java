package me.achul123.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.achul123.Utilities.config;
import static me.achul123.Utilities.parsePlaceholders;

public class fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length > 1) return false;
        Player player = (Player) sender;

        Player target = player;
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(parsePlaceholders(player, config.getString("invalid-player")));
             } return true;
        }

        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);
            player.sendMessage(parsePlaceholders(player, config.getString("flying-disabled")));
        } else {
            target.setAllowFlight(true);
            target.setFlying(true);
            player.sendMessage(parsePlaceholders(player, config.getString("flying-enabled")));
        } return true;
    }

}