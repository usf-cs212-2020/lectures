import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class SerialDirectoryListing {

	public static Set<Path> list(Path path) throws IOException {
		HashSet<Path> paths = new HashSet<>();

		// TODO

		return paths;
	}

	private static void list(Path path, Set<Path> paths) throws IOException {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			// TODO
		}
	}

	public static void main(String[] args) throws IOException {
		list(Path.of(".")).stream().forEach(System.out::println);
	}
}
