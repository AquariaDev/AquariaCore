package fr.aquaria.spigot.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GUI {

    private Map<Player, IGui> playerGUIMap = new HashMap<>();

    public IGui getOpenGui(Player player) {
        return playerGUIMap.get(player);
    }

    public void setGui(Player player, IGui gui) {
        playerGUIMap.put(player, gui);
        player.openInventory(gui.getInventory());
    }

}
