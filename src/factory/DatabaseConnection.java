package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String USERNAME = "SA";
    private static final String PASSWORD = "Root123.";
    private static final String PORT = "1433";
    private static final String URL = "jdbc:sqlserver://localhost:" + PORT + ";DatabaseName=library;encrypt=true;trustServerCertificate=true";
    private static DatabaseConnection instance;
    private static Connection connection;

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public static Connection getConnection() throws Exception {
        if (connection == null) {
            //Loading the required JDBC Driver class
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //Creating a connection to the database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        return connection;
    }

}