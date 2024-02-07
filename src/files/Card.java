package files;

public class Card {
	final protected String name;
	final protected String description;
	
	public Card(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Card() {
		this.name = null;
		this.description = null;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\nDescription: " + this.description;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Card) {
			Card temp = (Card) o;
			if(temp.getName().equals(this.name) && temp.getDescription().equals(this.description)) {
				return true;
			}
		}
		return false;
	}
}
