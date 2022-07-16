package fr.aquaria.spigot.listeners;

import fr.aquaria.spigot.AquariaCore;
import fr.aquaria.spigot.entity.ServerPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DatabasePlayerSetup implements Listener {

    private AquariaCore aquariaCore;

    public DatabasePlayerSetup(AquariaCore aquariaCore) {
        this.aquariaCore = aquariaCore;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ServerPlayer player = new ServerPlayer(e.getPlayer());
        player.setupUser();
    }

}
