package dev.neonwave.keyall.PlaceholderAPI;

import dev.neonwave.keyall.KeyAll;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class KeyAllPlaceholder extends PlaceholderExpansion {
    private final KeyAll plugin;

    public KeyAllPlaceholder(KeyAll plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "keyall";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equals("timer")) {
            long timerIntervalInSeconds = plugin.timerInterval;
            long days = TimeUnit.SECONDS.toDays(timerIntervalInSeconds);
            long hours = TimeUnit.SECONDS.toHours(timerIntervalInSeconds) % 24;
            long minutes = TimeUnit.SECONDS.toMinutes(timerIntervalInSeconds) % 60;
            long seconds = timerIntervalInSeconds % 60;

            if (days > 0) {
                return String.format("%d:%02d:%02d:%02d", days, hours, minutes, seconds);
            } else if (hours > 0) {
                return String.format("%02d:%02d:%02d", hours, minutes, seconds);
            } else if (minutes > 0) {
                return String.format("%02d:%02d", minutes, seconds);
            } else {
                return String.format("%02d", seconds);
            }
        }
        return null;
    }
}

