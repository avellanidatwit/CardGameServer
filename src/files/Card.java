package files;

import javafx.scene.image.Image;

/**
 * A class to make a card, for use in the game.
 * @author evelyn
 */
public final class Card implements CardEffects {
	final protected String NAME;
	final protected String DESCRIPTION;
	final protected Image IMAGE;
	protected int uses, maxUses;
	/**
	 * 3-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param image Card image.
	 */
	public Card(String name, String description, Image image) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.IMAGE = image;
	}
	/**
	 * No-arg constructor (mostly unused)
	 */
	public Card() {
		this.NAME = null;
		this.DESCRIPTION = null;
		this.IMAGE = null;
	}
	/**
	 * Gets the name of the card.
	 * @return Name of the card.
	 */
	public String getName() {
		return this.NAME;
	}
	/**
	 * Gets the description of the card.
	 * @return Description of the card.
	 */
	public String getDescription() {
		return this.DESCRIPTION;
	}
	/**
	 * Gets the name of the card.
	 * @return Name of the card.
	 */
	public Image getImage() {
		return this.IMAGE;
	}
	/**
	 * Triggers a card's effects. This method is not to be called, and should later be implemented to throw an error that is handled appropriately as such.
	 * @throws Exception 
	 */
	public void trigger() {
		System.out.println("Unimplemented Superclass Method");
	}
	
	@Override
	public String toString() {
		return "Name: " + this.NAME + " Description: " + this.DESCRIPTION;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Card) {
			Card temp = (Card) o;
			if(temp.getName().equals(this.NAME) && temp.getDescription().equals(this.DESCRIPTION)) {
				return true;
			}
			
		}
		return false;
	}
	@Override
	public CardTypes getCardType() {
		// TODO Auto-generated method stub
		return this.getCardType();
	}
	@Override
	public void trigger(Card c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCardType(CardTypes t) {
		// TODO Auto-generated method stub
		
	}
}
