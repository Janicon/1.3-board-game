import java.awt.*;
import java.util.*;

/** Implements a Junction object containing tiles that players have to go through. It facilitates the traversal of the player on the board
 *  
 *
 */
public class Junction
{
	private int start;
	private int end;
	private int length;
	private ArrayList<Tile> tiles;
	
	public static int count = 1;
	private static String TITLES[][]={
			{"Orange Space", "Orange Space", "Orange Space", 
			 "Graduation Space", "Orange Space", "Orange Space",
			 "Orange Space", "College Career Choice","Orange Space",
			"Orange Space","Pay Day"},
			{"Orange Space", "Orange Space", "Pay Raise", 
			 "Orange Space", "Orange Space", "Pay Day", 
	         "Orange Space", "Get Married","Orange Space",
			 "Pay Day", "Orange Space"},
			 
			{"Orange Space","Job Search","Orange Space",
			 "Blue Space","Orange Space","Pay Raise",
			 "Orange Space", "Pay Day","Orange Space"},
			{"Orange Space","Orange Space","Pay Raise",
			 "Orange Space","Pay Day","Pay Day",
			 "Pay Day","Orange Space","Pay Raise"},
			 
			{"Orange Space","Blue Space","Orange Space",
			 "Pay Raise","Orange Space","Pay Day",
			 "Orange Space","Pay Raise`","Orange Space",
			 "Pay Raise","Pay Day","Orange Space",
			 "Pay Day","Orange Space","Pay Day",
			 "Blue Space","Orange Space","Orange Space",
			 "Pay Day","Pay Day","Orange Space",
			"Pay Raise"}, 
			{"Orange Space","Orange Space","Pay Raise",
			 "Orange Space","Blue Space","Pay Day",
			 "Orange Space","Orange Space","Blue Space",
			 "Have Twins","Orange Space","Orange Space",
			 "Pay Day","Orange Space","Orange Space",
			 "Blue Space","Pay Raise","Orange Space",
			 "Buy a House","Orange Space","Pay Day",
			 "Orange Space"},};
			 
			  
	private static Color  COLORS[][]= { 
			{GUI.ORANGE,GUI.ORANGE,GUI.ORANGE,
			 GUI.MAGENTA,GUI.ORANGE,GUI.ORANGE,
			 GUI.ORANGE,GUI.MAGENTA,GUI.ORANGE,
			 GUI.ORANGE,GUI.GREEN}, 
			{GUI.ORANGE,GUI.ORANGE,GUI.GREEN,
			 GUI.ORANGE,GUI.ORANGE,GUI.GREEN,
			 GUI.ORANGE,GUI.ORANGE,GUI.ORANGE,
			 GUI.GREEN,GUI.ORANGE}, 
			 
			{GUI.ORANGE,GUI.MAGENTA,GUI.ORANGE,
			 GUI.BLUE,GUI.ORANGE,GUI.GREEN,
			 GUI.ORANGE,GUI.GREEN,GUI.ORANGE}, 
			{GUI.ORANGE,GUI.ORANGE,GUI.GREEN,
			 GUI.ORANGE,GUI.GREEN,GUI.GREEN,
			 GUI.GREEN,GUI.ORANGE,GUI.GREEN}, 
			 
			{GUI.ORANGE,GUI.BLUE,GUI.ORANGE,
			 GUI.GREEN,GUI.ORANGE,GUI.GREEN,
			 GUI.ORANGE,GUI.GREEN,GUI.ORANGE,
			 GUI.GREEN,GUI.GREEN,GUI.ORANGE,
			 GUI.GREEN,GUI.ORANGE,GUI.GREEN,
			 GUI.BLUE,GUI.ORANGE,GUI.ORANGE,
			 GUI.GREEN,GUI.GREEN,
			 GUI.ORANGE,GUI.GREEN}, 
			 
			{GUI.ORANGE,GUI.ORANGE,GUI.GREEN,
			 GUI.ORANGE,GUI.BLUE,GUI.GREEN,
			 GUI.ORANGE,GUI.ORANGE,GUI.BLUE,
			 GUI.MAGENTA,GUI.ORANGE,GUI.ORANGE,
			 GUI.GREEN,GUI.ORANGE,GUI.ORANGE,
			 GUI.BLUE,GUI.GREEN,GUI.ORANGE,
			 GUI.MAGENTA,GUI.ORANGE,
			 GUI.GREEN,GUI.ORANGE}}; 

	/** Creates a junction object with its starting tile and ending tile
	 *
	 * @param start the position of the tile where the start of the junction is connected 
	 * @param length the number of tiles in the junction
	 */
	public Junction (int start, int length)
	{
		this.start = start;
		this.end = start + 1;
		this.length = length;
		tiles = new ArrayList<> (length);
		
		for (int i = 0; i < length; i++)
			tiles.add(new Tile(i,COLORS[count-1][i],TITLES[count-1][i]));
		
		count++;
	}
	
	/** Returns the starting pos of the tile at which the junction is connected on the main path
	 *
	 * @return start the position of the first tile that connects the junction on the main path 
	 */
	public int getStart()
	{
		return start;
	}
	
	/** Returns the ending pos of the tile at which the junction will be reconnected to the main path 
	 *
	 * @return end the position of the last tile that connects the junction on the main path 
	 */
	public int getEnd()
	{
		return end;
	}
	
	/** Returns the number of tiles in the junction
	 *
	 * @return length the number of tiles in the junction
	 */
	public int getLength()
	{
		return length;
	}
	
	/** Returns the tile in the junction using its index
	 *
	 * @param index the index of the Tile in the junction.
	 * @return Tile the tile in the junction accessed by the index
	 */
	public Tile getTile(int index)
	{
		if(index >= tiles.size())
			return null;
		return tiles.get(index);
	}
	
	@Override 
	/** Overrides the equals method and checks if two junctions have the same starting tile
	 *
	 * @return boolean returns true if the junctions have the same starting tile, and false otherwise
	 */
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		return (((Junction) o).getStart() == getStart());
	}
}