import java.util.*;
import java.awt.*;

/** Implements the game where most actions and methods occur. 
 * Contains the elements of the game such as the players, bank, and cards
 *  
 */ 
public class ThatsLife {
	
	private ArrayList<Player> players;
	private ArrayList<Player> retiredPlayers;
	
	private ActionDeck actionCards;
	private SalaryDeck salaryCards;
	private CareerDeck careerCards;
	private HouseDeck houseCards;
	private BlueDeck blueCards;
	
	private ArrayList<Tile> board;
	private ArrayList<Junction> junctions;
	
	private Card curCard;
	private Card chosenCard;
	private ArrayList<Card> hand;

	private Player curPlayer;
	private Player chosenPlayer;
	
	private Tile curTile;
	private Junction curJunction;
	private Junction chosenJunction ;
	private Player actionCard;
	

	private int randNum;
	
	private int TILE_COUNT = 59;
	private int JUNCTIONS[][] =  { {0,11} , {0,11}, {14,9}, {14,9}, {31,22}, {31,22}};
	private int[] ORANGE_TILES = {1,3,5,7,8,10,17,18,19,21,24,26,28,29,34,36,38,43,48,50,52,57};
	private int[] BLUE_TILES = {2,9,15,20,27,32,39,44,45,53};
	private int[] PAY_DAY = {11,12,13,16,22,23,30,35,34,40,41,42,47,49,51,54,55,56};
	private int[] PAY_RAISE = {4,6,25,33,37,46}; 
	private int[] MAGENTA_TILES = {0,14,31,58};
	private String[] MAGENTA_TITLES = {"Go to College", "Start a Family","Change Career", "Retirement"};
	
	private int place ;
	private int nextPlayer;
	
	private boolean firstTime;
	
	/** Creates the ThatsLife object. Generates the cards and creates the Players based on number entered by the user
	 * . It also shuffles the cards after generation
	 *
	 *
	 */
	public ThatsLife () {
		players = new ArrayList<> ();
		retiredPlayers = new ArrayList<> ();
		
		actionCards = new ActionDeck ();
		salaryCards = new SalaryDeck();
		careerCards = new CareerDeck();
		houseCards = new HouseDeck();
		blueCards = new BlueDeck();
		
		curCard = null;
		chosenCard = null;
		hand = new ArrayList<> ();
		
		curPlayer = getPlayer(0);
		chosenPlayer = null;
		curTile = null;
		chosenJunction = null;
		randNum = 10;
		place = 1;
		nextPlayer = 0;
		
		firstTime = true;
		

		initBoard();
	}
	
	/**	Initializes the contents and colors of the game board tiles.
	 *	
	 */
	private void initBoard()
	{
		board = new ArrayList<> (TILE_COUNT);
		
		for(int i= 0; i < TILE_COUNT; i++)
			board.add(new Tile());

		Tile temp;
		for (int i = 0; i < ORANGE_TILES.length; i++) {
			temp = board.get (ORANGE_TILES[i]);
			temp = new Tile(ORANGE_TILES[i], GUI.ORANGE, "Orange Space");
			board.set (ORANGE_TILES[i], temp);
		}
		for (int i = 0; i < BLUE_TILES.length; i++) {
			temp = board.get (BLUE_TILES[i]);
			temp = new Tile(BLUE_TILES[i], GUI.BLUE, "Blue Space");
			board.set (BLUE_TILES[i], temp);
		}
		for (int i = 0; i < PAY_DAY.length; i++) {
			temp = board.get (PAY_DAY[i]);
			temp = new Tile(PAY_DAY[i], GUI.GREEN, "Pay Day");
			board.set (PAY_DAY[i], temp); 
		}
		for (int i = 0; i < PAY_RAISE.length; i++) {
			temp = board.get (PAY_RAISE[i]);
			temp = new Tile(PAY_RAISE[i], GUI.GREEN, "Pay Raise");
			board.set (PAY_RAISE[i], temp); 
		}
		for (int i = 0; i < MAGENTA_TILES.length; i++) {
			temp = board.get (MAGENTA_TILES[i]);
			temp = new Tile(MAGENTA_TILES[i], GUI.MAGENTA, MAGENTA_TITLES[i]);
			board.set (MAGENTA_TILES[i], temp);
		}
		
		junctions = new ArrayList<> (JUNCTIONS.length);
		for (int i = 0; i < JUNCTIONS.length; i++)
			junctions.add(new Junction (JUNCTIONS[i][0], JUNCTIONS[i][1]));
	}
	
