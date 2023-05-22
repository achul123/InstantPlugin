package me.achul123.commands;

import me.achul123.Main;
import me.achul123.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class invsee implements CommandExecutor {
    private final Main main;

    public invsee(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = Utilities.configGet();
        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getString("only-players").replace("&", "§"));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("usage : /invsee <joueur>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            sender.sendMessage(config.getString("player-not-online").replace("&", "§"));
            return true;
        }

        Player player = (Player) sender;

        Inventory targetInventory = target.getInventory();
        Inventory playerInventory = Bukkit.createInventory(null, 36, "Inventaire de " + target.getName());
        playerInventory.setContents(targetInventory.getContents());
        player.openInventory(playerInventory);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.getOpenInventory().getTitle().equals(playerInventory.getTitle())) {
                    this.cancel();
                    return;
                }

                targetInventory.setContents(playerInventory.getContents());
                target.updateInventory();
            }
        }.runTaskTimer(main, 0L, 1L);

        return true;
    }
}
