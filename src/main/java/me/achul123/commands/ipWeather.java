package me.achul123.commands;

import me.achul123.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ipWeather implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = Utilities.configGet();
        Player player = (Player) sender;

        if (sender == null) {
            sender.sendMessage(Utilities.parsePlaceholders(player ,config.getString("only-players")));
            return true;
        }


        String arg = args[0].toLowerCase();

        if (arg.equals("on")) {
            Bukkit.getWorlds().forEach(world -> {
                world.setStorm(true);
                world.setWeatherDuration(0);
            });
            player.sendMessage(Utilities.parsePlaceholders(player ,config.getString("ipweather-on")));
        } else if (arg.equals("off")) {
            Bukkit.getWorlds().forEach(world -> {
                world.setStorm(false);
                world.setWeatherDuration(0);
            });
            player.sendMessage(Utilities.parsePlaceholders(player ,config.getString("ipweather-off")));
        }

        return true;
    }
}
