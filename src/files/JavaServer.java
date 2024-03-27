package files;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;	

import files.Network.Card;
import files.Network.Deck;
import files.Network.SomeRequest;
import files.Network.SomeResponse;

/**
 * This class implements java Socket server
 * @author evelyn
 *
 */


public class JavaServer implements RMIMethods {    
	
	
	
    public static void main(String args[]) throws IOException {
    	
    	Server server = new Server();
    	Kryo kryo = server.getKryo();
    	kryo.register(Card.class);
    	kryo.register(Deck.class);
    	kryo.register(ObjectRegistrationResponse.class);
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        
        ObjectSpace.registerClasses(kryo);
        ObjectSpace OSS = new ObjectSpace();
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
    	server.addListener(new Listener() {
    		public void serverObjectRegistrar(Connection c, Object o, int id) {
    			RMIMethods.OSConnector(c, OSS);
    			boolean status = RMIMethods.objectRegistrar(o, id, OSS);
    			ObjectRegistrationResponse r = new ObjectRegistrationResponse();
    			r.status = status;
    			r.id = id;
    			r.o = o;
    			c.sendTCP(r);
    		}
    	});
    	
        server.bind(54555, 54777);
        server.start();
    }
    
}