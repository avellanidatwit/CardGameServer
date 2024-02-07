package files;
/**
 * Class that establishes the user's private hand of cards.
 * 
 * @author evelyn
 */
public class UserHand extends Deck {
	
	final protected String handOwner;
	/**
	 * 1 arg constructor.
	 * @param handOwner owner of the hand (user's name)
	 */
	public UserHand(String handOwner) {
		this.handOwner = handOwner;
	}
	/**
	 * Gets the hand owner's name.
	 * @return The hand owner's name.
	 */
	public String getHandOwner() {
		return this.handOwner;
	}
	
	@Override
	public String toString() {
		return "Number of Cards: " + super.numberOfCards + "\nCards: " + super.cards + "\nHand Owner: " + this.handOwner;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof UserHand) {
			UserHand temp = (UserHand) o;
			if(temp.getHandOwner().equals(this.handOwner) && this.cards == temp.getCards() && this.numberOfCards == temp.getNumberOfCards()) {
				return true;
			}
		}
		return false;
	}
}
