import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Demonstrates how to interact with our MariaDB database server on campus in Jetty.
 */
public class FacultyServer {
  /** Required tables for this example to work. */
  private static final Set<String> REQUIRED = Set.of("faculty_names", "faculty_twitter", "faculty_courses");

  /**
   * Creates a database connection and Jetty server to allow users to interact with the faculty
   * tables from the Relational Databases examples.
   *
   * @param args first argument is used as the database properties file if present
   * @throws Exception if unable to run server
   */
  public static void main(String[] args) throws Exception {
    String properties = "database.properties";

    // check for other properties file in command-line arguments
    if (args.length > 0 && Files.exists(Path.of(args[0]))) {
      properties = args[0];
    }

    // attempt to connect to database and create servlet
    DatabaseConnector connector = new DatabaseConnector(properties);

    // make sure the table already exists
    // doesn't make sure the tables have the right structure or content though!
    if (!connector.getTables().containsAll(REQUIRED)) {
      System.err.println("Please make sure the faculty tables are already setup!");
      return;
    }

    FacultyServlet servlet = new FacultyServlet(connector);

    // default handler for favicon.ico requests
    ContextHandler defaultHandler = new ContextHandler("/favicon.ico");
    defaultHandler.setHandler(new DefaultHandler());

    // main servlet handler
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(new ServletHolder(servlet), "/");

    HandlerList handlers = new HandlerList();
    handlers.addHandler(defaultHandler);
    handlers.addHandler(servletHandler);

    // only setup server if we made it this far (otherwise an exception thrown)
    Server server = new Server(8080);
    server.setHandler(handlers);
    server.start();
    server.join();
  }
}
