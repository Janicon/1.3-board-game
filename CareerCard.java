import java.util.*;

/** Implements the CareerCard object.
 *  CareerCards dictate the career the player will have as well as the maximum of times they can get a pay raise.
 *  Each CareerCard also describe whether a degree is required to get it.
 */
public class CareerCard extends Card {
	private	boolean	degree;		
	private int raiseLimit;
	
	
	public static final String CAREER[] = {"Lawyer", "Accountant", "Computer Consultant", "Doctor", "Server", "Racecar Driver", "Athlete"};
	public static final boolean DEGREE[] = {true, true, true, true, false, false, false};
	public static final int RAISE_RANGE[][] = {{5,8},{4,7},{3,7},{5,8},{1,4},{2,8},{1,6}};
	private static int numCard = 1;
	
	/** The constructor for the CareerCard.
	 * Initializes the respective careers based on the constants above and the number of CareerCards initialized.
	 *
	 */
	public CareerCard () 
	{	
		title = CAREER[numCard-1] ;
		description = "Become a/an " + CAREER[numCard-1];
		degree = DEGREE[numCard-1];
		raiseLimit = genLimit(numCard-1);
		numCard++;

	}
	
	/** Randomly generates and returns the pay raise limit for the respective career based on the number of CareerCards initialized.
	 *
	 *
	 * @param index is the index that will be used for accessing the raise range constants.
	 * @return int the randomized pay raise limit;
	 */
	private int genLimit(int index)
	{
		Random rand = new Random();
		//to get values in the range, subtract upperbound with lowerbound then add 1 then add lowerbound again
		int raiseLimit = rand.nextInt (RAISE_RANGE[index][1] - RAISE_RANGE[index][0] + 1) + RAISE_RANGE[index][0];
		
		return raiseLimit;
	}
	
	/** returns the title of the CareerCard based on its title
	 *
	 * @return String the title of the CareerCard
	 */
	 @Override
	public String getTitle () {
		return title;
	}	

	/** returns the description of the CareerCard, containing degree requirement and number of pay raise limits
     *  
	 * @return String description of the card
	 */ 
	 @Override
	public String getDescription () {
		return description;
	}
	
	/** returns the amount of tax in the card
	 *
	 * @return int amount the amount of tax in the card
	 */
	public int getRaiseLimit() {
		return raiseLimit;
	}
	
	/** returns if the CareerCard requires a degree
	 *
	 * @return boolean if the career requires a degree
	 */
	 
	public boolean getDegree() {
		return degree;
	}
	
	/** This method is called when the player draws the card. The player will keep the card and inherit its attributes, such as career and pay raise limits.
	 * The player who drew the card is the target.
	 * @param target the player who drew the card
	 *
	 */
	 @Override
	 public boolean apply (Player target)//career 
	{
		if((target.getDegree() == degree) || degree == false){
			target.setCareerCard(this);
			target.setCareer(title);
			target.setTimesRaised(0);
			return true;
		}
		return false;
	}
	
	/** Overrides the method and returns its attributes as a String 
	 *
	 * @return String the string displaying the attributes of the cards
	 */
	@Override
	public String toString () {
		return ("[" + title + "] " + description + "(Requires degree: " + degree + "): " + "Pay Raise Limit = " + raiseLimit);
	}
	
	@Override
	/** Overrides the method and returns if two CareerCards are the same .
	 * Compares the cards' title if they are the same
	 *
	 * @return boolean returns true if same and false if not
	 */
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return ((CareerCard) o).getTitle() == getTitle();
	}
}