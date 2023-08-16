import java.util.*;

/** Implements an ActionDeck object containing ActionCards used by Players in the ThatsLife game. It is also responsible for generating, shuffling,
 *  and drawing the cards.
 *
 */
public class ActionDeck extends Deck {
	private ArrayList<ActionCard> cards;
	
	/* The number of ActionCards in the deck per type. First element of COUNTS refer to the number of 1st type ActionCards in the deck
	 */
	private static final int[] COUNTS = {20, 20, 5, 5};
	
	/** Creates a Deck object containing an array list of Action Cards. This class generates the values of each card for Player use in the ThatsLife game.
	 *  Each type of ActionCard are generated a number of times based on their specified frequency
	 */
	public ActionDeck () {
		cards = new ArrayList<> ();
		nextCard = 0;
		
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < COUNTS[i]; j++)
				cards.add (new ActionCard (i + 1));
	}
	
	/** Shuffles the ActionCards generated in the deck. This method is called after every generation of the cards
	 * 
	 */
	public void shuffle () {
		Collections.shuffle (cards);
	}

	/** collects and returns the top card of the list. The top card is based on the number of cards drawn (nextCard) throughout the game. For example,
	 *  If no cards are drawn yet, nextCard points to the first ActionCard in the list.
	 *
	 *  @return top ActionCard in the deck.
	 */
	public ActionCard draw () {
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