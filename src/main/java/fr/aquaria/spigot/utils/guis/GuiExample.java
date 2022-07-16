package fr.aquaria.spigot.utils.guis;

import fr.aquaria.spigot.utils.IGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class GuiExample implements IGui {

    @Override
    public Inventory getInventory() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public IGui handleClick(Player player, ItemStack itemStack, InventoryView view) {
        return null;
    }

    @Override
    public boolean isInventory(Inventory inventory) {
        return false;
    }
}
