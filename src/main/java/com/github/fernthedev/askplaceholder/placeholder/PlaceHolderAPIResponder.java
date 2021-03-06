package com.github.fernthedev.askplaceholder.placeholder;

import com.github.fernthedev.fernapi.universal.FernAPIChannels;
import com.github.fernthedev.fernapi.universal.Universal;
import com.github.fernthedev.fernapi.universal.data.network.Channel;
import com.github.fernthedev.fernapi.universal.data.network.PluginMessageData;
import com.github.fernthedev.fernapi.universal.handlers.PluginMessageHandler;
import com.github.fernthedev.fernapi.universal.util.ProxyAskPlaceHolder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PlaceHolderAPIResponder extends PluginMessageHandler {


    public PlaceHolderAPIResponder() {
        Universal.getLogger().info("Initiated PlaceHolderAPI plugin message listener");
    }

    /**
     * This is the channel name that will be registered incoming and outcoming
     *
     * @return The channels that will be incoming and outcoming
     */
    @Override
    public List<Channel> getChannels() {
        List<Channel> channels = new ArrayList<>();
        channels.add(FernAPIChannels.PlaceHolderBungeeChannel);

        return channels;
    }

    @Override
    public void onMessageReceived(PluginMessageData data, Channel channel) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PluginMessageData newData = new PluginMessageData(stream, "All", FernAPIChannels.PlaceHolderValue, FernAPIChannels.PlaceHolderBungeeChannel);

        Queue<String> dataQueue = data.getExtraDataQueue();

        String placeholderOld = dataQueue.remove(); //PLACEHOLDER
        String uuid = dataQueue.remove(); //UUID OF SENDER

        Player player = (Player) Universal.getMethods().convertFPlayerToPlayer(data.getPlayer());

        String placeholder = null;

        // Redundant?
        try {
            Class.forName("me.clip.placeholderapi.PlaceholderAPI");
        } catch (ClassNotFoundException e) {
            placeholder = ProxyAskPlaceHolder.PLACEHOLDER_API_NOT_ENABLE;
        }

        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceHolderAPI")) {
            placeholder = ProxyAskPlaceHolder.PLACEHOLDER_API_NOT_ENABLE;
        }

        if (placeholder == null) {
            placeholder = PlaceHolderUtil.setPlaceholders(player, placeholderOld);
            if (placeholder.equalsIgnoreCase(placeholderOld)) {
                placeholder = ProxyAskPlaceHolder.PLACEHOLDER_NOT_FOUND;
            }
        }

        newData.addData(placeholder);


        newData.addData(uuid);

        Universal.debug("Data Placeholder: " + newData);

        Universal.getMessageHandler().sendPluginData(newData);

        // Bukkit.getServer().sendPluginMessage(spigotAPI.getPlugin(spigotAPI.getClass()), FernAPIChannels.PlaceHolderBungeeChannel, stream.toByteArray());


    }
}
