package me.achul123.commands;

import me.achul123.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.achul123.Utilities.config;
import static me.achul123.Utilities.parsePlaceholders;

public class IpWeather implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length > 1) return false;
        Player player = (Player) sender;
        String arg = args[0].toLowerCase();

        if (arg.equals("on")) {
            Bukkit.getWorlds().forEach(world -> {
                world.setStorm(true);
                world.setWeatherDuration(999999999);
            });
            player.sendMessage(parsePlaceholders(player, config.getString("ipweather-on")));
        } else if (arg.equals("off")) {
            Bukkit.getWorlds().forEach(world -> {
                world.setStorm(false);
                world.setWeatherDuration(999999999);
            });
            player.sendMessage(parsePlaceholders(player, config.getString("ipweather-off")));
        } return true;
    }
}
