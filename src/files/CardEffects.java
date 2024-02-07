package files;
/**
 * Interface for the sole purpose of setting up cards, which each card type will be defined in its own class, therefore implementing this interface.
 * @author evelyn
 */
public interface CardEffects {
	String getEffects();
	
	void setEffects(Object e);
	
	void triggerEffects();
	
	void checkInteractions(Card c);
	
	void checkInteractions();
	
	String getCardType();
}
