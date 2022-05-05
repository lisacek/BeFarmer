package me.lisacek.befarmer.sql;

import lombok.Getter;
import me.lisacek.befarmer.BeFarmer;

public class ConnectionManager {

    @Getter
    private DatabaseConnection coreConnection;

    public boolean initialize() {
        coreConnection = createConnection("plugin.mysql", false);
        return true;
    }

    public DatabaseConnection createConnection(String path, boolean readOnly) {
        return createConnection(path, readOnly, new ConnectionInfo("localhost", 3306, "user", "secretpassword", "database"));
    }

    public DatabaseConnection createConnection(String path, boolean readOnly, ConnectionInfo defaults) {
        ConnectionInfo connectionInfo = ConnectionInfo.load(BeFarmer.getInstance().getConfig().getConfigurationSection(path));

        if (connectionInfo == null) {
            BeFarmer.getInstance().getLogger().info("Could not load connection info from " + path);
            return null;
        }

        DatabaseConnection connection = new DatabaseConnection(connectionInfo);

        try {
            connection.connect();
        } catch (IllegalStateException e) {
            BeFarmer.getInstance().getLogger().info("Could not connect to database at " + connectionInfo.toString() + ". Reason: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return connection;
    }


    public void close() {
        if (coreConnection == null) return;
        coreConnection.close();
    }

}