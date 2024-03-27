package files;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public interface RMIMethods {
	static public boolean objectRegistrar(Object o, int id, ObjectSpace RMI) {
		if(o instanceof Card) {
			Card temp = (Card) o;
			RMI.register(id, temp);
			return true;
		}
		else if(o instanceof Deck){
			Deck temp = (Deck) o;
			RMI.register(id, temp);
			return true;
		}
		else {
			System.out.printf("WARNING: invalid object detected, object " + o + " not registered.");
			return false;
		}
	}
	static public void OSConnector(Connection c, ObjectSpace RMI) {
		RMI.addConnection(c);
	}

}
