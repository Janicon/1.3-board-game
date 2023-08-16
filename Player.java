import java.util.*;

/** Implements a Player object that will participate in the ThatsLife game.
 *  The Player's career is only assigned for now and can only perform actions derived from the Action Cards.
 */
public class Player {
	private	String	name; 
	private	long money;
	private long loan;
	
	//attributes for position
	private int pos; //numerical position of the player in the board or in a junction 
	private int inJunction; //index for the junction the player is currently on
	
	
	//attributes for CareerCard
	private String career; 
	private boolean hasDegree; 
	private CareerCard careerCard;
	
	
	//attributes for SalaryCard
	private SalaryCard salaryCard;
	private int salary;
	private int tax;
	private int timesRaised;
	
	//attribute for HouseCard
	private HouseCard home;
	
	//attribute for Marriage
	private boolean isMarried;
	private int numChildren;
	
	public static int playerNum = 1;
	
	/* The given careers that a Player can have. These are assigned for now but can be chosen by the players
	 *
	 */
	private static final String[] CAREERS = {
		"Lawyer",
		"Accountant",
		"Computer Consultant",
		"Doctor",
		"Server",
		"Racecar Driver",
		"Athlete"
	};
	
	/** Creates a Player object with a starting money of 200,000
	 *  Initializes name and career based on the number of Players created
	 *  
	 *
	 */
	public Player () {
		name = "Player " + playerNum;
		money = 200000;
		loan = 0;
		
		inJunction = -1;
		pos = -1;
		
		this.career = CAREERS[playerNum - 1];
		hasDegree = false;
		
		salary = 10000;
		tax = 0;
		timesRaised = 0;
		
		home = null;
		isMarried =  false;
		numChildren = 0;
		
		playerNum++;
	}
	
	/** Creates a Player object with the String passed as their name. When their name is Bank, they are considered as the game's bank and not a player 
	 *  
	 * 
	 * @param name the name of the player. Bank will not be treated as a Player but a separate entity of the Player object 
	*/
	public Player (String name) {
		this ();
		this.name = name;
	}
	
	
	/** returns the name of the Player 
	 * 
	 *
	 * @return String name of the Player
	 */
	public String getName () {
		if(name == null)
			return null;
		return name;
	}
	
	/** returns the money of the Player 
	 * 
	 *
	 * @return int money of the Player
	 */
	public long getMoney () {
		return money;
	}
	
	/** returns the loan or payable of the Player 
	 * 
	 *
	 * @return loan amount of money the Player has to pay 
	 */
	public long getLoan()
	{
		return loan;
	}
			
	/** returns the position of the Player on the board 
	 * 
	 *
	 * @return pos Position of the player on the board 
	 */
	public int getPos()
	{
		return pos;
	}
	
	/** returns the index of the junction the player is on
	 * 
	 *
	 * @return junction the index for the player's junction
	 */
	public int getInJunction()
	{
		return inJunction;
	}
	
	
	/** returns the career of the Player 
	 * 
	 *
	 * @return career Career or occupation of the Player
	 */
	public String getCareer()
	{
		if(careerCard == null)
			return null;
		return careerCard.getTitle();
	}
	
	/** returns the degree of the player
	 * 
	 *
	 * @return degree returns true if they have a degree and false otherwise
	 */
	public boolean getDegree()
	{
		return hasDegree;
	}

	/** returns the CareerCard the player is currently holding 
	 * 
	 *
	 * @return CareerCard the CareerCard the player owns 
	 */
	public CareerCard getCareerCard()
	{
		if(careerCard == null)
			return null;
		return careerCard;
	}
	
	/** returns the SalaryCard the player is currently holding 
	 * 
	 *
	 * @return SalaryCard the SalaryCard the player owns 
	 */
	public SalaryCard getSalaryCard()
	{
		if(salaryCard == null)
			return null;
		return salaryCard;
	}
	
	/** returns the salary of the Player 
	 * 
	 *
	 * @return int salary of the Player
	 */
	public int getSalary()
	{
		return salary;
	}
	
	/** returns the tax due of the Player 
	 * 
	 *
	 * @return int tax of the Player
	 */
	public int getTax()
	{
		return tax;
	}
	
	/** returns the number of times the player received a pay raise 
	 * 
	 *
	 * @return timesRaised the number of times hte player has received a pay raise 
	 */
	public int getTimesRaised()
	{
		return timesRaised;
	}
	
	/** returns the maximum number of pay raises the player can have
	 * 
	 *
	 * @return raiseLimit the CareerCard the player owns 
	 */
	public int getRaiseLimit()
	{
		if(careerCard == null)
			return 0;
		return careerCard.getRaiseLimit();
	}
	
	/** returns the HouseCard the player is currently holding 
	 * 
	 *
	 * @return HouseCard the CareerCard the player owns 
	 */
	public HouseCard getHouseCard()
	{
		if(home == null)
			return null;
		return home;
	}
	
