package fr.aquaria.spigot.listeners;

import fr.aquaria.spigot.AquariaCore;
import fr.aquaria.spigot.utils.IGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiManagerListeners implements Listener {

    private AquariaCore aquariaCore;

    public GuiManagerListeners(AquariaCore aquariaCore) {
        this.aquariaCore = aquariaCore;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null) return;
        if(!event.getCurrentItem().hasItemMeta()) return;

        Player player = (Player) event.getWhoClicked();
        IGui gui = aquariaCore.getGuisManager().getOpenGui(player);
        if(gui == null) return;

        IGui newGUI = gui.handleClick(player, event.getCurrentItem(), event.getView());

        event.getView().close();

        if(newGUI != null) {
            aquariaCore.getGuisManager().setGui(player, newGUI);
        }


    }

}
