import java.util.*;

/** Implements a CareerDeck object containing CareerCards Players will keep during the game. It is also responsible for generating, shuffling,
 *  and drawing the cards.
 *
 */
public class CareerDeck extends Deck {
	private ArrayList<CareerCard> cards;
	
	private static final int CAREERCARDNUM = 7;	
	private int numCard;
	/** Creates a Deck object containing an array list of CareerCards.
	 * 
	 */
	public CareerDeck () {
		cards = new ArrayList<> ();
		nextCard = 0;
		numCard = 0;
		
		for (int i = 0; i < CAREERCARDNUM; i++){
			cards.add (new CareerCard ());
			numCard++;
		}
		
		shuffle();
	}
	
	/** Shuffles the CareerCards generated in the deck. This method is called after every generation of the cards or when all the cards are drawn.
	 * 
	 */
	public void shuffle () {
		Collections.shuffle (cards);
	}
	
	/** collects and returns the top card of the list. The top card is based on the number of cards drawn (nextCard) throughout the game. For example,
	 *  If no cards are drawn yet, nextCard points to the first CareerCard in the list.
	 *
	 *  @return top CareerCard in the deck.
	 */
	public CareerCard draw () {
		if ((nextCard++) >= cards.size())
		{
			shuffle();
			nextCard = 1;
		}
		return cards.get (nextCard - 1);
	}
	
	/** Removes the SalaryCard drawn by the player from the list. This is called when the player takes the card and keeps it.
	 *  The nextCard is also decremented to point to the right top card in the deck.
	 *
	 *  @param card the CareerCard that the player will choose and take
	 */
	public void removeCard(CareerCard card)
	{
		cards.remove(card);
		numCard --;
		nextCard --;
	}
	
	/** Returns the SalaryCard drawn by the player in the list. This is called when the player replaces their card with another.
	 *  
	 * @param card the CareerCard that the player previously had before taking another.
	 *  
	 */
	public void returnCard(CareerCard card)
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