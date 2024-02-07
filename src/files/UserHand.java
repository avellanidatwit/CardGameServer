package files;

public class UserHand extends Deck {
	
	final protected String handOwner;
	
	public UserHand(String handOwner) {
		this.handOwner = handOwner;
	}
	
	public String getHandOwner() {
		return handOwner;
	}
	
	@Override
	public String toString() {
		return "Number of Cards: " + super.numberOfCards + "\nCards: " + super.cards + "\nHand Owner: " + this.handOwner;
	}
}
