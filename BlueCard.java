import java.util.*;

/** Implements the BlueCard object.
 *  BlueCards command a Player to pay in different ways. 
  * They also have a corresponding career, which the player who drew must have to collect a 15,000 bonus money.
 *  Otherwise, they play the player who has it or the bank if no one does.
 */
public class BlueCard extends Card
{
	private int type;
	private long amount;
	private String career;
	
	private static int numCard = 1;


	public static final String TITLES[] = {
								"Lawsuit", 
								"Salary Tax Due", 
								"Computer Repair", 
								"Ski Accident", 
								"Tip the Server", 
								"F1 Race", 
								"World Cup"};
	public static final String DESC[] = {
								"Pay amount: ", 
								"Pay your tax due", 
								"Press for a number. Pay 5,000 if even, 10,000 otherwise", 
								"Pay 10000", 
								"Press for a number and pay 1000 times the number", 
								"Pay 10% of your salary", 
								"Pay 5000 times number of players"
								};
	
	public BlueCard()
	{
		type = numCard;
		amount = 0;
		
		title = TITLES[numCard - 1];
		description = DESC[numCard - 1];
		career = CareerCard.CAREER[numCard-1];
		
		switch(type)
		{
			case 1:
			{
				amount = genAmount();
				description += amount;
			}break;
			case 4: 
			{
				amount = 10000;
			}break;
		}
		numCard++;
	}
	
	/** This method generates the amount player will pay when drawing [Lawsuit] BlueCard. 
 	  * Generates multiples of 1000 from 5000 to 15,000 and returns it.
	  *
	  *	@return int the amount generated to assign to the [Lawsuit] BlueCard.
	  */
	private int genAmount()
	{
		Random rand = new Random();
		int multiple = rand.nextInt (16 - 5) + 5;
		
		return 1000 * multiple;
	}
	
	/** returns the type of BlueCard, which will be used for the reading of the  Card
	 *
	 * @return type the type of BlueCard
	 */
	public int getType () {
		return this.type;
	}
	
	/** returns the title of the BlueCard based on its title
	 *
	 * @return String the title of the BlueCard
	 */
	  @Override
	public String getTitle () {
		return this.title;
	}
	
	/** returns the amount that can be received or sent using this BlueCard
	 *
	 * @return int amount the amount of money 
	 */
	public long getAmount () {
		return this.amount;
	}
	
	/** returns the description of the BlueCard
     *  
	 * @return String description of the card
	 */ 
	  @Override
	public String getDescription () {
		return this.description;
	}
	
	/** returns the career a player must have to receive a 15,000 bonus when this card is drawn.
     *  
	 * @return String career of the card
	 */ 
	public String getCareer()
	{
		return career;
	}
	
	/** Finds the player that has the same career as the card requires in order to receive a bonus.
     *  The player who drew it will receive the bonus if he does. Otherwise, the game finds the player who does.
	 *	If no one has the career the card requires, the player who drew it instead pays 15,000.
	 *  This is called everytime the BlueCard is drawn
	 *  @param players the ArrayList of players in the game. The card looks for the player who has the same career as the card requires.
	 *	@param player the Player who drew the card.
	 */
	 
	public void apply (ArrayList<Player> players, Player player)
	{
		boolean found = false; //represents whether a player with the same career as the card requires is found
		Player curPlayer; //Player object used for finding the player.
		if(player.getCareer() == career) //The player collects the bonus if they have the career the card requires.
		{
			player.changeMoney(15000);
			found = true;
		}
			
		for(int i = 0; i < players.size() && found == false; i++) //Looks for the player in the list that has the career.
		{
			curPlayer = players.get(i);
			if(curPlayer.getCareer() == career && !(player.getCareer() != (curPlayer.getCareer()) ))
			{
				(players.get(i)).changeMoney(15000);
				player.changeMoney(15000 * -1);
				found = true;
			}
		}
		
		if (found != true){ //the player who drew pays the bonus instead.
			player.changeMoney(15000 * -1);
		}
		
	}
	
	/** Applies the effects of the card as indicated in the specifications to the player who drew it.
     *  This is called when the effect does not require the player to press for a number.
	 *	
	 *	@param target the Player who drew the card.
	 */
	  @Override
	 public boolean apply(Player target) // blue
	{
		boolean success = false;
		switch  (type)
		{
			case 1: //Lawsuit 
			case 4: //Ski Accident 
			{
				if(target.getMoney() >= amount){
					target.changeMoney(amount * -1); //Player pays for the amount indicated in the card
					success =true;
				}
			} break;
			case 2: //Salary Tax Due
			{
				if(target.getMoney() >= target.getTax()){
					target.changeMoney(target.getTax() * -1); //Player pays for his tax dues
					success = true;
				}
			} break;
			case 6: //F1 Race
			{
				long percentage = (long) (target.getSalary() * .10); //Player pays for 10% of his calary
				if(target.getMoney() >= percentage){
					target.changeMoney(percentage * -1);
					success = true;
				}
			} break;
		}
		return success;
	}
	/** Applies the effects of the card as indicated in the specifications to the player who drew it.
     *  This is called when the effect requires the player to press for a number or requires or when it requires the number of players.  
	 *	
	 *	@param target the Player who drew the card.
	 *  @param num the random rumber the Player pressed for.
	 *	
	 *  @return boolean whether application of BlueCard is successful.
	 */
	 public boolean apply (Player target, int num)
	{
		boolean success = false;
		switch (type)
		{
			case 3: //Computer Repair
			{
				long amountGen;
				if (num % 2 == 0) //Amount is 5000 if even and 10,000 if even
					amountGen = 5000; 
				else
					amountGen = 10000;
				if(target.getMoney() >= amountGen){
					target.changeMoney(amountGen * -1);
					success = true;
				}				
			}break;
			case 5: //Tip the Server
			{
				long amountGen = 1000 * num;
				if(target.getMoney() >= amountGen){
					target.changeMoney(amountGen * -1); //Pays for 1000 times the random number pressed
					success = true;
				}
			} break;
			case 7: //World Cup
			{
				long amountGen = 5000 * num;
				if(target.getMoney() >= amountGen){
					target.changeMoney(amountGen * -1); //Pay for 5000 times the number of players in the game
					success = true;
				}
			}
		}
		return success;
	}
	
	@Override
	/** Overrides the method and returns its attributes as a String 
	 *
	 * @return String the string displaying the attributes of the cards
	 */
	public String toString()
	{
		return("[" + title + "] "+ description + "(Collect 15,000 if " + career + ")");
	}
	
	@Override
	/** Overrides the equals method and compares the two BlueCards 
	 *
	 * @return whether the two BlueCards have the same title;
	 */
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return ((BlueCard) o).getTitle() == getTitle();
	}
}