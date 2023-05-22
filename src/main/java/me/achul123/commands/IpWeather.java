package me.achul123.commands;

import me.achul123.Main;
import me.achul123.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class IpWeather implements CommandExecutor {
    private final Main main;

    public IpWeather(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Utilities.configGet();

        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getString("only-players").replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "usage : /ipweather <on/off>");
            return true;
        }

        String arg = args[0].toLowerCase();

        if (arg.equals("on")) {
            Bukkit.getWorlds().forEach(world -> {
                world.setStorm(true);
                world.setWeatherDuration(0);
            });
            player.sendMessage(config.getString("ipweather-on").replace("&", "§"));
        } else if (arg.equals("off")) {
            Bukkit.getWorlds().forEach(world -> {
                world.setStorm(false);
                world.setWeatherDuration(0);
            });
            player.sendMessage(config.getString("ipweather-off").replace("&", "§"));
        } else {
            player.sendMessage(ChatColor.RED + "usage : /ipweather <on/off>");
        }

        return true;
    }
}
