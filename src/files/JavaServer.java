package files;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

/**
 * This class implements java Socket server
 * @author Dominic Avellani
 *
 */


public class JavaServer {    
    
    public static void main(String args[]) throws IOException {
    	
    	Server server = new Server();
    	Kryo kryo = server.getKryo();
    	kryo.register(Card.class);
        kryo.register(SomeResponse.class);
    	
    	server.addListener(new Listener() {
    	       public void received (Connection connection, Object object) {
    	          if (object instanceof Card) {
    	             Card card = (Card)object;
    	             System.out.println(card.getName());
    	    
    	             SomeResponse response = new SomeResponse();
    	             response.text = "Activated: " + card.getName();
    	             connection.sendTCP(response);
    	          }
    	       }
    	    });
    	
        server.bind(54555, 54777);
        server.start();
    }
    
}