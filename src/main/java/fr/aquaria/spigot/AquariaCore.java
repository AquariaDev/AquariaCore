package fr.aquaria.spigot;

import fr.aquaria.spigot.database.MySQL;
import fr.aquaria.spigot.listeners.DatabasePlayerSetup;
import fr.aquaria.spigot.listeners.GuiManagerListeners;
import fr.aquaria.spigot.utils.GUI;
import org.bukkit.plugin.java.JavaPlugin;

public class AquariaCore extends JavaPlugin {

    private GUI gui;
    private MySQL mySQL;

    @Override
    public void onEnable() {
        /*
        * TODO: Bahh j'en sais rien à quoi ça va servir pour l'instant, on verra ensemble Viazel
        * */

        // Connexion aux bdds
        mySQL = new MySQL();
        mySQL.getPlayersConnection().connect();
        // ----
        this.gui = new GUI();
        getServer().getPluginManager().registerEvents(new GuiManagerListeners(this), this);
        getServer().getPluginManager().registerEvents(new DatabasePlayerSetup(this), this);


    }

    public GUI getGuisManager() {
        return gui;
    }

}
