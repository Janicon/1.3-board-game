import java.util.*;

/** Implements the SalaryCard object.
 *  SalaryCards dictate the salary that the player will collect and the tax that they will pay when they draw it.
 *
 */
public class SalaryCard extends Card {
	private	int	salary;
	private	int	tax;	
	
	
	/** The constructor for the SalaryCard.
	 * Randomizes the amount of salary by multiples of 10,000. Its tax is equal to 10% of its salary.
	 *
	 */
	public SalaryCard () 
	{	
		Random rand = new Random ();
		int multiple = rand.nextInt (10) + 1;
		
		salary = genSalary (multiple);
		tax = genTax(multiple);
				
		title = "Salary Card";
		description = "Collect " + salary + " Salary. Pay " + tax + " Tax" ;
	}
	
	/** Generates and returns the salary of the card by multiples of 10,000 until 100,000
	 * based on number randomly generated in the constructor
	 *
	 * @param multiple the multiple generated in the constructor
	 * @return int the amount of salary the card will have
	 */
	private int genSalary(int multiple)
	{
		return 10000 * multiple;
	}
	
	
	/** Generates and returns the tax of the salary by multiples of 1,000, with the same multiple as one used in the salary
	 * based on number randomly generated in the constructor
	 *
	 * @param multiple the multiple generated in the constructor
	 * @return int the amount of tax the card will have
	 */
	private int genTax(int multiple)
	{
		return 1000 * multiple;
	}
	
	
	/** returns the title of the SalaryCard
	 *
	 * @return String the title of the SalarynCard
	 */
	 @Override
	public String getTitle () {
		return title;
	}
	
	/** returns the description, containing the salary and tax, of the SalaryCard
     *  
	 * @return String description of the card
	 */ 
	 @Override
	public String getDescription () {
		return description;
	}
	
	/** returns the amount of salary in the card
	 *
	 * @return int amount the amount of salary in the card
	 */
	public int getSalary() {
		return salary;
	}
	
	/** returns the amount of tax in the card
	 *
	 * @return int amount the amount of tax in the card
	 */
	public int getTax() {
		return tax;
	}
	
	/** This method is called when the player draws the card. The player will have the salary and tax the card contains
	 * The player who drew the card is the target.
	 * @param target the player who drew the card
	 *
	 */
	 @Override
	public boolean apply (Player target) // salary 
	{
		target.setSalaryAndTax(salary, tax);
		target.setSalaryCard(this);
		return true;
	}
	
	
	@Override
	/** Overrides the method and displays its attributes as a String 
	 *
	 * @return String the string displaying the attributes of the cards
	 */
	public String toString () {
		return ("[" + title + "] "+ description);
	}
	
	@Override
	/** Overrides the method and returns if two SalaryCards are the same .
	 * Compares the cards' salary and tax if they are the same
	 *
	 * @return boolean returns true if same and false if not
	 */
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return (((SalaryCard) o).getSalary() == getSalary()) && (((SalaryCard) o).getTax() == getTax()) ;
	}
}