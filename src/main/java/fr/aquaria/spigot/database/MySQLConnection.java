package fr.aquaria.spigot.database;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private MySQLCredentials mySQLCredentials;
    private Connection connection;

    public MySQLConnection(MySQLCredentials mySQLCredentials) {
        this.mySQLCredentials = mySQLCredentials;
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(mySQLCredentials.toURI(), mySQLCredentials.getUser(), mySQLCredentials.getPassword());
            Bukkit.getLogger().info("Connexion à la base de données réussie");
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if(this.connection != null) {
            try {
                if(!this.connection.isClosed()) {
                    this.connection.close();
                }
             } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        if(this.connection != null) {
            try {
                if(!this.connection.isClosed()) {
                    return this.connection;
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        connect();
        return getConnection();
    }


}
