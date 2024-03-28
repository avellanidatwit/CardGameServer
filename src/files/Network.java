package files;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import files.packets.Request;
import files.packets.Text;

/**
 * Class to handle server side operations
 * @author evelyn
 */
public class Network {
	
	static public void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Card.class);
		kryo.register(Deck.class);
		kryo.register(User.class);
		kryo.register(Request.class);
		kryo.register(Text.class);
		
	}
}