	/** Returns a Player from this class by index. Used when cycling through Player turns.
	 *	Returned player will be one traversing the board.
	 * 
	 * @param index the index that will be used for accessing the Player
	 *
	 * @return Player the player found at the given index
	 */
	public Player getPlayer (int index) {
		if (index >= players.size())
			return null;
		return players.get (index);
	}
	
	
	/**	Returns a Player by index from this class. Used to hold players who have finished playing.
	 *	Returned player will be one who has finished traversing the board.
	 *	
	 *	@param index the index that will be used for accessing the Player
	 *		
	 *	@return Player the player found at the given index
	 */
	public Player getRetiredPlayer (int index) {
		if (index >= retiredPlayers.size())
			return null;
		return retiredPlayers.get (index);
	}
	
	/** returns the Player with the same name as the parameter. Used for targeting in Action Cards.
	 * 
	 * @param name the name of the player being searched
	 *
	 * @return Player reference to the player's  clone
	 */
	public Player getPlayer (String name) {
		Player	tempPlayer;
		
		for (int i = 0; i < players.size (); i++) {
			tempPlayer = players.get (i);
			if (name.equals (tempPlayer.getName ()))
				return tempPlayer;
		}
		return null;
	}
	
	
	/**	Draws the top card from the passed deck.
	 *	
	 *	@param deck the deck from which to draw a card
	 */
	public void drawCard(Deck deck)
	{
		curCard = deck.draw();
	}
	
	/**	Generalized draw method. Used in drawing one card from Action Card and Blue Card decks.
	 *	Bases the card to draw on current tile.
	 */
	public void drawCard()
	{
		Color color = curTile.getColor();
		
		if(color == GUI.ORANGE)
		{
			curCard = actionCards.draw();
		}
		else if(color == GUI.BLUE)
		{
			curCard = blueCards.draw();
		}
	}
	
	/**	Retrieves the Action Card deck.
	 *	
	 *	@return ActionDeck the deck of Action Cards
	 */
	public ActionDeck getActionDeck()
	{
		return actionCards;
	}
	
	/**	Retrieves the Salary Card deck.
	 *		
	 *	@return SalaryDeck the deck of Salary Cards
	 */
	public SalaryDeck getSalaryDeck()
	{
		return salaryCards;
	}
	
	/**	Retrieves the Career Card deck.
	 *		
	 *	@return CareerDeck the deck of Career Cards
	 */
	public CareerDeck getCareerDeck()
	{
		return careerCards;
	}
	
	/**	Retrieves the House Card deck.
	 *		
	 *	@return HouseDeck the deck of House Cards
	 */
	public HouseDeck getHouseDeck()
	{
		return houseCards;
	}
	
	/**	Retrieves the Blue Card deck.
	 *		
	 *	@return BlueDeck the deck of Blue Cards
	 */
	public BlueDeck getBlueDeck()
	{
		return blueCards;
	}
	
	/**	Retrieves the current player taking a turn.
	 *		
	 *	@return Player the current player being targeted by methods
	 */
	public Player getCurPlayer()
	{
		return curPlayer;
	}
	
	/**	Retrieves the current tile the current player is on.
	 *		
	 *	@return Tile the tile with the same indices as CurPlayer
	 */
	public Tile getCurTile()
	{
		return curTile;
	}
	
	/**	Retrieves the current card being read
	 *		
	 *	@return Card the card changing the attibutes of Player
	 */
	public Card getCurCard () {
		return curCard;
	}
	
	/**	Retrieves the dice roll/number of steps remaining.
	 *		
	 *	@return int a randomly generated number saved in the class
	 */
	public int getRandNum()
	{
		return randNum;
	}
	
	/**	Adds a player by name to the list of participating players.
	 *	
	 *	@param name the name of the player to be added to the list
	 */
	public void addPlayer(String name)
	{
		Player temp;
		if (name == null || name.equals (""))
			temp = new Player();
		else
			temp = new Player(name);
		
		if(players.size() == 0)
			curPlayer = temp;
		players.add(temp);
	}
	
