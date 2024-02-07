package files;
/**
 * Controls for the dealer object
 * @author evelyn
 */
public interface DealerControls {
	/**
	 * Triggers the effects on the other users/cards on the field.
	 */
	void triggerEffects();
	/**
	 * Triggers effects based upon a given card, if it is in play.
	 * @param c Card to check against.
	 */
	void triggerEffects(Card c);
	/**
	 * Method to check if a given card has interactions with another given card.
	 * @param c Card to check against.
	 */
	void checkInteractions(Card c);
	/**
	 * Method to check if there are interactions with any given cards that are in play at that time.
	 */
	void checkInteractions();

}
