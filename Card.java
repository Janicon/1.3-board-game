

/** An abstract class serving as a template for the different types of cards in the game.
 * 	It contains a title and a description and the getters for it. It contains an apply method which affects the player who drew it.
 *  
 */ 
public abstract class Card {
	protected String title;
	protected String description;
	
	/** Getter for the title of the card.
	 * @return String the title of the card
	 * 
	 *
	 */
	public abstract String getTitle();
	
	/** Getter for the description of the card.
	 * @return String the description of the card
	 * 
	 *
	 */
	public abstract String getDescription();
	
	/** Applies the effects of the card to the player who drew it
	 * @param target The player who drew the card.
	 * 
	 * @return whether the application of the card is successful;
	 */
	public abstract boolean apply (Player target);
	
	/** The string representation of the card
	 * @return String the string representation of the card.
	 * 
	 *
	 */ 
	public abstract String toString();
}