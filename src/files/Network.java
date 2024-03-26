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
public class Network {
	
	static public final int port = 54555;
	static public ObjectSpace OSN = new ObjectSpace();
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Card.class);
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		kryo.register(Deck.class);
		
		ObjectSpace.registerClasses(kryo);
	}
	
	static public void objectRegistrar(Object o, int id) {
		if(o instanceof Card) {
			Card temp = (Card) o;
			OSN.register(id, temp);
		}
		else if(o instanceof SomeRequest) {
			SomeRequest temp = (SomeRequest) o;
			OSN.register(id, temp);
		}
		else if(o instanceof SomeResponse) {
			SomeResponse temp = (SomeResponse) o;
			OSN.register(id, temp);
		}
		else if(o instanceof Deck){
			Deck temp = (Deck) o;
			OSN.register(id, temp);
		}
		else {
			System.out.printf("WARNING: invalid object detected, object " + o + " not registered.");
		}
	}
	
	static public void OSConnector(Connection c) {
		OSN.addConnection(c);
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
