package files;
/**
 * Interface for the sole purpose of setting up cards, which each card type will be defined in its own class, therefore implementing this interface.
 * @author evelyn
 */
public interface CardEffects extends DealerControls {
	/**
	 * Gets a cards effects.
	 * @return The card's effects.
	 */
	String getEffects();
	/**
	 * Sets a cards effects.
	 * @param e effects to mirror from a card.
	 */
	void setEffects(Card e);
	/**
	 * Gets a card's type.
	 * @return The card's type.
	 */
	String getCardType();
}
