import java.util.*;

/** Implements the ActionCard object.
 *  ActionCards command a player to receive or send an amount of money to a target. They indicate specific sources and targets, ranging from other players to the bank,
 *  that will participate in the action and their values differ based on the titles.
 */
public class ActionCard extends Card {
	private	int		type;
	private	int		amount;	
	private boolean hasTarget;
	
	/* The titles of the 4 types of the ActionCard
	 * Used to assign its title attribute
	 */
	public static final String[] TITLES = {
		"Collect from bank",
		"Pay bank",
		"Pay player",
		"Collect from player"
	};
	
	/* The possible descriptions of the ActionCard based on its type
	 * The description is randomly chosen during its generation
	 */
	public static final String[][] DESCRIPTIONS = {
		
		//Collect from bank descriptions
		{	
			"Tax refund",
			"Sell an item",
			"Bonus payday",
			"Setup school",
			"Write a book"
		},
		//Pay Bank descriptions
		{	
			"Buy an item",
			"Visit a place",
			"Hiking",
			"Watch a show",
			"Win a competition",
			"Traffic violation"
		},
		//Pay Player descriptions
		{
			"Lawsuit",
			"Christmas Bonus"
		},
		//Collect from player descriptions
		{
			"File a lawsuit",
			"It's your Birthday"
		}
	};
	
	/** The default constructor for the ActionCard
	 *
	 */
	public ActionCard () {
	}
	
	/**	creates an ActionCard based on the type given during the deck's generation.
	 *  The description is randomized based on its type
	 *
	 *  @param type the type of ActionCard passed in the deck's generation
	 */
	public ActionCard (int type) {
		Random rand = new Random ();
		int	index = rand.nextInt (DESCRIPTIONS[type - 1].length);
		this.type = type;
		this.title = TITLES[type - 1];
		this.amount = genAmount ();
		this.description = DESCRIPTIONS[type - 1][index];
		this.hasTarget = false;
		
		//If the type pays or collects from another player and the description requires choosing that player
		if(description == DESCRIPTIONS[2][0] || description == DESCRIPTIONS[3][0]){
			this.hasTarget = true;
		}
	}
	
	/** returns the type of ActionCard, which will be used for the reading of the  Card
	 *
	 * @return type the type of ActionCard
	 */
	public int getType () {
		return this.type;
	}
	
	/** returns the title of the ActionCard based on its title
	 *
	 * @return String the title of the ActionCard
	 */
	  @Override
	public String getTitle () {
		return this.title;
	}
	
	/** returns the amount that can be received or sent using this ActionCard
	 *
	 * @return int amount the amount of money 
	 */
	public int getAmount () {
		return this.amount;
	}
	
	/** returns the description of the ActionCard
     *  
	 * @return String description of the card
	 */ 
	  @Override
	public String getDescription () {
		return this.description;
	}
	
	public boolean getHasTarget()
	{
		return this.hasTarget;
	}
	
	/** randomly generates the amount of the ActionCard and returns it.
	 * Generates from 5000 to 30000
	 * 
	 * @return int nReturn the amount of money the ActionCard will have
	 */
	private int genAmount () {
		int nReturn;
		Random rand = new Random ();
		nReturn = rand.nextInt (6) + 1;
		return nReturn * 5000;
	}
	
	/** This method is called when the action card drawn by the player allows them to choose another player to pay or collect.
	 * The player who drew the card is the source and the player they chose will be the target of the action in the card.
	 * If Type 3, They will send money to the player they chose. If Type 4, they will receive money from the player chosen
	 *
	 * @param source the player who drew the card
	 * @param target the player chose or will be targeting based on the ActionCard
	 *
	 * @return boolean whether the application of the ActionCard is successful.
	 */
	public boolean apply (Player source, Player target) { //action with target
		boolean success = false;
		switch (type) 
		{
			case 3: {
				if(source.getMoney()>= amount ){
					source.changeMoney (amount * -1);
					target.changeMoney (amount);
					success = true;
				}
			} break;
			case 4: {
				source.changeMoney (amount);
				target.changeMoney (amount * -1);
				success = true;
			}
		}
		return success;
	}
	/** This method is called when the player collects or pays the bank
	 * The player who drew the card is the target.
	 * If Type 1, they will receive money. If Type 2, they will send money.
	 * @param target the player who drew the card
	 *
	 */
	 @Override
	public boolean apply (Player target) // action without
	{
		boolean  success = false;
		switch (type)
		{
			case 1: 
			{
				target.changeMoney(amount);
				success = true;
			} break;
			case 2:
			{
				if (target.getMoney() >= amount ){
					target.changeMoney(amount * -1);
					success = true;
				}
			}break;
		}
		return success;
	
	}
	
	@Override
	/** Overrides the method and returns its attributes as a String 
	 *
	 * @return String the string displaying the attributes of the cards
	 */
	public String toString () {
		return ("[" + title + "] " + description + ": " + amount);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return (((ActionCard) o).getTitle() == getTitle()) && (((ActionCard) o).getDescription() == getDescription()) ;
	}
}