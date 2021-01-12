package com.github.fernthedev.askplaceholder;

import com.github.fernthedev.askplaceholder.placeholder.PlaceHolderAPIResponder;
import com.github.fernthedev.fernapi.server.spigot.FernSpigotAPI;
import com.github.fernthedev.fernapi.universal.Universal;

public final class AskPlaceholder extends FernSpigotAPI {

    @Override
    public void onEnable() {
        super.onEnable();
        // Plugin startup logic
        Universal.getMessageHandler().registerMessageHandler(new PlaceHolderAPIResponder());
    }

}
