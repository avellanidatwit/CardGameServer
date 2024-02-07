package files;
/**
 * Class to establish a user's identity & hand.
 * @author evelyn
 */
public class User {
	
	protected UserHand hand;
	final protected String USERNAME;
	/**
	 * 1-arg Constructor
	 * @param username A user's username.
	 */
	public User(String username) {
		this.USERNAME = username;
	}
	/**
	 * Returns the user's username.
	 * @return The user's username.
	 */
	public String getUsername() {
		return this.USERNAME;
	}
	/**
	 * Returns the user's hand.
	 * @return The user's hand.
	 */
	public UserHand getUserHand() {
		return this.hand;
	}
	@Override
	public String toString() {
		return "Username: " + this.USERNAME + "\nCurrent Hand: " + this.hand;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof User) {
			User temp = (User) o;
			if(this.USERNAME.equals(temp.getUsername())) {
				return true;
			}
		}
		return false;
	}
}
