package files;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Class to handle server side operations
 * @author evelyn
 */
public class Network implements RMIMethods {
	
	static public final int port = 54555;
	static public ObjectSpace OSN = new ObjectSpace();
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Card.class);
		kryo.register(ObjectRegistrationResponse.class);
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		kryo.register(Deck.class);
		
		ObjectSpace.registerClasses(kryo);
	}
	
		
	
	/**
	 * Kryo handler for object registration response
	 */
	static public class ObjectRegistrationResponse {
		public int id;
		public boolean status;
		public Object o;
	}
	/**
	 * Kryo handler for Card
	 */
	static public class Card {
		public String name, description;
		public Image image;
		public int uses, maxUses;
	}
	/**
	 * Kryo handler for SomeRequest
	 */
	static public class SomeRequest {
		public String text;
	}
	/**
	 * Kryo handler for SomeResponse
	 */
	static public class SomeResponse {
		public String text;
	}
	/**
	 * Kryo handler for Deck
	 */
	static public class Deck {
		public ArrayList<Card> cards;
	}
}
