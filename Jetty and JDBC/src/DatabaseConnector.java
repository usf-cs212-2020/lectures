import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Set;

/**
 * This class is designed to test your database configuration. You need to have a
 * database.properties file with username, password, database, and hostname. You must also have the
 * tunnel to stargate.cs.usfca.edu running if you are off-campus.
 */
public class DatabaseConnector {
  /**
   * URI to use when connecting to database. Should be in the format:
   * jdbc:subprotocol://hostname/database
   */
  public final String uri;

  /** Properties with username and password for connecting to database. */
  private final Properties login;

  /**
   * Creates a connector from a "database.properties" file located in the current working directory.
   *
   * @throws IOException if unable to properly parse properties file
   * @throws FileNotFoundException if properties file not found
   */
  public DatabaseConnector() throws FileNotFoundException, IOException {
    this("database.properties");
  }

  /**
   * Creates a connector from the provided database properties file.
   *
   * @param configPath path to the database properties file
   * @throws IOException if unable to properly parse properties file
   * @throws FileNotFoundException if properties file not found
   */
  public DatabaseConnector(String configPath) throws FileNotFoundException, IOException {
    // try to load the configuration from file
    Properties config = loadConfig(configPath);

    // create database URI in proper format
    uri = String.format("jdbc:mysql://%s/%s",
        config.getProperty("hostname"),
        config.getProperty("database"));

    // create database login properties
    login = new Properties();
    login.put("user", config.getProperty("username"));
    login.put("password", config.getProperty("password"));
  }

  /**
   * Attempts to load properties file with database configuration. Must include username, password,
   * database, and hostname.
   *
   * @param configPath path to database properties file
   * @return database properties
   * @throws IOException if unable to properly parse properties file
   * @throws FileNotFoundException if properties file not found
   */
  private Properties loadConfig(String configPath) throws FileNotFoundException, IOException {
    // specify which keys must be in properties file
    Set<String> required = Set.of("username", "password", "database", "hostname");

    // load properties file
    Properties config = new Properties();
    config.load(new FileReader(configPath, StandardCharsets.UTF_8));

    // check that required keys are present
    if (!config.keySet().containsAll(required)) {
      String error = "Must provide the following in properties file: ";
      throw new InvalidPropertiesFormatException(error + required);
    }

    return config;
  }

  /**
   * Attempts to connect to database using loaded configuration.
   *
   * @return database connection
   * @throws SQLException if unable to establish database connection
   */
  public Connection getConnection() throws SQLException {
    Connection dbConnection = DriverManager.getConnection(uri, login);
    dbConnection.setAutoCommit(true);
    return dbConnection;
  }

  /**
   * Opens a database connection and returns a set of found tables. Will return an empty set if
   * there are no results.
   *
   * @param db the active database connection
   *
   * @return set of tables
   * @throws SQLException if unable to successfully execute sql statement
   */
  public Set<String> getTables(Connection db) throws SQLException {
    Set<String> tables = new HashSet<>();

    // create statement and close when done
    // database connection will be closed elsewhere
    try (Statement sql = db.createStatement();) {
      if (sql.execute("SHOW TABLES;")) {
        ResultSet results = sql.getResultSet();

        while (results.next()) {
          tables.add(results.getString(1));
        }
      }
    }

    return tables;
  }

  /**
   * Opens a database connection and returns a set of found tables. Will return an empty set if
   * there are no results.
   *
   * @return set of all tables if all operations successful
   * @throws SQLException if unable to fetch tables from database
   */
  public Set<String> getTables() throws SQLException {
    // open database connection and close when done
    try (Connection db = getConnection();) {
      return getTables(db);
    }
  }

  /**
   * Tests whether database configuration (including tunnel) is correct. If you see the message
   * "Connection to database established" then your settings are correct.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    try {
      String properties = "database.properties";

      // check for other properties file in command-line arguments
      if (args.length > 0 && Files.exists(Paths.get(args[0]))) {
        properties = args[0];
      }

      DatabaseConnector test = new DatabaseConnector(properties);
      System.out.println("Connecting to " + test.uri);

      Set<String> tables = test.getTables();
      System.out.println("Connection to database established.");
      System.out.printf("Found %d tables: %s%n", tables.size(), tables.toString());
    }
    catch (Exception e) {
      System.err.println("Unable to connect properly to database.");
      System.err.println(e.getMessage());
    }
  }
}
