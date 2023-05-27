package me.achul123.commands;

import me.achul123.Utilities;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skull implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length > 1) return false;
        Player executor = (Player) sender;
        String playerName;
        if (args.length == 1) playerName = args[0];
        else playerName=executor.getName();
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1 , (short)3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        // meta.addEnchant(Enchantment.KNOCKBACK, 31073, true);
        meta.setOwner(playerName);
        item.setItemMeta(meta);
        executor.getInventory().addItem(item);
        executor.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(executor, Utilities.config.getString("head-received").replace("%player_skull_name%", playerName))));
        return true;
    }
}