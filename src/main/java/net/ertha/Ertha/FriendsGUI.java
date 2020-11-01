package net.ertha.Ertha;

import net.ertha.packet.PlayerStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendsGUI implements Listener {
    private final Ertha e;

    FriendsGUI(Ertha ertha) {
        e = ertha;
    }

    ItemStack createFriendHead(String name) {
        OfflinePlayer op = Bukkit.getPlayer(name);
        ItemStack friendHead = new ItemStack(Material.PLAYER_HEAD, 1);

        SkullMeta meta = (SkullMeta) friendHead.getItemMeta();
        if (meta != null) {
            meta.setOwningPlayer(op);
            meta.setDisplayName(ChatColor.GREEN + (op != null ? op.getName() : name));
        }
        friendHead.setItemMeta(meta);
        return friendHead;
    }

    public void createGui(List<String> friends) {
        int noFriends = friends.size();
        List<ItemStack> friendList = new ArrayList<>();
        for (String friend:friends) {
            friendList.add(createFriendHead(friend));
        }
        List<Inventory> inventories = GUI.generateList(friendList, "Friends");
    }

    // You can call this whenever you want to put the items in
    public Inventory addPagination(Inventory inventory) {  //Add page number for pagination
        if (inventory.firstEmpty() <= 44 && inventory.getSize() == 54){
            inventory.setItem(45,GUI.backButton());
            inventory.setItem(46,GUI.toFirstButton());
            inventory.setItem(52,GUI.toLastButton());
            inventory.setItem(53,GUI.nextButton());
        }
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent inventoryClickEvent) {
        PlayerStorage player = getPlayer((Player) inventoryClickEvent.getWhoClicked());
        Inventory playerInventory = player.getGuiByIndex(player.getGuiIndex());
        if (inventoryClickEvent.getInventory() != playerInventory) return;

        inventoryClickEvent.setCancelled(true);

        final ItemStack clickedItem = inventoryClickEvent.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final Player p = (Player) inventoryClickEvent.getWhoClicked();

        switch (Objects.requireNonNull(inventoryClickEvent.getCurrentItem().getItemMeta()).getDisplayName()){
            case GUI.NEXT:
                nextPage(inventoryClickEvent);
                break;
            case GUI.PREV:
                prevPage(inventoryClickEvent);
                break;
            case GUI.FIRST:
                firstPage(inventoryClickEvent);
                break;
            case GUI.LAST:
                lastPage(inventoryClickEvent);
                break;
            default:
                clickedFriend(inventoryClickEvent);
        }
        // Using slots click is a best option for your inventory click's
        p.sendMessage("You clicked at slot " + inventoryClickEvent.getRawSlot());
    }


    private void nextPage(InventoryClickEvent inventoryClickEvent) {
        PlayerStorage playerStorage = getPlayer((Player) inventoryClickEvent.getWhoClicked());
        int index = playerStorage.getGuiIndex()!=null? playerStorage.getGuiIndex() : 0;
        if(playerStorage.getFriendsGUI() != null)
            if(playerStorage.getFriendsGUI().size() > index){
                inventoryClickEvent.getWhoClicked().openInventory(playerStorage.getGuiByIndex(index+1));
            }
    }

    private void prevPage(InventoryClickEvent inventoryClickEvent) {
        PlayerStorage playerStorage = getPlayer((Player) inventoryClickEvent.getWhoClicked());
        int index = playerStorage.getGuiIndex()!=null? playerStorage.getGuiIndex() : 0;
        if(playerStorage.getFriendsGUI() != null)
            if(0 < index){
                inventoryClickEvent.getWhoClicked().openInventory(playerStorage.getGuiByIndex(index-1));
            }
    }

    private void firstPage(InventoryClickEvent inventoryClickEvent) {
        PlayerStorage playerStorage = getPlayer((Player) inventoryClickEvent.getWhoClicked());
        int index = playerStorage.getGuiIndex()!=null? playerStorage.getGuiIndex() : 0;
        if(playerStorage.getFriendsGUI() != null)
            if( index > 0){
                inventoryClickEvent.getWhoClicked().openInventory(playerStorage.getGuiByIndex(0));
            }
    }

    private void lastPage(InventoryClickEvent inventoryClickEvent) {
        PlayerStorage playerStorage = getPlayer((Player) inventoryClickEvent.getWhoClicked());
        int index = playerStorage.getGuiIndex()!=null? playerStorage.getGuiIndex() : 0;
        if(playerStorage.getFriendsGUI() != null)
            if(playerStorage.getFriendsGUI().size() > index){
                inventoryClickEvent.getWhoClicked().openInventory(playerStorage.getGuiByIndex(playerStorage.getFriendsGUI().size()));
            }
    }

    private void clickedFriend(InventoryClickEvent inventoryClickEvent) {
        PlayerStorage playerStorage = getPlayer((Player) inventoryClickEvent.getWhoClicked());
        if(playerStorage.containsFriend(Objects.requireNonNull(Objects.requireNonNull(
                inventoryClickEvent.getCurrentItem()).getItemMeta()).getDisplayName())){
            ;
        }
    }

    private PlayerStorage getPlayer(Player player){
        e.loadPlayer(player);
        for (PlayerStorage p:e.playerStorages){
            if(p.getUuid().equals(player.getUniqueId())){
                return p;
            }
        }
        return new PlayerStorage(player);
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent inventoryDragEvent) {
        PlayerStorage playerStorage = getPlayer((Player) inventoryDragEvent.getWhoClicked());
        if (inventoryDragEvent.getInventory() == playerStorage.getGuiByIndex(playerStorage.getGuiIndex())) {
            inventoryDragEvent.setCancelled(true);
        }
    }
}