package files;

import java.util.HashMap;

import javafx.scene.image.Image;

public class CardCreator {
	static HashMap<String, Card> cardData = new HashMap<String, Card>(){{
		put("Forest", new Card("Forest", "Adds cards from exploring the forest.", new Image("file:src/resources/Forest Booster.png")));
	    put("Stick", new Card("Stick", "Destroy 2 cards from opponents deck.", new Image("file:src/resources/Stick.png")));
	    put("Log", new Card("Log", "Creates 3-2 sticks on use.", new Image("file:src/resources/Log.png")));
	    put("Stone", new Card("Stone", "Create 2 Sharp Stones.", new Image("file:src/resources/Stone.png")));
	    put("Sharp Stone", new Card("Sharp Stone", "Destroy a card from opponents hand.", new Image("file:src/resources/Sharp Stone.png")));
	    put("Axe", new Card("Axe", "Destroy 10 cards from opponents deck. Discard your hand.", new Image("file:src/resources/Axe.png")));
	}};
	
	public static Card createCard(String name) {
		Card card = cardData.get(name);
		return new Card(card.getName(), card.getDescription(), card.getImage());
	}

}
