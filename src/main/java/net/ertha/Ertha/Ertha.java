package net.ertha.Ertha;

import me.lucko.helper.plugin.ExtendedJavaPlugin;

import org.bukkit.Bukkit;

public final class Ertha extends ExtendedJavaPlugin
{
    ErthaPluginMessages epm = new ErthaPluginMessages(this);

    @Override
    public void enable()
    {

    }

    @Override
    public void disable()
    {
        getLogger().info( "<pluginName> driver was disabled!" );
    }


}