	/**	Increases a player's money and loan amount.
	 *	
	 *	@param curPlayer the player whose money is to be changed
	 *	@param amount the amount of money to loan
	 */
	public void loan (Player curPlayer, long amount)
	{
		curPlayer.changeMoney(amount);
		curPlayer.changeLoan(amount + 5000);
	}
	
	/**	Decreases a player's loan amount and money.
	 *	
	 *	@param curPlayer the player whose loan amount is to be changed
	 *	@param amount the amount of loan amount to repay
	 */
	public void payLoan (Player curPlayer, long amount)
	{
		curPlayer.changeMoney(amount * - 1);
		curPlayer.changeLoan(amount * - 1);
	}
	
	/**	Generated a random number and saves it to the class.
	 *	Used for setting a dice roll value.
	 *
	 */
	public void setRandNum()
	{
		Random rand = new Random (System.currentTimeMillis());
		randNum = rand.nextInt(6-1) + 1; //rolls from 1 to 6
	}
	
	/**	Sets a number to randNum. Used for debugging.
	 *	
	 *	@param num the value to set randNum as
	 */
	public void setRandNum(int num)
	{
		randNum = num;
	}
	
	/**	Sets the value of chosenPlayer. Is used for the targeting of Action Card.
	 *	
	 *	@param player the value to be assigned to chosenPlayer
	 */
	public void setChosenPlayer(Player player)
	{
		chosenPlayer = player;
	}
	
	/**	Sets the value of chosenCard. Is used for selecting between multiple draws.
	 *	
	 *	@param card the value to be assigned to chosenCard
	 */
	public void setChosenCard(Card card)
	{
		chosenCard = card;
	}
	
	/**	Sets the value of chosenJunction. Is used for directing players into junctions
	 *	
	 *	@param junction the value to be assigned to chosenJunction
	 */
	public void setChosenJunction(Junction junction)
	{
		chosenJunction = junction;
	}
	
	/**	Sets the value of chosenJunction. Is used for directing players into junctions
	 *	
	 *	@param index the index of the junction in the list to be assigned to chosenJunction
	 */
	public void setChosenJunction(int index)
	{
		chosenJunction = junctions.get(index);
	}
	
	/**	Assigns the value of chosenJunction to the current player.
	 *	
	 */
	public void setPlayerJunction()
	{
		int index;
		
		if(curPlayer.getInJunction() == -1){
			if(chosenJunction == null)
				index = -1;
			else
				index = junctions.indexOf(chosenJunction);
			curPlayer.setInJunction(index);
		}
	}
	
