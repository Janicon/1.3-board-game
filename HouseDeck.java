import java.util.*;

/** Implements an HouseDeck object containing HouseCards, each containing title and description, and the value. It is also responsible for generating, shuffling,
 *  and drawing the cards.
 *
 */
public class HouseDeck extends Deck {
	private ArrayList<HouseCard> cards;
	private static final int HOUSECARDNUM = 5;
	private int numCard;
	
	/** Creates a Deck object containing an array list of HouseCards. 
	 *  
	 */
	public HouseDeck () {
		cards = new ArrayList<> ();
		nextCard = 0;
		numCard = 0;
		
		for (int i = 0; i < HOUSECARDNUM; i++){
			cards.add (new HouseCard ());
			numCard++;
		}
	}
	
	/** Shuffles the HouseCards generated in the deck. This method is called after every generation of the cards and if all the cards are drawn
	 * 
	 */
	public void shuffle () {
	}
	
	/** collects and returns the top card of the list. The top card is based on the number of cards drawn (nextCard) throughout the game. For example,
	 *  If no cards are drawn yet, nextCard points to the first HouseCard in the list.
	 *
	 *  @return top HouseCard in the deck.
	 */
	 	  @Override
	public HouseCard draw () {
		if ((nextCard++) >= numCard)
		{
			nextCard = 1;
		}
		return cards.get (nextCard - 1);
	}
	
	/** Removes the HouseCard drawn by the player from the list. This is called when the player takes the card and keeps it.
	 *  The nextCard is also decremented to point to the right top card in the deck.
	 *
	 *  @param card the HouseCard the player will choose and take
	 */
	public void removeCard(HouseCard card)
	{
		cards.remove(card);
		numCard --;
		nextCard --;
	}
	
	/** Returns the HouseCard drawn by the player in the list. This is called when the player replaces their card with another.
	 *  
	 * @param card the HouseCard the player previously had before choosing and taking another one.
	 *  
	 */
	public void returnCard(HouseCard card)
	{
		if(cards.contains(card) == false){
			cards.add(card);
			numCard++;
		}
	}
	
	/** Returns the number of cards in the deck
	 *
	 *   @return int the number of cards in the deck
	 */
	 	  @Override
	public int getDeckSize () {
		return numCard;
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