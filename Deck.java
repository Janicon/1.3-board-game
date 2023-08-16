import java.util.*;

/** Implements a Deck object containing ActionCards used by Players in the ThatsLife game. It is also responsible for generating, shuffling,
 *  and drawing the cards.
 *
 */
public abstract class Deck {
	protected int nextCard;
	
	
	/** Shuffles the ActionCards generated in the deck. This method is called after every generation of the cards
	 * 
	 */
	public abstract void shuffle ();
	
	/** collects and returns the top card of the list. The top card is based on the number of cards drawn (nextCard) throughout the game. For example,
	 *  If no cards are drawn yet, nextCard points to the first ActionCard in the list.
	 *
	 *  @return top ActionCard in the deck.
	 */
	public abstract Card draw ();
	
	/** Returns the number of cards in the deck
	 *
	 *   @return int the number of cards in the deck
	 */
	public abstract int getDeckSize ();
	
	// /** Displays all the string representation of all the cards in the deck
	 // *
	 // *  
	 // */
	// public abstract void displayCards();
	
	
	
}