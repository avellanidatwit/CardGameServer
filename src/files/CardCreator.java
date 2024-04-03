package files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Class to create all the cards locally, because local object storage is hard in literally everything without native JSON support.
 * @author evelyn
 * 
 * @category Cards
 */
class CardCreator {
	private HashMap<String, Card> cardData;
	private HashMap<ArrayList<String>, Card> recipes = new HashMap<>();

	// Static variable reference of single_instance
	// of type Singleton
	private static CardCreator single_instance = null;

	// Constructor
	// Here we will be creating private constructor
	// restricted to this class itself
	/**
	 * Private 0-arg constructor for creating cards
	 */
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
	/**
	 * Creates a card after the initial constructor run.
	 * @param name Name of card.
	 * @return The card.
	 */
	public Card createCard(String name) {
		Card card = cardData.get(name);
		return new Card(card.getName(), card.getImage());
	}
	/**
	 * Checks if a card can craft, and if it can it returns the recipes that are available for it.
	 * @param card1 Card 1 to check with.
	 * @param card2 Card 2 to check with.
	 * @return Recipe list(?)
	 */
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

	/**
	 * Static method for singleton initialization
	 * @return An instance of Card Creator.
	 */
	public static synchronized CardCreator getInstance() {
		if (single_instance == null) {
			single_instance = new CardCreator();
		}

		return single_instance;
	}
}