	/**	Advances the current player to the next tile in the board.
	 *	
	 */
	public void advancePlayer()
	{
		setPlayerJunction();
		int index = curPlayer.getInJunction();
		
		if(index == -1)
		{
			curPlayer.move();
			setCurTile();
			if(curTile.getColor() == GUI.MAGENTA)
			{
				randNum = 0;
			}
			else
			{
				randNum--;
			}
		}
		else if (index != -1)
		{
			Junction inJunction = junctions.get (curPlayer.getInJunction ());
			if(firstTime == true)
			{
				curPlayer.setPos(0);
				firstTime = false;
				setCurTile();
				if(curTile.getColor() == GUI.MAGENTA)
				{
					randNum = 0;
				}
				else
				{
					randNum--;
				}
			}
			else
			{
				if(curPlayer.getPos() == (inJunction.getLength() -1) )
				{
					curPlayer.setPos(inJunction.getEnd());
					curPlayer.setInJunction (-1);
					firstTime = true;
					chosenJunction = null;
				}
				else 
				{
					curPlayer.move();
				}
				setCurTile();
				if(curTile.getColor() == GUI.MAGENTA)
				{
					randNum = 0;
				}
				else
				{
					randNum--;
				}
			}	
		}
	}
	
	
	/** Sets the value of curTile based on the current player's position
	 *	
	 */
	public void setCurTile()
	{
		if(curPlayer.getInJunction() == -1)
		{	
			curTile = board.get(curPlayer.getPos());
		}
		else
		{
			curTile = (junctions.get(curPlayer.getInJunction())).getTile(curPlayer.getPos());
		}
	}
	
	
	// /**	Prints the value of curTile. Used for debugging.
	 // *	
	 // */
	// public void checkTile()
	// {
		// System.out.println(curTile);
	// }
	
	
	/**	Applies the effects of the current tile to the current player.
	 *	
	 */
	public void applyTile()
	{
		Color color = curTile.getColor();
		String title = curTile.getTitle();
		
		if (color == GUI.GREEN)
		{
			curTile.apply(curPlayer);
		}
		else if (color == GUI.MAGENTA)
		{
			switch(title)
			{
				case "Get Married":
				{
					// pause();
					// while(pause);
					curTile.apply(players, curPlayer, randNum);
				}
				case "Have Baby":
				case "Have Twins":
				{
					curTile.apply(players, curPlayer);
				};
				case "Retirement":
				{
					retire();
				}break;
				case "Graduation Space":
				{
					curTile.apply(curPlayer);
				}
				
			 } 
		}
	}
	
	
	/**	Applies the effects of the current card (drawn card) to the affected players.
	 *	
	 *	@return true if the card was successfully applied, otherwise false
	 */
	public boolean readCard () {
	
	boolean success = false;
		if (curCard instanceof ActionCard){
			ActionCard cardRead = new ActionCard();
			cardRead = (ActionCard) curCard;
			
			
			int type = cardRead.getType ();
			Player target;
			String tempDesc = cardRead.getDescription ();

			if (type == 3 || type == 4) {
				if (tempDesc.equals (cardRead.DESCRIPTIONS[2][1]) || tempDesc.equals (cardRead.DESCRIPTIONS[3][1])) 
				{
					long totalAmount = cardRead.getAmount() * (players.size() -1); //size - 1 since the player will not be paying or receiving money from themselves
					if(type == 4 || curPlayer.getMoney() >= totalAmount) //proceed to loop if player will receive money or has enough money to pay everyone
					{
						for (int i = 0; i < players.size(); i++)
							if (i != players.indexOf (curPlayer)) {
								target = getPlayer (i);
								cardRead.apply (curPlayer, target);
							}
						success = true;
					}
				}
				else
				{
					success = cardRead.apply(curPlayer, chosenPlayer);
				}
			}
			else 
			{
				success = cardRead.apply (curPlayer);
			}
		}

		else if (curCard instanceof SalaryCard)
		{
			SalaryCard cardRead =  new SalaryCard();
			cardRead = (SalaryCard)curCard;
			SalaryCard temp = null;
			
			if(curPlayer.getSalaryCard() != null){ //stores the current career card of player
				temp = curPlayer.getSalaryCard();
			}
			
			success = cardRead.apply(curPlayer);
			
			salaryCards.removeCard(cardRead); //removes card from deck
			if(temp != null) //if player has a card prior to taking the card, return the card to the deck
					salaryCards.returnCard(temp);
		}

		else if (curCard instanceof CareerCard)
		{
			CareerCard cardRead = (CareerCard) curCard;
			CareerCard temp = null;
			
			if(curPlayer.getCareerCard() != null){ //stores the current career card of player
				temp = curPlayer.getCareerCard();
			}
			
			success = cardRead.apply(curPlayer); //checks if player can get the card
			
			if(success == true)
			{
				careerCards.removeCard(cardRead); //removes card from deck
				if(temp != null) //if player has a card prior to taking the card, return the card to the deck
					careerCards.returnCard(temp);
			}
		}

		else if (curCard instanceof HouseCard)
		{
			// HouseCard cardRead =  new HouseCard();
			HouseCard temp = null;
			HouseCard cardRead = (HouseCard)curCard;
			
			if(curPlayer.getHouseCard() != null){ //stores the current house card of player
				temp = curPlayer.getHouseCard();
			}
			
			success = cardRead.apply(curPlayer);
			
			if(success == true)
			{
				houseCards.removeCard(cardRead); //removes card from deck
				if(temp != null) //if player has a card prior to taking the card, return the card to the deck
					houseCards.returnCard(temp);
			}
		}

		else if (curCard instanceof BlueCard)
		{
			BlueCard cardRead = (BlueCard) curCard;
			
			cardRead.apply(players, curPlayer);
			
			switch (cardRead.getType())
			{
				case 1:
				case 2:
				case 4:
				case 6:
				{
					success = cardRead.apply(curPlayer);
				} break;
				
				case 3:
				case 5:
				{
					success = cardRead.apply(curPlayer, randNum);
				} break;
				
				case 7:
				{
					success = cardRead.apply(curPlayer, players.size());
				}break;
						
			}
		}
		return success;
	}
	
