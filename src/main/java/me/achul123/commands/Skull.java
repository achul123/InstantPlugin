package me.achul123.commands;

import me.achul123.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skull implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = Utilities.configGet();
        if (args.length == 0) {
            Player player = null;
            if (sender instanceof Player) {
                player = (Player) sender;

                ItemStack skull = createSkull(player.getName());
                if (skull != null) {
                    player.getInventory().addItem(skull);
                    player.sendMessage(Utilities.parsePlaceholders(player, config.getString("own-head")));
                } else {
                    player.sendMessage(Utilities.parsePlaceholders(player, config.getString("error-head")));
                }
            } else {
                sender.sendMessage(Utilities.parsePlaceholders(player, config.getString("only-players")));
            }
        } else if (args.length == 1) {
            String playerName = args[0];
            ItemStack skull = createSkull(playerName);
            if (skull != null) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.getInventory().addItem(skull);
                    player.sendMessage("You have received the head of " + playerName + " !");
                } else {
                    sender.sendMessage("The head of" + playerName + " has been generated !");
                }
            } else {
                sender.sendMessage("Head creation error !");
            }
        } else {
            sender.sendMessage(config.getString("invalid-skull-use").replace("&", "§"));
        }
        return true;
    }

    private ItemStack createSkull(String playerName) {
        ItemStack skull = new ItemStack(org.bukkit.Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(playerName);
        skull.setItemMeta(skullMeta);
        return skull;
    }
}
