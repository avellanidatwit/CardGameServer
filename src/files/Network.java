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
		kryo.register(Deck.class);
		kryo.register(ObjectRegistrationResponse.class);
		
		ObjectSpace.registerClasses(kryo);
	}
}
