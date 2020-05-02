import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;

public class FacultyServer {
  private static final Set<String> REQUIRED = Set.of("faculty_names", "faculty_twitter", "faculty_courses");

  public static void main(String[] args) throws Exception {
    String properties = "database.properties";

    if (args.length > 0 && Files.exists(Path.of(args[0]))) {
      properties = args[0];
    }

    // TODO Setup database connector and servlet
    System.out.println(properties);
    System.out.println(REQUIRED);

    ContextHandler defaultHandler = new ContextHandler("/favicon.ico");
    defaultHandler.setHandler(new DefaultHandler());

    ServletHandler servletHandler = new ServletHandler();
    // TODO Add servlet

    HandlerList handlers = new HandlerList();
    handlers.addHandler(defaultHandler);
    handlers.addHandler(servletHandler);

    Server server = new Server(8080);
    server.setHandler(handlers);
    server.start();
    server.join();
  }
}