	/**	Moves a player from the list of active players to the list of retired players.
	 *	Is called when a player reaches the end of the game.
	 *	
	 */
	public void retire()
	{
		switch(place)
		{
			case 1:
			{
				curPlayer.changeMoney(100000);
			}break;
			case 2:
			{
				curPlayer.changeMoney(50000);
			}break;
			case 3:
			{
				curPlayer.changeMoney(20000);
			}
		}
		curPlayer.changeMoney(10000 * curPlayer.getNumChildren());
		
		if(curPlayer.getHouseCard() != null)
		{
			curPlayer.changeMoney((curPlayer.getHouseCard()).getValue());
			houseCards.returnCard(curPlayer.getHouseCard());
			curPlayer.removeHouseCard();
		}
		curPlayer.changeMoney(curPlayer.getLoan() * -1);
		curPlayer.changeLoan(curPlayer.getLoan() * -1);
		

		Player temp = curPlayer;
		if(players.indexOf(curPlayer) != (players.size() - 1))
		{
			nextPlayer--;
		}
			retiredPlayers.add(temp);
			players.remove(temp);
			
		place++;
	}
	
	
	/**	Ends the turn of the current players and moves to the next player in the queue.
	 *	
	 */
	public void endTurn()
	{
		nextPlayer++;
		if( nextPlayer >= players.size())
		{
			nextPlayer = 0;
		}
		curPlayer = players.get(nextPlayer);
		chosenJunction = null;
	}
	
	/**	Retrieves a Tile by index from the class.
	 *	
	 *	@param index the index of the Tile to be retrieved
	 *
	 *	@return Tile the tile found at the given index
	 */
	public Tile getTile (int index) {
		return board.get (index);
	}
	
	
	/**	Retrieves the number of tiles in the main path.
	 *
	 *	@return int the number of tiles in the main path
	 */
	public int getTileCount () {
		return board.size ();
	}
	
	/**	 Retrieves the total number of tiles in the board.
	 *
	 *	@return int the total number of tiles in the board
	 */
	public int getTotalTiles () {
		int num;
		
		num = ORANGE_TILES.length + BLUE_TILES.length + PAY_DAY.length + PAY_RAISE.length + MAGENTA_TILES.length;
		
		for (int i = 0; i < JUNCTIONS.length; i += 2)
			num += JUNCTIONS[i][1];
		
		num -= JUNCTIONS.length;
		
		return num - 1;
	}
	
	/**	Retrieves a Junction by index from the class.
	 *	
	 *	@param index the index of the Junction to be retrieved
	 *
	 *	@return Junction the junction found at the given index
	 */
	public Junction getJunction (int index) {
		return junctions.get (index);
	}
	
	/**	Retrieves the number of Junctions found in the class
	 *	
	 *	@return int the number of Junctions found in the class
	 */
	public int getJunctionCount () {
		return junctions.size ();
	}
	
	/**	Retrieves the tile numbers at which the board splits off into junctions.
	 *	
	 *	@return int[][] the tile numbers at which the board splits off into junctions as well as the indices of the junctions
	 */
	public int[][] getBranchPoints () {
		int[][] branches = new int[3][3];
		
		Junction temp;
		int count = 0;
		int index = 0;
		
		for (int i = 0; i < branches.length; i++)
			for (int j = 0; j < branches[i].length; j++)
				branches[i][j] = -1;
		
		for (int i = 0; i < junctions.size (); i++) {
			temp = junctions.get (i);
			index = existInCol (branches, 0, temp.getStart ());
			if (index != -1) {
				branches[index][2] = i;
			}
			else {
				branches[count][0] = temp.getStart ();
				branches[count][1] = i;
				count++;
			}
		}
		
		return branches;
	}

