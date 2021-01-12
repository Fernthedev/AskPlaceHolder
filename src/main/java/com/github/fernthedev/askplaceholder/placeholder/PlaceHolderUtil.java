package com.github.fernthedev.askplaceholder.placeholder;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceHolderUtil {
    public static String setPlaceholders(Player player, String placeholderOld) {
        return PlaceholderAPI.setPlaceholders(player, placeholderOld);
    }
}
