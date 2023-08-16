import java.util.*;

/** Implements the HouseCard object.
 *  HouseCards are cards the players will draw and take when they land on the Buy a House space.
 *
 */
public class HouseCard extends Card {
	private	int	value;
	
	
	public static final String[] TITLES = {
		"Basic Apartment",
		"High-rise Condominium Unit",
		"Two-Story Suburban House",
		"Urban Residential",
		"Private Manor"
	};
	
	public static final String[] DESCRIPTIONS = {
		"A studio-sized apartment unit. Worth $75 000.",
		"A condo unit in the high-rise buildings of the city. Worth $100 000.",
		"A standard-layout dwelling located in the suburbs. Worth $125 000.",
		"A residence built on private land within the city. Worth $175 000.",
		"A private real estate located in an exclusive subdivision. Worth $250 000."
	};
	
	public static final int[] PRICES = {
		75000,
		100000,
		125000,
		175000,
		250000
	};
	private static int numCard = 1;
	
	/** The constructor for the HouseCard.
	 * Randomizes the amount of salary by multiples of 10,000. Its tax is equal to 10% of its salary.
	 *
	 */
	public HouseCard () 
	{	
		title = TITLES[numCard-1];
		value = PRICES[numCard-1];
		description = DESCRIPTIONS[numCard-1];
		numCard++;
	}
	
	/** returns the title of the HouseCard 
	 *
	 * @return String the title of the HouseCard
	 */
	  @Override
	public String getTitle () {
		return title;
	}
	
	/** returns the description of the HouseCard
     *  
	 * @return String description of the HouseCard
	 */ 
	  @Override
	public String getDescription () {
		return description;
	}
	
	/** returns the value of the HouseCard
     *  
	 * @return int value of the HouseCard
	 */ 
	public int getValue () {
		return value;
	}
	
	/** This method is called when the player draws the card. The player will have the salary and tax the card contains
	 * The player who drew the card is the target.
	 * @param target the player who drew the card
	 *
	 */
	  @Override
	public boolean apply (Player target)//house
	{
		if(target.getMoney() >= value){
			target.setHouseCard(this);
			target.changeMoney(value * - 1);
			return true;
		}
		return false;
	}
	
	@Override
	/** Overrides the method and returns its attributes as a String 
	 *
	 * @return String the string displaying the attributes of the cards
	 */
	public String toString () {
		return ("[" + title + "] "+ description);
	}
	
	@Override
	/** Overrides the method and returns if two ActionCards are the same .
	 * Compares the cards' title if they are the same
	 *
	 * @return boolean returns true if same and false if not
	 */
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return ((HouseCard) o).getTitle() == getTitle();
	}
}