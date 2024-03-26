package files;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

/**
 * This class implements java Socket server
 * @author evelyn
 *
 */


public class JavaServer {    
    
    public static void main(String args[]) throws IOException {
    	
    	Server server = new Server();
    	Kryo kryo = server.getKryo();
    	kryo.register(Card.class);
    	kryo.register(Deck.class);
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
    	
    	server.addListener(new Listener() {
    	       public void received (Connection connection, Object object) {
    	          if (object instanceof SomeRequest) {
    	             SomeRequest request = (SomeRequest)object;
    	             System.out.println(request.text);
    	    
    	             SomeResponse response = new SomeResponse();
    	             response.text = "Thanks";
    	             connection.sendTCP(response);
    	          }
    	       }
    	    });
    	
        server.bind(54555, 54777);
        server.start();
    }
    
}