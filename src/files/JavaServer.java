package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import files.packets.Request;
import files.packets.Text;

/**
 * A class that represents a game server which players connect to and get their actions from.
 * @author evelyn
 */
public class JavaServer {
	
	// Mapping connections to users
	private final Map<Connection, User> users = new HashMap<>();
    public Server server;

    public JavaServer() throws IOException {
    	server = new Server();
    	// Registering the classes to be sent over the network
    	Network.register(server);

        Kryo kryo = server.getKryo();
        kryo.setRegistrationRequired(false);

        // Adding responses to client requests
        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {

                User user = users.get(connection);
                
                // Card behavior
                if (object instanceof Card){
                	Card card = (Card) object;
                	
                	// Forest Booster Pack
                	if(card.getName().equals("Forest")) {
                		String text = "You found " + formatList(user.Forest());
                		printToUser(text , connection.getID());
                		user.discardCard(card);}
                	
                	// Log
                	if(card.getName().equals("Log")) {
                		String text = "You found " + formatList(user.Log());
                		printToUser(text , connection.getID());}
                	
                	// Stone
                	if(card.getName().equals("Stone")) {
                		String text = "You found " + formatList(user.Stone());
                		printToUser(text , connection.getID());}
                	
                	// Stone
                	if(card.getName().equals("Bandage")) {
                		String text = "You found " + formatList(user.Bandage());
                		printToUser(text , connection.getID());}
                }
                
                // Other request behavior
                if (object instanceof Request){
                	Request request = (Request) object;
                	
                	// Sends user data back to the client when a game starts
                	if (request.message.equals("Init User")) {
                		server.sendToTCP(connection.getID(), user);
                	}
                	
                	// Draws a card and sends it to the user
                	if (request.message.equals("Draw Card")) {
                		Card card = user.drawCard();
                		if(card != null) {server.sendToTCP(connection.getID(), card);}
                	}
                }
            }

            // Handles removing clients
            @Override
            public void disconnected(Connection connection) {
            	if (users.containsKey(connection)) {   
                    users.remove(connection);
                }
            }

            // Handles adding clients
            @Override
            public void connected(Connection connection) {
            	if (!users.containsKey(connection)) {
                    users.put(connection, new User("Player " + connection.getID()));
                }
            }
        });
        
        // Starts the server
        server.bind(54555, 54777);
        server.start();
    }
    
    public void printToUser(String string, int ID) {
    	Text text = new Text(string);
    	server.sendToTCP(ID, text);
    }
    
    public String formatList(ArrayList<Card> list) {
    	StringBuilder string = new StringBuilder();
    	for (int i = 0; i < list.size(); i++) {
    		if (i == list.size()-1) {
    			string.append("and a ");
    			string.append(list.get(i).getName());
    			string.append(".");} 
    		else {
    			string.append(list.get(i).getName());
    			string.append(", ");}
    	}
    	return string.toString();
    }
}