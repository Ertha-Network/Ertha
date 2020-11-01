package net.ertha.Ertha;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GUI {

    public static final String NEXT = "Next Page";
    public static final String PREV = "Previous Page";
    public static final String FIRST = "First Page";
    public static final String LAST = "Last Page";

    public static Integer invSize(Integer noItems){
        double exp = noItems.doubleValue() / 9;
        double inv = Math.ceil(exp) * 9;
        return (int) inv;
    }

    public static Integer invCount(Integer noItems){
        double exp = noItems.doubleValue() / 45;
        return (int) Math.ceil(exp);
    }

    public static List<Inventory> generateList(List<ItemStack> items, String title){
        Queue<ItemStack> queue = new LinkedList<>(items);

        List<Inventory> list = new ArrayList<>();
        if (items.size() < 54){
            Inventory inventory = generate(items,title);
        }else {
            for (int i = 1; i < invCount(items.size()); i++) {
                Inventory inventory = Bukkit.createInventory(null, invSize(items.size()), title);
                for(int x = 0; x < 44; x++){
                    if(queue.isEmpty())break;
                    inventory.setItem(x,queue.poll());
                }
                list.add(inventory);
                if(queue.isEmpty())break;
            }
        }
        return list;
    }

    public static Inventory generate(List<ItemStack> items, String title){
        Inventory inventory = Bukkit.createInventory(null, invSize(items.size()), title);
        for (ItemStack item:items) {
            inventory.addItem(item);
        }
        return inventory;
    }

    @Deprecated
    public static ItemStack backButton(){
        ItemStack button = new ItemStack(Material.PLAYER_HEAD);
        Bukkit.getUnsafe().modifyItemStack(button,"SkullOwner:{Id:[I;1725013901,-1319550924,-1685700747,-1241102807],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY0Zjc3OWE4ZTNmZmEyMzExNDNmYTY5Yjk2YjE0ZWUzNWMxNmQ2NjllMTljNzVmZDFhN2RhNGJmMzA2YyJ9fX0=\"}]}}");
        ItemMeta meta = button.getItemMeta();
        assert meta != null;
        meta.setDisplayName(PREV);
        button.setItemMeta(meta);
        return button;
    }

    @Deprecated
    public static ItemStack nextButton(){
        ItemStack button = new ItemStack(Material.PLAYER_HEAD);
        Bukkit.getUnsafe().modifyItemStack(button,"SkullOwner:{Id:[I;-886489585,-502644074,-1943245271,-436273173],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDllY2NjNWMxYzc5YWE3ODI2YTE1YTdmNWYxMmZiNDAzMjgxNTdjNTI0MjE2NGJhMmFlZjQ3ZTVkZTlhNWNmYyJ9fX0=\"}]}}");
        ItemMeta meta = button.getItemMeta();
        assert meta != null;
        meta.setDisplayName(NEXT);
        button.setItemMeta(meta);
        return button;
    }

    @Deprecated
    public static ItemStack toFirstButton(){
        ItemStack button = new ItemStack(Material.PLAYER_HEAD);
        Bukkit.getUnsafe().modifyItemStack(button,"SkullOwner:{Id:[I;-1285014551,-625587517,-1090198550,607145256],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTE4YTJkZDViZWYwYjA3M2IxMzI3MWE3ZWViOWNmZWE3YWZlODU5M2M1N2E5MzgyMWU0MzE3NTU3MjQ2MTgxMiJ9fX0=\"}]}}");
        ItemMeta meta = button.getItemMeta();
        assert meta != null;
        meta.setDisplayName(FIRST);
        button.setItemMeta(meta);
        return button;
    }

    @Deprecated
    public static ItemStack toLastButton(){
        ItemStack button = new ItemStack(Material.PLAYER_HEAD);
        Bukkit.getUnsafe().modifyItemStack(button,"SkullOwner:{Id:[I;1250870935,120473079,-1281564568,-714819828],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDk5ZjI4MzMyYmNjMzQ5ZjQyMDIzYzI5ZTZlNjQxZjRiMTBhNmIxZTQ4NzE4Y2FlNTU3NDY2ZDUxZWI5MjIifX19\"}]}}");
        ItemMeta meta = button.getItemMeta();
        assert meta != null;
        meta.setDisplayName(LAST);
        button.setItemMeta(meta);
        return button;
    }
}
