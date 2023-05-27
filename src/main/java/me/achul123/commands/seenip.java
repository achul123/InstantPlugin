package me.achul123.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.achul123.Utilities.config;
import static me.achul123.Utilities.parsePlaceholders;

public class seenip implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player  = (Player) sender;
        if (!(sender instanceof Player) || args.length != 1) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(parsePlaceholders(player, config.getString("invalid-player"))); return true; }

        String ip = target.getAddress().getAddress().getHostAddress();
        player.sendMessage(parsePlaceholders(player, config.getString("ip-seen")).replace("%ip%", ip));

        return true;
    }
}
