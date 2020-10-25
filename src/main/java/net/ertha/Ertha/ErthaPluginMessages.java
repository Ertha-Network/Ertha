package net.ertha.Ertha;


import me.lucko.helper.messaging.Channel;
import me.lucko.helper.messaging.ChannelAgent;

public class ErthaPluginMessages
{
    private Ertha e;
    private Channel channel;

    ErthaPluginMessages(Ertha ertha){
        e = ertha;
        channel = ChannelAgent<T>.getChannel("ertha:bungee");
    }


}