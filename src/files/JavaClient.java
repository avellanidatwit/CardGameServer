package files;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * This class implements the java socket client
 * @author Dominic Avellani
 *
 */
// TODO: restructure this class into an actual client instead of an example case.
public abstract sealed class JavaClient permits User {

    public static void main(String[] args) throws IOException{
    	
    	Client client = new Client();
    	Kryo kryo = client.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
    	
    	client.addListener(new Listener() {
    	       public void received (Connection connection, Object object) {
    	          if (object instanceof SomeResponse) {
    	             SomeResponse response = (SomeResponse)object;
    	             System.out.println(response.text);
    	          }
    	       }
    	    });
    	
        client.start();
        client.connect(5000, "127.0.0.1", 54555, 54777);
        
        SomeRequest request = new SomeRequest();
        request.text = "Here is the request";
        client.sendTCP(request);
    }
}