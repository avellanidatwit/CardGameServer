package files;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Class to handle server side operations
 * @author evelyn
 */
public class Network {
	
	static public final int port = 54555;
	
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Card.class);
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		kryo.register(Deck.class);
	}
	
	static public class Card {
		public String name, description;
		public Image image;
		public int uses, maxUses;
	}
	
	static public class SomeRequest {
		public String text;
	}
	
	static public class SomeResponse {
		public String text;
	}
	
	static public class Deck {
		public ArrayList<Card> cards;
	}
}