	/**	Determines if a search key exists in a column of a 2D array
	 *	
	 *	@param arr the array containing the column to be searched
	 *	@param col the column to be searched
	 *	@param key the number to be searched for
	 *
	 *	@return int the index of an available index if the key is found, otherwise -1
	 */
	public int existInCol (int[][] arr, int col, int key) {
		for (int i = 0; i < arr.length; i++)
			if (arr[i][col] == key)
				return i;
		return -1;
	}

	/**	Retrieves the color of a tile at a given index.
	 *	
	 *	@param index the index of the tile to retrieve the color of
	 *
	 *	@return Color the color of the tile at the given index
	 */
	public Color getTileColor (int index) {
		return board.get (index).getColor ();
	}

	/**	Retrieves the color of a tile at a given index and junction
	 *	
	 *	@param junction the index of the junction to retrieve a tile
	 *	@param index the index of the tile to retrieve the color of
	 *
	 *	@return Color the color of the tile at the given index
	 */
	public Color getTileColor (int junction, int index) {
		return junctions.get (junction).getTile (index).getColor ();
	}
	
	/**	Sets the value of chosenPlayer.
	 *	
	 *	@param player the player to be assigned
	 */
	public void selectPlayer(Player player)
	{
		chosenPlayer = player;
	}

	/**	Draws a career card to the hand. 
	 *	
	 */
	public void addCareerCard()
	{
		hand.add(careerCards.draw());
	}

	/**	Draws a salary card to the hand
	 *	
	 */
	public void addSalaryCard()
	{
		hand.add(salaryCards.draw());
	}

	
	/**	Draws a house card to the hand
	 *	 
	 */
	public void addHouseCard()
	{
		hand.add(houseCards.draw());
	}
	
	
	/**	Retrieves the number of unclaimed house cards
	 *
	 *	@return int the number of unclaimed house cards
	 */
	public int getAvailableHouseCards () {
		return houseCards.getDeckSize ();
	}

	/**	Clears the hand of cards
	 *	
	 */
	public void clearHand()
	{
		hand.clear();
	}

	/**	Selects a card by index from the hand
	 *	
	 *	@param index the index of the card to select
	 */
	public void selectCard(int index)
	{
		if(!(index >= hand.size()))
			curCard = hand.get(index);
	}

	/**	Prints the value of the current tile. Used for debugging.
	 *	
	 *	@return String the string representation of curTile
	 */
	public String checkCurTile()
	{
		return curTile.getTitle();
	}
	
	/**	Retrieved the current position of the player translated to the board index
	 *	
	 *	@return int the index of a tile to be printed by the GUI
	 */
	public int getCurPos () {
		int pos = 0;
		int index;
		
		if (curTile != null) {
			if (chosenJunction != null) {
				index = junctions.indexOf (chosenJunction);
				for (int i = 0; i < index; i += 2)
					pos += junctions.get (i).getLength () - 2;
				pos += chosenJunction.getStart ();
				pos += curTile.getPos () - 1;
			}
			if (chosenJunction == null) {
				for (int i = 0; i < curTile.getPos (); i++)
					for (int j = 0; j < 6; j += 2)
						if (curTile.getPos () >= junctions.get (j).getEnd ())
							pos += junctions.get (j).getLength () - 2;
				pos += curTile.getPos ();
			}
		}
		return pos;
	}
	
	/**	Retrives a card by index from the hand
	 *	
	 *	@param index the index of the card to be retrieved
	 *
	 *	@return Card the card located at index
	 */
	public Card getHandCard (int index) {
		return hand.get (index);
	}
	
	public Player[] endGame()
	{
		Player[] sortedPlayers = new Player[retiredPlayers.size()];
		Player temp;
		
		for(int i = 0; i < sortedPlayers.length; i++)
			sortedPlayers[i] = retiredPlayers.get(i);
		
		for(int i=0; i < sortedPlayers.length; i++){  
		 for(int j=1; j < sortedPlayers.length - 1; j++){ 
			if((sortedPlayers[j-1]).getMoney() < (sortedPlayers[j]).getMoney()){
				 //swap elements  
				 // temp = arr[j-1];  
				 // arr[j-1] = arr[j];  
				 // arr[j] = temp;  
				 temp = sortedPlayers[j-1];
				 sortedPlayers[j-1] = sortedPlayers[j];
				 sortedPlayers[j] = temp;
			 }  
		 }  
		}
		return sortedPlayers;
	}
}