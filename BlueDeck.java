import java.util.*;

/** Implements an BlueDeck object containing HouseCards, each containing title and description, and the value. It is also responsible for generating, shuffling,
 *  and drawing the cards.
 *
 */
public class BlueDeck extends Deck {
	private ArrayList<BlueCard> cards;
	private static final int BLUECARDNUM = 7;
	
	/** Creates a Deck object containing an array list of HouseCards. 
	 *  
	 */
	public BlueDeck () {
		cards = new ArrayList<> ();
		nextCard = 0;
		
		for (int i = 0; i < BLUECARDNUM; i++)
			cards.add (new BlueCard ());
		
		shuffle();
	}
	
	/** Shuffles the BlueCards generated in the deck. This method is called after every generation of the cards and if all the cards are drawn
	 * 
	 */
	 @Override
	public void shuffle () {
		Collections.shuffle (cards);
	}
	
	/** collects and returns the top card of the list. The top card is based on the number of cards drawn (nextCard) throughout the game. For example,
	 *  If no cards are drawn yet, nextCard points to the first HouseCard in the list.
	 *
	 *  @return top BlueCard in the deck.
	 */
	 @Override
	public BlueCard draw () {
		if ((nextCard++) >= cards.size())
		{
			shuffle();
			nextCard = 1;
		}
		return cards.get (nextCard - 1);
	}
	
	/** Returns the number of cards in the deck
	 *
	 *   @return int the number of cards in the deck
	 */
	 @Override
	public int getDeckSize () {
		return cards.size ();
	}
	
	/** Returns the index for the top card in the deck
	 *
	 *   @return int the index for the top card in the deck
	 *
	 */
	public int getNextCard()
	{
		return nextCard;
	}
}