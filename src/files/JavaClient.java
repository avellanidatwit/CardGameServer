package files;
import java.io.IOException;
import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.TypeListener;

import javafx.application.Platform;

import files.packets.Text;

/**
 * A players connection to the game server.
 * @author evelyn
 *
 */
public class JavaClient {
	public Client client;
	
	// A reference to the GUI script
	public VFXMethods vfxmethods;

    public JavaClient(VFXMethods vfxmethods) throws IOException {
    	
    	this.vfxmethods = vfxmethods;
    	
        client = new Client();
        // Registering the classes to be sent over the network
        Network.register(client);
        InetAddress address = client.discoverHost(54777, 5000);
        
        Kryo kryo = client.getKryo();
        kryo.setRegistrationRequired(false);

        client.start();
        client.connect(5000, address, 54555, 54777);

        setupController();
    }

    // Server response handling
    public void setupController() {
    	TypeListener typeListener = new TypeListener();
    	
    	// When the server sends a card object, the player draw the card.
		typeListener.addTypeHandler(Card.class, (connection, card) -> {
			if (card.action == Action.DISCARD) {
				card.action = Action.NONE;
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	vfxmethods.removeCardHromHand(card);
				    }
				});	
			}
			else {
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	vfxmethods.addCardToHand(card);
				    }
				});	
			}
		});
		
		// When the server sends a card object, the player draw the card.
		typeListener.addTypeHandler(Text.class, (connection, text) -> {
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    	vfxmethods.textNotification(text.message);}});	
		});
		
		client.addListener(typeListener);
    }
}