import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class DatabaseConnector {
  public final String uri;
  private final Properties login;

  public DatabaseConnector() throws FileNotFoundException, IOException {
    this("database.properties");
  }

  public DatabaseConnector(String configPath) throws FileNotFoundException, IOException {
    Properties config = loadConfig(configPath);

    uri = String.format("jdbc:mysql://%s/%s",
        config.getProperty("hostname"),
        config.getProperty("database"));

    login = new Properties();
    login.put("user", config.getProperty("username"));
    login.put("password", config.getProperty("password"));
  }

  private Properties loadConfig(String configPath) throws FileNotFoundException, IOException {
    Set<String> required = Set.of("username", "password", "database", "hostname");

    Properties config = new Properties();

    // TODO Load properties file
    System.out.println(required);

    return config;
  }

  public Connection getConnection() throws SQLException {
    return null; // TODO Get connection
  }

  public Set<String> getTables(Connection db) throws SQLException {
    Set<String> tables = new HashSet<>();

    // TODO Execute SQL over connection

    return tables;
  }

  public Set<String> getTables() throws SQLException {
    try (Connection db = getConnection();) {
      return getTables(db);
    }
  }

  public static void main(String[] args) {
    try {
      String properties = "database.properties";

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
