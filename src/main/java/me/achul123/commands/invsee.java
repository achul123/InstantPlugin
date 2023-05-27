package me.achul123.commands;

import me.achul123.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import static me.achul123.Utilities.config;
import static me.achul123.Utilities.parsePlaceholders;

public class invsee implements CommandExecutor {
    private final Main main;

    public invsee(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length != 1) return false;


        Player target = Bukkit.getPlayer(args[0]);
        Player player = (Player) sender;
        if (target == null || !target.isOnline()) {
            player.sendMessage(parsePlaceholders(player, config.getString("player-not-online"))); return true; }

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
