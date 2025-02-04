package database;

import network.ServerConfiguration;
import utils.JSONReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connector to MySQL database
 */
public class DBConnector {
    private static final String BASE_URL = "jdbc:mysql://%s:%d/%s?verifyServerCertificate=false&useSSL=true";
    private String dbUsername;
    private String dbPassword;
    private int dbPort;
    private String db;
    private String url;
    private static Connection conn = null;
    private static Statement s;
    private ServerConfiguration config;

    /**
     * Constructor for DBConnector
     */
    public DBConnector() {
        config = new ServerConfiguration();
        initDBConfiguration();
    }

    /**
     * Initializes the database configuration from a file
     */
    private void initDBConfiguration() {
        JSONReader jsonReader = new JSONReader();
        this.config = jsonReader.getServerConfiguration();
        this.db = config.getDbName();
        this.dbUsername = config.getDbUser();
        this.dbPassword = config.getDbPassword();
        this.dbPort = config.getDbPort();
        this.url = String.format(BASE_URL, config.getDbIp(), dbPort, db);
    }

    /**
     * Connects to MySQL jdbc driver
     */
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, dbUsername, dbPassword);
            if (conn != null) {
                System.out.println("Connecting database " + url + " ... OK");
            }
        } catch (SQLException ex) {
            System.out.println("Connecting database" + url + " ... KO");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Insert query function
     * @param query Query
     */
    public void insertQuery(String query) {
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Insertion KO " + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }
    }

    /**
     * Updates query function
     * @param query Query
     */
    public void updateQuery(String query) {
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Modification KO " + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }
    }

    /**
     * Select query function
     * @param query Query
     * @return ResultSet
     */
    public ResultSet selectQuery(String query) {
        ResultSet rs = null;
        try {
            s = (Statement) conn.createStatement();
            rs = s.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println("Data Recovery KO" + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }
        return rs;
    }

    /**
     * Delete query function
     * @param query Query
     */
    public void deleteQuery(String query) {
        try {
            s = (Statement) conn.createStatement();
            s.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Delete KO" + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }

    }

    /**
     * Procedure calls function
     * @param query Procedure query
     */
    public void callProcedure(String query) {
        try {
            s = (Statement) conn.createStatement();
            s.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println("Delete KO" + ex.getSQLState());
            System.out.println("Query: " + query);
            System.err.println(ex);
        }

    }

    /**
     * Close mysql connection
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Connection KO" + ex.getSQLState());
            System.err.println(ex);
        }
    }
}
