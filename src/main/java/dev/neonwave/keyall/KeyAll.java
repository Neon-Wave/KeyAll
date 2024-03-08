package dev.neonwave.keyall;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.neonwave.keyall.Commands.ReloadCommand;
import dev.neonwave.keyall.PlaceholderAPI.KeyAllPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            timerInterval--;

            if (timerInterval < 5) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isOnline()) {
                        String message = config.getString("notifyKeyAll");
                        player.sendMessage(translateColorCodes(message));
                    }
                }
            }

            if (timerInterval < 10) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isOnline()) {
                        String message = config.getString("notifyKeyAll");
                        player.sendMessage(translateColorCodes(message));
                    }
                }
            }

            if (timerInterval < 15) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isOnline()) {
                        String message = config.getString("notifyKeyAll");
                        player.sendMessage(translateColorCodes(message));
                    }
                }
            }

            if (timerInterval <= 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isOnline()) {
                        String soundName = config.getString("sound", "ENTITY_EXPERIENCE_ORB_PICKUP");
                        Sound sound = Sound.valueOf(soundName);
                        player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
                    }
                }


                console.sendMessage(ChatColor.GREEN + "Congratulations! You've been awarded a key as part of our Key All event!");
                Bukkit.getScheduler().runTask(this, () -> {
                    final String command = config.getString("command");
                    if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
                        Bukkit.dispatchCommand(console, command);
                    } else {
                        getLogger().info("No players online. Command not executed.");
                    }
                });

                timerInterval = config.getInt("timerInterval", 60);
            }
        }, 20L, 20L);
    }

    private void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    private String translateColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
