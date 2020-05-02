import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.StringSubstitutor;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

public class FacultyServlet extends HttpServlet {
  private static final long serialVersionUID = 202020;
  private static final Logger log = Log.getLog();

  private final DatabaseConnector connector;
  private final String sqlSelect;

  private final String htmlHeader;
  private final String htmlRow;
  private final String htmlFooter;

  private static final Set<String> COLUMNS = Set.of("last", "first", "email", "twitter", "courses");

  private static final Charset UTF_8 = StandardCharsets.UTF_8;
  private static final String DATE_PATTERN = "hh:mm a 'on' EEEE, MMMM dd yyyy";
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

  public FacultyServlet(DatabaseConnector connector) throws IOException {
    this.connector = connector;

    sqlSelect = Files.readString(Path.of("sql", "select.sql"), UTF_8);
    htmlHeader = Files.readString(Path.of("html", "header.html"), UTF_8);
    htmlRow = Files.readString(Path.of("html", "row.html"), UTF_8);
    htmlFooter = Files.readString(Path.of("html", "footer.html"), UTF_8);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    log.info(request.getQueryString());

    // TODO Handle form parameters

    StringBuilder sql = new StringBuilder(sqlSelect);

    // TODO Adjust SQL statement as necessary

    sql.append(System.lineSeparator());
    sql.append("GROUP BY faculty_names.usfid");

    sql.append(";");
    log.info("SQL: {}", sql);

    PrintWriter out = response.getWriter();
    out.println(getHeader("/", "", false)); // TODO Update table headers

    try (
        Connection db = connector.getConnection();
        // TODO Setup statement
    ) {
      // TODO Output results
      out.println(htmlRow);
    }
    catch (SQLException e) {
      log.warn(e);
    }

    out.println(getFooter("", false)); // TODO Update form

    response.setStatus(HttpServletResponse.SC_OK);
    response.flushBuffer();
  }

  private String getHeader(String filter, String sort, boolean asc) {
    Map<String, String> values = new HashMap<>();

    values.put("last", "true");
    values.put("email", "true");
    values.put("twitter", "true");
    values.put("courses", "true");

    // TODO Toggle sort order

    return StringSubstitutor.replace(htmlHeader, values);
  }

  private String getFooter(String sort, boolean asc) {
    var values = Map.of(
        "sort", sort,
        "asc", Boolean.toString(asc),
        "thread", Thread.currentThread().getName(),
        "date", getLongDate()
    );

    return StringSubstitutor.replace(htmlFooter, values);
  }

  public static String getColumn(HttpServletRequest request, String key, String value) {
    String found = request.getParameter(key);
    return found != null && COLUMNS.contains(found) ? found : value;
  }

  public static boolean isEqual(HttpServletRequest request, String key, String value) {
    String found = request.getParameter(key);
    return found != null && found.equalsIgnoreCase(value);
  }

  public static String escape(ResultSet results, String column) throws SQLException {
    return StringEscapeUtils.escapeHtml4(results.getString(column));
  }

  public static String getLongDate() {
    LocalDateTime today = LocalDateTime.now();
    return today.format(FORMATTER);
  }
}
