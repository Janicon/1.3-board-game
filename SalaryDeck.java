import java.util.*;

/** Implements an SalaryDeck object containing SalaryCards, each containing a salary the player will collect in payday and a tax the player will pay. It is also responsible for generating, shuffling,
 *  and drawing the cards.
 *
 */
public class SalaryDeck extends Deck {
	private ArrayList<SalaryCard> cards;
	
	private static final int SALARYCARDNUM = 10;
	private int numCard;
	
	/** Creates a Deck object containing an array list of SalaryCards. 
	 *  Each SalaryCard has its values randomly generated at the time of its initialization.
	 */
	public SalaryDeck () {
		cards = new ArrayList<> ();
		nextCard = 0;
		numCard = 0;
		
		for (int i = 0; i < SALARYCARDNUM; i++){
			cards.add (new SalaryCard ());
			numCard++;
		}
		
		shuffle();
	}
	
	/** Shuffles the SalaryCards generated in the deck. This method is called after every generation of the cards and if all the cards are drawn
	 * 
	 */
	 @Override
	public void shuffle () {
		Collections.shuffle (cards);
	}
	
	/** collects and returns the top card of the list. The top card is based on the number of cards drawn (nextCard) throughout the game. For example,
	 *  If no cards are drawn yet, nextCard points to the first SalaryCard in the list.
	 *
	 *  @return top SalaryCard in the deck.
	 */
	public SalaryCard draw () {
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
	 *  @param card the SalaryCard the player chose to take
	 */
	public void removeCard(SalaryCard card)
	{
		cards.remove(card);
		numCard --;
		nextCard --;
	}
	
	/** Returns the SalaryCard drawn by the player in the list. This is called when the player replaces their card with another.
	 *  
	 *
	 *  @param card the SalaryCard the player chose to take
	 */
	public void returnCard(SalaryCard card)
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