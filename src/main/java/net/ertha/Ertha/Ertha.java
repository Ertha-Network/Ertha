package net.ertha.Ertha;

import org.bukkit.plugin.java.JavaPlugin;

import java.net.Socket;

public final class Ertha extends JavaPlugin
{
    SocketClient sc = new SocketClient(this);

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {
        getLogger().info( "<pluginName> driver was disabled!" );
    }


}