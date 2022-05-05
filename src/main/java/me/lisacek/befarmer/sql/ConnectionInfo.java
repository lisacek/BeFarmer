package me.lisacek.befarmer.sql;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.ConfigurationSection;

public class ConnectionInfo {

    @Getter
    private final String host;
    @Getter
    private final int port;
    @Getter
    private final String user;
    @Getter
    private final String password;
    @Getter
    private final String database;

    @Getter
    @Setter
    private boolean readOnly = false;

    public ConnectionInfo(String host, int port, String user, String password, String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public static ConnectionInfo load(ConfigurationSection config) {
        if (config == null)
            return null;

        ConnectionInfo info = new ConnectionInfo(
                config.getString("host"),
                config.getInt("port"),
                config.getString("username"),
                config.getString("password"),
                config.getString("database"));
        info.setReadOnly(false);
        return info;
    }

    @Override
    public String toString() {
        return user + "@" + host + ":" + port + "/" + database;
    }
}