package files;
import java.io.IOException;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

/**
 * This class implements the java socket client
 * @author evelyn
 *
 */
// TODO: restructure this class into an actual client instead of an example case.
public class JavaClient {
	Client client;
	String name;
	
	//TODO: implement a way to get/input the server ip.	
	static final String serverIP = "127.0.0.1";
	
	public JavaClient() {
		client = new Client();
		client.start();
		
		Network.register(client);
		
		client.addListener(new Listener() {
	       public void received (Connection connection, Object object) {
	          if(object instanceof ObjectRegistrationResponse) {
	        	  ObjectRegistrationResponse r = (ObjectRegistrationResponse) object;
	        	  System.out.printf("Object Registration Status:%nStatus:%s%nObject: %s%nID:%02d%n", 
	        			  r.status ? "Successful" : "Failed",
	        			  r.o, 
	        			  r.id);
	          }
	       }
	    });
		client.addListener(new ThreadedListener(new Listener() {
			public void disconnected(Connection connection) {
				System.exit(0);
			}
		}));
		
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
		
	}
}