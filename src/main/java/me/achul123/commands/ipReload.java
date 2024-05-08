package me.achul123.commands;

import me.achul123.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ipReload implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utilities.loadConfig();
        sender.sendMessage("Plugin Reloaded");
        return true;
    }
}
