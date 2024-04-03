package files;

import java.io.IOException;
/**
 * Class that initializes and launches the server
 * @author evelyn
 * @category Networking
 */
public class ServerLauncher {

	@SuppressWarnings("unused")
	private static JavaServer server;

	public static void main(String[] args) {
		try {
			server = new JavaServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}