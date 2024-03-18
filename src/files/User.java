package files;
/**
 * Class to establish a user's identity and hand.
 * @author evelyn
 */
public final class User extends JavaClient {
	
	final protected String USERNAME;
	protected Deck pile;
	protected Deck trash;
	protected Deck discard;
	protected int id;
	/**
	 * 1-arg Constructor
	 * @param username A user's username.
	 */
	public User(String username) {
		if(id == 0) {
			this.id = 0;
		}
		else {
			this.id = id++;
			id++;
		}
		this.USERNAME = username;
		this.pile = null;
		this.trash = null;
		this.discard = null;
	}
	/**
	 * 2 arg constructor
	 * @param username A user's username.
	 * @param h The hand to set under the given user object.
	 */
	public User(String username, Deck h) {
		if(id == 0) {
			this.id = 0;
		}
		else {
			this.id = id++;
			id++;
		}
		this.USERNAME = username;
		this.pile = null;
		this.trash = null;
		this.discard = null;
	}
	public User(String username, Deck h, TrashDiscard t, TrashDiscard d) {
		if(id == 0) {
			this.id = 0;
		}
		else {
			this.id = id++;
			id++;
		}
		this.USERNAME = username;
		this.trash = t;
		this.discard = d;
	}
	/**
	 * Returns the user's username.
	 * @return The user's username.
	 */
	public String getUsername() {
		return this.USERNAME;
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
