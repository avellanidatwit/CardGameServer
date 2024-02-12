package files;

import java.util.Random;
/**
 * Card that reveals cards in deck/other player's hand.
 * @author evelyn
 */
public final class RevealCard extends Card implements CardEffects {
	/**
	 * 3 arg constructor.
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 * @param level Level of card.
	 */
	public RevealCard(String NAME, String DESCRIPTION, CardLevel level) {
		super(NAME, DESCRIPTION, level, CardTypes.REVEALER);
	}
	/**
	 * 2 arg constructor.
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 */
	public RevealCard(String NAME, String DESCRIPTION) {
		super(NAME, DESCRIPTION, CardLevel.LV0, CardTypes.REVEALER);
	}
	/**
	 * Triggers a card's effects.
	 */
	@Override
	public void trigger() {
		
	}

	@Override
	public CardTypes getCardType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCardType(CardTypes t) {
		// TODO Auto-generated method stub

	}
	@Override
	public void trigger(Card c) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Triggers a card's effects on a hand
	 * @param c Card to find.
	 * @param h Hand to look in.
	 */
	public String trigger(Card c, UserHand h) {
		for(Card current : h.getCards()) {
			if(current.equals(c)) {
				return "Card Found.";
			}
		}
		return "Card Not Found.";
	}
	/**
	 * Returns a certain number of cards from a hand.
	 * @param noOfCards Number of cards to pull.
	 * @param h Hand to pull from.
	 * @return Returns the cards assigned by the randomizer, and the total is equivalent to number of cards.
	 */
	public Card[] cardFinder(int noOfCards, UserHand h) {
		Random randomFinder = new Random((long) Math.log(h.getNumberOfCards()));
		Card[] cardRetVal = new Card[noOfCards];
		int[] randomValues = new int[noOfCards];
		for(int i = 0; i < randomValues.length; i++) {
			randomValues[i] = randomFinder.nextInt();
		}
		for(int i = 0; i < cardRetVal.length; i++) {
			cardRetVal[i] = h.getCards().get(randomValues[i]);
		}
		return cardRetVal;
	}
	private Card[] cardFinder(int noOfCards, Deck d) {
		Random randomFinder = new Random((long) Math.log(d.getNumberOfCards()));
		Card[] cardRetVal = new Card[noOfCards];
		int[] randomValues = new int[noOfCards];
		for(int i = 0; i < randomValues.length; i++) {
			randomValues[i] = randomFinder.nextInt();
		}
		for(int i = 0; i < cardRetVal.length; i++) {
			cardRetVal[i] = d.getCards().get(randomValues[i]);
		}
		return cardRetVal;
	}
	/**
	 * Triggers a card's effects, returning an entire hand.
	 * @param h Hand to pull from.
	 * @return The hand.
	 */
	public Card[] trigger(UserHand h) {
		return (Card[]) h.getCards().toArray();
	}
	 
	public void trigger(Deck d, UserHand h) {
		Card[] cards = cardFinder(5, d);
		//TODO: add user integration to actually getting the cards.
		for(Card c : cards) {
			h.addCard(c);
		}
	}

}
