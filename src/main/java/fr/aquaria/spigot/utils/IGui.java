package fr.aquaria.spigot.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public interface IGui {

    Inventory getInventory();

    String getName();

    IGui handleClick(Player player, ItemStack itemStack, InventoryView view);

    boolean isInventory(Inventory inventory);

}
