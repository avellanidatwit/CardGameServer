package files;
/**
 * Class to establish a user's identity & hand.
 * @author evelyn
 */
public final class User extends JavaClient {
	
	protected Deck hand;
	final protected String USERNAME;
	protected Deck pile;
	protected TrashDiscard trash;
	protected TrashDiscard discard;
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
		this.hand = null;
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
		this.hand = h;
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
		this.hand = h;
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
	/**
	 * Returns the user's hand.
	 * @return The user's hand.
	 */
	public Deck getUserHand() {
		return this.hand;
	}
	@Override
	public String toString() {
		return "Username: " + this.USERNAME + "\nCurrent Hand: " + this.hand;
	}
	/**
	 * Method to set the discard/trash items.
	 * @param s item to set.
	 * @param switcher if true, set trash, if false, set discard.
	 */
	public void setDiscardTrash(TrashDiscard s, boolean switcher) {
		if(switcher) {
			this.trash = s;
		}
		else {
			this.discard = s;
		}
	}
	/**
	 * Method to get the discard/trash
	 * @param switcher if true, get trash, if false, get discard.
	 * @return Returned trash/discard.
	 */
	public TrashDiscard getDiscardTrash(boolean switcher) {
		if(switcher) {
			return this.trash;
		}
		else {
			return this.discard;
		}
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
