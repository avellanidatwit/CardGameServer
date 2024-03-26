package files;

import java.io.IOException;
import java.util.HashMap;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.esotericsoftware.kryonet.Listener.TypeListener;
import com.esotericsoftware.minlog.Log;

/**
 * This class implements the java socket client
 * @author evelyn
 *
 */
// TODO: restructure this class into an actual client instead of an example case.
public abstract sealed class JavaClient permits User {
	Client client;
	String name;
	//TODO: implement a way to get/input the server ip.	
	static final String serverIP = "";
	public JavaClient() throws IOException {
		client = new Client();
		client.start();
		
		try {
			client.connect(5000, serverIP, 54555, 54777);
		}
		catch(IOException e) {
			System.out.printf("Connection failed, exiting program.%nStack Trace: %s", e.getStackTrace().toString());
			System.exit(1);
		}
		finally {
			System.out.println("Connection succeeded, continuing.");
		}
		
		Network.register(client);
		client.addListener(new Listener() {
	       public void received (Connection connection, Object object) {
	          if (object instanceof SomeResponse) {
	             SomeResponse response = (SomeResponse)object;
	             System.out.println(response.text);
	          }
	       }
	    });
		client.addListener(new ThreadedListener(new Listener() {
			public void disconnected(Connection connection) {
				System.exit(0);
			}
		}));
		
	}
}