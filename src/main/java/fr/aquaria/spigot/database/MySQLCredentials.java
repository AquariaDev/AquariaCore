package fr.aquaria.spigot.database;

public class MySQLCredentials {

    private String host, user, password, database;
    private int port;

    public MySQLCredentials(String host, String user, String password, String database, int port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    public String toURI() {
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
