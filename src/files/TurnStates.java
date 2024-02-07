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
	 * Get's the effects from cards played.
	 */
	GETEFFECTS,
	/**
	 * Triggers the effects from cards played.
	 */
	TRIGGEREFFECTS,
	/**
	 * Syncs the hands/deck.
	 */
	SYNCDECK,
	/**
	 * Checks for a win condition.
	 */
	CHECKWINCONDITIONS,
	/**
	 * Resets turn
	 */
	RESET,
	/**
	 * Waits for users to sync.
	 */
	AWAITFORSYNC
}
