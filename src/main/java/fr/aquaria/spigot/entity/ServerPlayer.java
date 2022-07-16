package fr.aquaria.spigot.entity;

import com.avaje.ebeaninternal.server.lib.util.NotFoundException;
import fr.aquaria.spigot.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ServerPlayer {

    private Player player;

    public ServerPlayer(Player player) {
       /*
       *
       * Player comme paramètre est une façon de créer une instance de ServerPlayer
       *
       * */

        this.player = player;

    }


    public ServerPlayer(String username) {
        /* Le serveur recherchera automatiquement un joueur qui possède ce pseudo, qu'il soit connecté ou non */
        Player player = Bukkit.getPlayer(username);
        if(!player.hasPlayedBefore()) {
            player = null;
            throw new NotFoundException("Le joueur demandé n'a jamais rejoint le serveur, il est donc impossible de le trouver. Cette erreur n'en est pas vraiment une, le plugin fonctionne correctement cependant.");
        }
    }

    public ServerPlayer(UUID UUID) {
        /* Le serveur recherchera automatiquement un joueur qui possède ce pseudo, qu'il soit connecté ou non */
        Player player = Bukkit.getPlayer(UUID);
        if(!player.hasPlayedBefore()) {
            player = null;
            throw new NotFoundException("Le joueur demandé n'a jamais rejoint le serveur, il est donc impossible de le trouver. Cette erreur n'en est pas vraiment une, le plugin fonctionne correctement cependant.");
        }
    }

    public void sendAlert(String circumstences) {
        player.sendMessage("§c[Alerte] : " + ChatColor.translateAlternateColorCodes('&', circumstences));
    }

    public Player toPlayer() {
        return this.player;
    }

    /* TODO: Récupérer le rôle d'un joueur */

    public String getRoleName() {
        MySQL mySQL = new MySQL();
        try {
            PreparedStatement state = mySQL.getPlayersConnection().getConnection().prepareStatement("SELECT * FROM players WHERE USERNAME = '" + player.getName() + "'");
            ResultSet rs = state.executeQuery();
            if(rs.next()) {
                return rs.getString("USERNAME");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "NULL";
    }

    /* TODO: Récupérer l'argent virtuelle d'un joueur */
    public int getMoney() {
        MySQL mySQL = new MySQL();
        try {
            PreparedStatement state = mySQL.getPlayersConnection().getConnection().prepareStatement("SELECT * FROM players WHERE USERNAME = '" + player.getName() + "'");
            ResultSet rs = state.executeQuery();
            if(rs.next()) {
                return rs.getInt("MONEY");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    /* TODO: Récupérer la dernière connection du joueur */

    public Date getLastConnection() {
        MySQL mySQL = new MySQL();
        try {
            PreparedStatement state = mySQL.getPlayersConnection().getConnection().prepareStatement("SELECT * FROM players WHERE USERNAME = '" + player.getName() + "'");
            ResultSet rs = state.executeQuery();
            if(rs.next()) {
                return rs.getDate("LAST-CONNECTION");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /* TODO: Récupérer le pseudo depuis la base de données */

    public String getNameFromDataBase() {
        MySQL mySQL = new MySQL();
        try {
            PreparedStatement state = mySQL.getPlayersConnection().getConnection().prepareStatement("SELECT * FROM players WHERE UUID = '" + player.getUniqueId().toString() + "'");
            ResultSet rs = state.executeQuery();
            if(rs.next()) {
                return rs.getString("USERNAME");
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /* TODO: Enregister le joueur dans la base de données */

    public void setupUser() {

        MySQL mySQL = new MySQL();
        try {
            PreparedStatement state = mySQL.getPlayersConnection().getConnection().prepareStatement("SELECT * FROM players WHERE UUID = '" + player.getUniqueId() + "'");
            ResultSet rs = state.executeQuery();
            if(!rs.next()) {
                PreparedStatement statement = mySQL.getPlayersConnection().getConnection().prepareStatement("INSERT INTO players (USERNAME, UUID, RANK, MONEY) VALUES(" +
                        "'" + player.getName() + "'," +
                        "'" + player.getUniqueId() + "'," +
                        "'JOUEUR'" +
                        "2)");
                statement.executeUpdate();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
