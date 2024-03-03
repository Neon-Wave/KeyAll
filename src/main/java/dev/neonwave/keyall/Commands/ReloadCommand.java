package dev.neonwave.keyall.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        if (cmd.equalsIgnoreCase("keyall") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {

            try {
                Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("KeyAll"));
                Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin("KeyAll"));
                if  (sender instanceof Player)  {
                    Player  player = (Player) sender;
                    player.sendMessage(ChatColor.GREEN + "KeyAll has been restarted");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