	/** returns if the player is married 
	 * 
	 *
	 * @return isMarried returns  the CareerCard the player owns 
	 */
	public boolean getIsMarried()
	{
		return isMarried;
	}
	
	/** returns the number of children the player has 
	 * 
	 *
	 * @return numChildren the number of chidlren the player has 
	 */
	public int getNumChildren()
	{
		return numChildren;
	}
	
	/** sets the career of the player. This is based on their CareerCard
	 * 
	 *
	 * @param title the title or the career the player will have  
	 */
	public void setCareer(String title)
	{
		career = title;
	}
	 
	 /** sets the hasDegree of the player to true
	 * 
	 *
	 *
	 */
	public void graduate()
	{
		hasDegree = true;
	}
	
	/** Assigns the CareerCard to the class itself
	 * 
	 *
	 * @param card the CareerCard player chose to take 
	 */
	public void setCareerCard(CareerCard card)
	{
		careerCard = card;
	}
	
	/** Assigns the SalaCard to the class itself
	 * 
	 *
	 * @param card the SalaryCard the player will chose to take
	 */
	public void setSalaryCard(SalaryCard card)
	{
		salaryCard = card;
	}
	
	/** Assigns the HouseCard to the class itself
	 * 
	 *
	 * @param card the HouseCard the player will have 
	 */
	public void setHouseCard(HouseCard card)
	{
		home = card;
	}
	
	/** Removes the HouseCard the player currently has by assigning it as null
	 * 
	 *
	 * 
	 */
	public void removeHouseCard()
	{
		home = null;
	}
	
	/** Sets the times raised of the Player. Called when the player changes CareerCards
	 * 
	 *
	 * @param num the new value for the player's timesRaised 
	 */
	public void setTimesRaised(int num)
	{
		timesRaised = num;
	}
	
	/** Sets the index of the junction at which the Player is currently on
	 * 
	 *
	 * @param index the index of the junction the player is currently standing on.
	 */
	public void setInJunction(int index)
	{
		inJunction = index;
	}
	
	/** Sets the salary and tax of the Player. This is called when the Player receives a SalaryCard
	 * 
	 *
	 * @param salary the amount of money the player will receive on payday
	 * @param tax the amount of money the player will pay for his tax dues.
	 */
	public void setSalaryAndTax(int salary, int tax)
	{
		this.salary = salary;
		this.tax = tax; 
	}
	
	/** Increases the salary and taxes of the player. This is called in the pay raise space or tile
	 * 
	 *
	 * @param salaryRaise the additional amount of money the player will receive on pay day.
	 * @param taxRaise the additional amount of payment for tax dues of the player.
	 */
	public void increaseSalaryAndTax(int salaryRaise, int taxRaise)
	{
		salary += salaryRaise;
		tax += taxRaise;
		timesRaised++;
	}
	
	/** Increments the position of the player by 1. Called inside a loop to navigate around the board
	 * 
	 *
	 *
	 */
	public void move()
	{
		pos++;
	}
	
	/** Sets the position of the player on the board 
	 * 
	 *
	 * @param num the new position of the player in the board.
	 */
	public void setPos(int num)
	{
		pos = num;
	}
	
	/** Adds the number of children the player has. This is called in the have babies spaces or tiles
	 * 
	 *
	 * @param num the additional number of children the player will have 
	 */
	public void addNumChildren(int num)
	{
		numChildren += num;
	}
	
	/** Sets the isMarried attribute of the player into true.
	 * 
	 *
	 *
	 */
	public void getMarried()
	{
		isMarried = true;
	}
	
	
	/** increases or decreseas the money of the Player based on the amount passed. 
	 *  This is used when the Player receives an Action Card that allows them to send or receive money. 
	 * 
	 *  @param amount that the Player will either receive or send to a target
	 */
	public void changeMoney (long amount) {
		this.money += amount;
	}		
	
	public void changeLoan (long amount)
	{
		loan += amount;
	}
	
	/** Transacts the loan of the player based on the count in the bank screen. 
	*	If the count is negative, this means that they are paying the loan. Otherwise, they are taking the loan.
	 * 
	 *
	 * @param count the number of ticks the player entered in the bank screen interface
	 */
	public void transactLoan (int count) {
		if (count > 0) {
			changeMoney (count * 20000);
			changeLoan (count * 25000);
		}
		else if (count < 0 && this.loan >= count * -25000) {
			changeMoney (count * 25000);
			changeLoan (count * 25000);
		}
	}
	
	@Override
	/** Overrides the method and returns the player;s attributes as a String
	 *
	 * @return String the string displaying the attributes of the player
	 */
	public String toString()
	{
		return(name + ", M:" + money  + ", C:" + career + ", P:" + pos);
	}

	@Override
	/** Overrides the method and checks if the player has the same name as theirs.
	 *
	 * @return boolean returns true if player has the same name as the other.
	 */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		return ((Player) obj).getName() == getName();
	}
	
}