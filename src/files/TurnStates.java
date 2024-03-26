package files;
/**
 * Enum for the purpose of controlling the turns
 * @author evelyn
 */
public enum TurnStates {
	/**
	 * Activates the 1st user's turn.
	 */
	USER1PLAY,
	/**
	 * Activates the 2nd user's turn.
	 */
	USER2PLAY,
	/**
	 * Syncs the hands/deck.
	 */
	DISCARD,
	/**
	 * Checks for a win condition.
	 */
	CHECKWINCONDITIONS
}
