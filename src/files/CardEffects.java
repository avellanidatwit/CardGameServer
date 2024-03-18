package files;
/**
 * Interface for the sole purpose of setting up cards, which each card type will be defined in its own class, therefore implementing this interface.
 * @author evelyn
 */
public sealed interface CardEffects permits Card {
	CardTypes getCardType();
	/**
	 * Sets a card type.
	 * @param t Card type to set
	 */
	void setCardType(CardTypes t);
	/**
	 * Triggers a card's effects.
	 */
	void trigger();
	/**
	 * Triggers a card's effects on another card.
	 * @param c
	 */
	void trigger(Card c);
}
