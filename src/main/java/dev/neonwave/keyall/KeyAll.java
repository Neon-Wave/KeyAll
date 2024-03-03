package dev.neonwave.keyall;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.neonwave.keyall.Commands.ReloadCommand;
import dev.neonwave.keyall.PlaceholderAPI.KeyAllPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class KeyAll extends JavaPlugin implements Listener {
    public int timerInterval = 60;
    private int taskId;
    private ConsoleCommandSender console;

    public static YamlDocument config;

    @Override
    public void onEnable() {
        try {
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"));
            timerInterval = config.getInt("timerInterval", 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getCommand("keyall").setExecutor(new ReloadCommand());

        getServer().getPluginManager().registerEvents(this, this);

        console = Bukkit.getConsoleSender();

        startTimer();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new KeyAllPlaceholder(this).register();
        } else {
            getLogger().warning("PlaceholderAPI not found! Placeholder will not work.");
        }
    }

    @Override
    public void onDisable() {
        stopTimer();
    }

    private void startTimer() {
        final String keyName = config.getString("keyName");
        final String keyAmount = config.getString("keyAmount");

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            timerInterval--;

            if (timerInterval <= 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isOnline()) {
                        player.sendMessage(ChatColor.RED + "Timer reached 0!");
                    }
                }
                console.sendMessage(ChatColor.YELLOW + "Executing command: Command");
                Bukkit.getScheduler().runTask(this, () -> {
                    Bukkit.dispatchCommand(console, "excellentcrates key giveall " + keyName + " " + keyAmount);
                });

                timerInterval = config.getInt("timerInterval", 60);
            }
        }, 20L, 20L);
    }

    private void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
