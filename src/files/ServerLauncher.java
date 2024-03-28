package files;

import java.io.IOException;

public class ServerLauncher {
	
		private static JavaServer server;
		
	public static void main(String[] args) {
		try {
			server = new JavaServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}