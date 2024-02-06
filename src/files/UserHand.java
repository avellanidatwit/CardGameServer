package files;

public class UserHand extends Deck {
	
	final protected String handOwner;
	
	public UserHand(String handOwner) {
		this.handOwner = handOwner;
	}
	
	public String getHandOwner() {
		return handOwner;
	}
}
