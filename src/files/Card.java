package files;

/**
 * A class to make a card, for use in the game.
 *
 * @author evelyn
 * @category Cards
 */
public final class Card {
	final protected String NAME;
	final protected String IMAGE;

	final protected int priority;
	protected int uses, maxUses;

	public Action action;
	/**
	 * 0-arg constructor
	 */
	public Card() {
		this.NAME = "";
		this.IMAGE = "";
		this.priority = 1;
		this.action = Action.NONE;
	}

	/**
	 * 3-arg constructor.
	 *
	 * @param name        Name of card.
	 * @param description Description of card.
	 * @param image       Card image.
	 */
	public Card(String name, String image) {
		this.NAME = name;
		this.IMAGE = image;
		this.priority = 1;
		this.action = Action.NONE;
	}
	/**
	 * 3-arg Constructor
	 * @param name Name of the card.
	 * @param image Image of the card.
	 * @param priority Card priority when played
	 */
	public Card(String name, String image, int priority) {
		this.NAME = name;
		this.IMAGE = image;
		this.priority = priority;
		this.action = Action.NONE;
	}

	/**
	 * Gets the name of the card.
	 *
	 * @return Name of the card.
	 */
	public String getName() {
		return this.NAME;
	}

	/**
	 * Gets the priority of the card.
	 *
	 * @return Description of the card.
	 */
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Gets the name of the card.
	 *
	 * @return Name of the card.
	 */
	public String getImage() {
		return this.IMAGE;
	}

	/**
	 * Triggers a card's effects. This method is not to be called, and should later
	 * be implemented to throw an error that is handled appropriately as such.
	 *
	 * @throws Exception
	 */
	public void trigger() {
		System.out.println("Unimplemented Superclass Method");
	}

	@Override
	public String toString() {
		return this.NAME;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Card) {
			Card temp = (Card) o;
			if (temp.getName().equals(this.NAME)) {
				return true;
			}
		}
		return false;
	}
}
