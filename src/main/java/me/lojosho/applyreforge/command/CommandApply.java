package me.lojosho.applyreforge.command;

import com.willfp.reforges.reforges.Reforge;
import com.willfp.reforges.reforges.Reforges;
import com.willfp.reforges.reforges.util.ReforgeUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandApply implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("applyreforge.use") || !sender.isOp()) return false;

        if (!(sender instanceof Player) && args.length < 2) {
            sender.sendMessage(Component.text("You must have a player name attached!"));
            return true;
        }

        Reforge reforge;
        Player player;
        ItemStack item;

        if (args.length == 1) {
            player = ((Player) sender).getPlayer();
            item = player.getInventory().getItemInMainHand();
            if (Reforges.getByKey(args[0]) != null) {
                reforge = Reforges.getByKey(args[0]);
                ReforgeUtils.setReforge(item, reforge);
            } else {
                sender.sendMessage(Component.text("Invalid Reforge"));
                return true;
            }
        }
        if (args.length == 2) {
            if (Reforges.getByKey(args[0]) != null) {
                reforge = Reforges.getByKey(args[0]);
            } else {
                sender.sendMessage(Component.text("Invalid Reforge"));
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(Component.text("Invalid Player"));
                return true;
            } else {
                player = Bukkit.getPlayer(args[1]);
                item = player.getInventory().getItemInMainHand();
                ReforgeUtils.setReforge(item, reforge);
                sender.sendMessage(Component.text("Successfully applied Reforge!"));
                return true;
            }
        }
        return false;
    }
}
