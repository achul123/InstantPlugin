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
            // /skull - Donne la tête du joueur qui a exécuté la commande
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack skull = createSkull(player.getName());
                if (skull != null) {
                    player.getInventory().addItem(skull);
                    player.sendMessage(config.getString("own-head").replace("&", "§"));
                } else {
                    player.sendMessage(config.getString("error-head").replace("&", "§"));
                }
            } else {
                sender.sendMessage(config.getString("only-players").replace("&", "§"));
            }
        } else if (args.length == 1) {
            // /skull {joueur} - Donne la tête du joueur spécifié
            String playerName = args[0];
            ItemStack skull = createSkull(playerName);
            if (skull != null) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.getInventory().addItem(skull);
                    player.sendMessage("Vous avez reçu la tête de " + playerName + " !");
                } else {
                    sender.sendMessage("La tête de " + playerName + " a été générée !");
                }
            } else {
                sender.sendMessage("Erreur lors de la création de la tête !");
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
