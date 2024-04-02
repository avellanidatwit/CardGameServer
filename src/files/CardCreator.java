package files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class CardCreator {
	private HashMap<String, Card> cardData;
	private HashMap<ArrayList<String>, Card> recipes = new HashMap<>();

	// Static variable reference of single_instance
	// of type Singleton
	private static CardCreator single_instance = null;

	// Constructor
	// Here we will be creating private constructor
	// restricted to this class itself
	@SuppressWarnings("serial")
	private CardCreator() {
		cardData = new HashMap<>() {
			{
				put("Forest Booster", new Card("Forest Booster", "file:src/resources/Forest Booster.png", 0));
				put("Stick", new Card("Stick", "file:src/resources/Stick.png"));
				put("Log", new Card("Log", "file:src/resources/Log.png"));
				put("Stone", new Card("Stone", "file:src/resources/Stone.png"));
				put("Sharp Stone", new Card("Sharp Stone", "file:src/resources/Sharp Stone.png"));
				put("Axe", new Card("Axe", "file:src/resources/Axe.png"));
				put("Rope", new Card("Rope", "file:src/resources/Rope.png"));
				put("Bandage", new Card("Bandage", "file:src/resources/Bandage.png", 2));
				put("Apple", new Card("Apple", "file:src/resources/Apple.png", 2));
				put("Seedling", new Card("Seedling", "file:src/resources/Seedling.png"));
				put("Tree", new Card("Tree", "file:src/resources/Tree.png"));
			}
		};

		// Crafting recipes
		recipes.put(new ArrayList<String>() {
			{
				add("Stick");
				add("Sharp Stone");
			}
		}, createCard("Axe"));
		recipes.put(new ArrayList<String>() {
			{
				add("Rope");
				add("Rope");
			}
		}, createCard("Bandage"));
	}

	public Card createCard(String name) {
		Card card = cardData.get(name);
		return new Card(card.getName(), card.getImage());
	}

	public Card canCraft(Card card1, Card card2) {
		@SuppressWarnings("serial")
		ArrayList<String> input = new ArrayList<>() {
			{
				add(card1.getName());
				add(card2.getName());
			}
		};
		Collections.sort(input);
		for (ArrayList<String> key : recipes.keySet()) {
			ArrayList<String> temp = new ArrayList<>(key);
			Collections.sort(temp);
			if (input.equals(temp)) {
				return recipes.get(key);
			}
		}
		return null;
	}

	// Static method
	// Static method to create instance of Singleton class
	public static synchronized CardCreator getInstance() {
		if (single_instance == null) {
			single_instance = new CardCreator();
		}

		return single_instance;
	}
}
