package net.ertha.Ertha;

import net.ertha.packet.PlayerStorage;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Ertha extends JavaPlugin
{
    SocketClient sc = new SocketClient(this);
    FriendsGUI friendsGUI = new FriendsGUI(this);

    List<PlayerStorage> playerStorages = new ArrayList<>();

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {
        getLogger().info( "<pluginName> driver was disabled!" );
    }

    public void loadPlayer(org.bukkit.entity.Player player){
        for (PlayerStorage p: playerStorages) {
            if(p.getUuid().equals(player.getUniqueId())){
                return;
            }
        }
        playerStorages.add(new PlayerStorage(player));
    }

}