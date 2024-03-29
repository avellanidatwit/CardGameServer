package files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javafx.scene.image.Image;

class CardCreator {
	private HashMap<String, Card> cardData;
	private HashMap<ArrayList<String>, Card> recipes = new HashMap<ArrayList<String>, Card>();

	// Static variable reference of single_instance
    // of type Singleton
    private static CardCreator single_instance = null;
 
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private CardCreator()
    {
        cardData = new HashMap<String, Card>(){{
    		put("Forest Booster", new Card("Forest Booster", "Adds cards from exploring the forest.", "file:src/resources/Forest Booster.png", 0));
    	    put("Stick", new Card("Stick", "Destroy 2 cards from opponents deck.", "file:src/resources/Stick.png"));
    	    put("Log", new Card("Log", "Creates 3-2 sticks on use.", "file:src/resources/Log.png"));
    	    put("Stone", new Card("Stone", "Create 2 Sharp Stones.", "file:src/resources/Stone.png"));
    	    put("Sharp Stone", new Card("Sharp Stone", "Destroy a card from opponents hand.", "file:src/resources/Sharp Stone.png"));
    	    put("Axe", new Card("Axe", "Destroy 10 cards from opponents deck. Discard your hand.", "file:src/resources/Axe.png"));
    	    put("Rope", new Card("Rope", "Discards 2 cards from opponents hand", "file:src/resources/Rope.png"));
    	    put("Bandage", new Card("Bandage", "Create 2 Bandages.", "file:src/resources/Bandage.png", 2));
    	}};
    	
    	// Crafting recipes
    	recipes.put(new ArrayList<String>() {{ add("Stick"); add("Sharp Stone");}}, createCard("Axe"));
    	recipes.put(new ArrayList<String>() {{ add("Rope"); add("Rope");}}, createCard("Bandage"));
    }
    
    public Card createCard(String name) {
		Card card = cardData.get(name);
		return new Card(card.getName(), card.getDescription(), card.getImage());
	}
    
    public Card canCraft(Card card1, Card card2) {
		String[] input1 = {card1.getName(), card2.getName()};
		ArrayList<String> input = new ArrayList<String>() {{ add(card1.getName()); add(card2.getName());}};
		Collections.sort(input);
		for(ArrayList<String> key : recipes.keySet()) {
			ArrayList<String> temp = new ArrayList<>(key);
			Collections.sort(temp);
			if(input.equals(temp)) {
				return recipes.get(key);
			}
		}
		return null;
	}
 
    // Static method
    // Static method to create instance of Singleton class
    public static synchronized CardCreator getInstance()
    {
        if (single_instance == null)
            single_instance = new CardCreator();
 
        return single_instance;
    }
}
