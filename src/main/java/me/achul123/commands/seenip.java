package me.achul123.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class seenip implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Could not find player " + args[0]);
            return true;
        }

        String ip = target.getAddress().getAddress().getHostAddress();
        sender.sendMessage(ChatColor.GREEN + target.getName() + "'s IP address is " + ip);

        return true;
    }
}
