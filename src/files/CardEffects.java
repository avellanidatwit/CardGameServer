package files;
/**
 * Interface for the sole purpose of setting up cards, which each card type will be defined in its own class, therefore implementing this interface.
 * @author evelyn
 */
public interface CardEffects {
	/**
	 * Gets a cards effects.
	 * @return The card's effects.
	 */
	String getEffects();
	/**
	 * Sets a cards effects.
	 * @param e effects to set.
	 */
	void setEffects(Object e);
	/**
	 * Triggers the effects on the other users/cards on the field.
	 */
	void triggerEffects();
	/**
	 * Method to check if a given card has interactions with another given card.
	 * @param c Card to check against.
	 */
	void checkInteractions(Card c);
	/**
	 * Method to check if there are interactions with any given cards that are in play at that time.
	 */
	void checkInteractions();
	/**
	 * Returns the type of card.
	 * @return Card type to return.
	 */
	String getCardType();
}
