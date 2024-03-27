package files;

import java.awt.Image;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

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
    	kryo.register(Image.class);
    	kryo.register(Card.class);
    	kryo.register(Deck.class);
    	kryo.register(ObjectRegistrationResponse.class);
    	
        ObjectSpace.registerClasses(kryo);
        ObjectSpace OSS = new ObjectSpace();
    	server.addListener(new Listener() {
    	       public void received (Connection connection, Object object) {
    	          
    	          if(object instanceof Card || object instanceof Deck) {
    	        	  int id = kryo.getNextRegistrationId();
    	        	  boolean status = RMIMethods.objectRegistrar(object, id, OSS);
    	        	  ObjectRegistrationResponse rsp = new ObjectRegistrationResponse();
    	        	  rsp.id = id;
    	        	  rsp.o = object;
    	        	  rsp.status = status;
    	        	  connection.sendTCP(rsp);
    	          }
    	       }
    	    });    	
        server.bind(54555, 54777);
        server.start();
    }
    
}