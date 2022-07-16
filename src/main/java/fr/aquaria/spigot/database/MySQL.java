package fr.aquaria.spigot.database;

public class MySQL {

    private MySQLConnection playersConnection;

    public MySQL() {
        this.playersConnection = new MySQLConnection(new MySQLCredentials("127.0.0.1", "root", "02022007", "s7_PlayersManagement", 3306));
        //this.playersConnection = new MySQLConnection(new MySQLCredentials("127.0.0.1", "forthedevelopers", "P$PcGx73D#tog@fp", "s7_PlayersManagement", 3306));
    }

    public void close() {
        playersConnection.close();
    }

    public MySQLConnection getPlayersConnection() {
        return this.playersConnection;
    }

}
