import java.awt.*;
import java.util.*;

/** Implements a Tile object, which the players land on when traversing through the board and receives the effects or instructions on the tile
 *  
 *
 */
public class Tile
{
	private int pos;
	private Color color;
	private String title;
	
	/** default constructor for tile, initializing the attributes to their zero values
	 *
	 */
	public Tile()
	{
		pos = 0;
		color = null;
		title = "";
	}
	/** Constructor, initializing the attributes to their zero values
	 * @param pos the position of the tile on the board or on a junction
	 * @param color the Color derived from GUI.java of the tile 
	 * @param title the title of the tile
	 */
	public Tile (int pos, Color color, String title)
	{
		this.pos = pos;
		this.color = color;
		this.title = title;
	}
	
	/** Returns the position of the tile
	 * 
	 * @return pos the position of the tile on the board or on a junction
	 */
	public int getPos()
	{
		return pos;
	}
	
	/** Returns the Color of the tile
	 * 
	 * @return color the Color of the tile
	 */
	public Color getColor()
	{
		return color;
	}
	
	/** Returns the tileof the tile
	 * 
	 * @return title the title of the tile
	 */
	public String getTitle()
	{
		return title;
	}
	
	/** Applies the tile on the current player landing on it. 
	 * This is called when it is a "Pay Raise", "Pay Day", or "Graduation Space" tile.
	 * 
	 * @param player the current player in turn that landed on the tile
	 */
	public void apply(Player player)
	{
		switch(title){
			case "Pay Raise":
			{
				int timesRaised = player.getTimesRaised() + 1;
				int salaryRaise = genAmount();
			
				if(timesRaised <= player.getRaiseLimit())
				{
					player.increaseSalaryAndTax(salaryRaise, 2000);
				}
			}
			case "Pay Day":
			{
				player.changeMoney(player.getSalary());
			}break;
			case "Graduation Space":
			{
				player.graduate();
			}
		}
		
	}
	
	/** Applies the 2 Have Babies space to the players in the game. The players pay the current player for every child they will be having.
	 * This effect is only applied when the player is married.
	 *
	 * @param players the set of players apart from the current player who will pay the current player.
	 * @param player the current player who landed on the tile
	 */
	public void apply (ArrayList<Player> players, Player player)
	{
		int numChildren = 0;
		Player temp;
		if(player.getIsMarried() == true){
			switch (title)
			{
				case "Have Baby":
				{
					numChildren = 1;
				} break;
				case "Have Twins":
				{
					numChildren = 2 ;
				} break;
			}
			player.addNumChildren(numChildren);
			for (int i = 0; i < players.size(); i++)
			{
				temp = players.get(i);
				if(!(temp.equals(player)))
				{
					temp.changeMoney((5000 * numChildren) * -1);
					player.changeMoney(5000 * numChildren);
				}
			}
		}
	}
	
	/** This is the apply method for the Get Married space. This is called when the player presses for a number.
	 * The players pay 5000 if the number is odd, and 10,000 if it is even. The benefits for this space only apply when
	 * the player is single when they landed on this space.
	 *
	 * @param players the set of players apart from the current player who will pay the current player.
	 * @param player the current player who landed on the tile
	 * @param randNum the randomly generated number the current player pressed for
	 */
	public void apply(ArrayList<Player> players, Player player, int randNum)
	{
		long amount = 5000;
		Player temp;
		if(player.getIsMarried() == false)
		{
			player.getMarried();
			
			if(randNum % 2 == 0)
				amount += 5000;
			
			for(int i = 0; i < players.size(); i++)
			{
				temp = players.get(i);
				temp.changeMoney(amount * -1);
				player.changeMoney(amount);
			}
		}
	}
	
	/** This is only used in the Pay Raise space and generates the salary increase the player will receive.
	 *
	 * @return amount the amount of salary increase the player will receive when landing on the Pay Raise space
	 */
	private int genAmount()
	{
		Random rand = new Random();
		int multiple = rand.nextInt(10-1) + 1;
		return 5000 * multiple;
	}
	
	@Override
	/** Overrides the method and returns its attributes as a String 
	 *
	 * @return String the string displaying the attributes of the tile
	 */
	public String toString()
	{
		return (title + ": " + pos);
	}
	
}
