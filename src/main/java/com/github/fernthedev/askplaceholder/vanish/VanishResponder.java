package com.github.fernthedev.askplaceholder.vanish;

import com.github.fernthedev.fernapi.universal.FernAPIChannels;
import com.github.fernthedev.fernapi.universal.Universal;
import com.github.fernthedev.fernapi.universal.data.network.Channel;
import com.github.fernthedev.fernapi.universal.data.network.PluginMessageData;
import com.github.fernthedev.fernapi.universal.handlers.PluginMessageHandler;
import com.github.fernthedev.fernapi.universal.util.ProxyAskPlaceHolder;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class VanishResponder extends PluginMessageHandler {

    private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

    /**
     * This is the channel name that will be registered incoming and outgoing
     * This is where you specify the channels you want to listen to
     * Just make a new {@link ArrayList} with Channel instance instance and add an instance of the channel accordingly.
     *
     * @return The channels that will be incoming and outgoing
     * @see ProxyAskPlaceHolder as an example
     */
    @Override
    public List<Channel> getChannels() {
        return Collections.singletonList(FernAPIChannels.VANISH_CHANNEL);
    }

    /**
     * The event called when message is received from the channels registered
     *
     * @param data    The dataInfo received for use of the event.
     * @param channel The channel it was received from, for use of multiple channels in one listener
     */
    @Override
    public void onMessageReceived(PluginMessageData data, Channel channel) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PluginMessageData newData = new PluginMessageData(stream, "All", FernAPIChannels.VANISH_SUBCHANNEL, FernAPIChannels.VANISH_CHANNEL);

        Queue<String> dataQueue = data.getExtraDataQueue();
        String uuid = dataQueue.remove(); //UUID OF SENDER
        Player player = (Player) Universal.getMethods().convertFPlayerToPlayer(data.getPlayer());


        newData.addData(uuid);

        newData.addData(String.valueOf(isVanished(player)));

        Universal.debug("Vanish response: " + isVanished(player));

        Universal.getMessageHandler().sendPluginData(newData);

        // Bukkit.getServer().sendPluginMessage(spigotAPI.getPlugin(spigotAPI.getClass()), FernAPIChannels.PlaceHolderBungeeChannel, stream.toByteArray());


    }
}
