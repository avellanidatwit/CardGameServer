package files;
/**
 * Card that establishes a location.
 * @author evelyn
 */
public final class LocationCard extends Card {
	protected String locationName;
	/**
	 * No arg constructor.
	 */
	public LocationCard() {
		super();
		this.locationName = null;
	}
	/**
	 * 2 arg constructor.
	 * @param name Name of the card.
	 * @param description Description of the card.
	 */
	public LocationCard(String name, String description) {
		super(name, description, CardLevel.LVLOC, CardTypes.LOCATION);
		this.locationName = null;
	}
	/**
	 * 3 arg constructor.
	 * @param name Name of the card.
	 * @param description Description of the card.
	 * @param locationName Location as described on the card.
	 */
	public LocationCard(String name, String description, String locationName) {
		super(name, description, CardLevel.LVLOC, CardTypes.LOCATION);
		this.locationName = locationName;
	}
	/**
	 * Gets the location name.
	 * @return The location name.
	 */
	public String getLocationName() {
		return this.locationName;
	}
	/**
	 * Sets the name of the location on the card.
	 * @param s Name to set.
	 */
	public void setLocationName(String s) {
		this.locationName = s;
	}
	/**
	 * Gets the card type
	 * @return A string, set to location (Location card type never changes.)
	 */
	@Override
	public CardTypes getCardType() {
		return CardTypes.LOCATION;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.NAME + "\nDescription: " + this.DESCRIPTION + "\nLocation: " + this.locationName;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof LocationCard) {
			LocationCard temp = (LocationCard) o;
			if(this.locationName.equals(temp.getLocationName())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String getEffects() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setEffects(Card e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCardType(CardTypes t) {
		this.type = t;
		
	}
}
