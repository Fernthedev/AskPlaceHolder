package com.github.fernthedev.askplaceholder;

import com.github.fernthedev.askplaceholder.placeholder.PlaceHolderAPIResponder;
import com.github.fernthedev.askplaceholder.vanish.VanishResponder;
import com.github.fernthedev.fernapi.server.spigot.FernSpigotAPI;
import com.github.fernthedev.fernapi.universal.Universal;
import org.bukkit.ChatColor;

public final class AskPlaceholder extends FernSpigotAPI {

    @Override
    public void onEnable() {
        super.onEnable();
        Universal.setDebug(true);
        getLogger().info(ChatColor.GREEN + "Enabling AskPlaceHolder");
        // Plugin startup logic

        Universal.getMessageHandler().registerMessageHandler(new PlaceHolderAPIResponder());
        Universal.getMessageHandler().registerMessageHandler(new VanishResponder());
    }

}
