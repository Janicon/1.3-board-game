import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class GUI extends JFrame {
	public static final Dimension WINDOW_SIZE = new Dimension (1280, 720);
	public static Color BACKGROUND_COLOR = new Color (205, 220, 237);
	public static Color GAME_BACKGROUND = new Color (248, 249, 214); //To remove
	public static String GAME_FONT;
	
	public static Color CLEAR = new Color (0, 0, 0, 0);
	public static Color BLUE = new Color (86, 170, 255);
	public static Color GREEN = new Color (43, 236, 43);
	public static Color MAGENTA = new Color (229, 70, 229);
	public static Color ORANGE = new Color (255, 181, 107);

	private JPanel window;
	private StartScreen startScreen;
	private NameScreen nameScreen;
	private GameScreen gameScreen;
	private EndScreen endScreen;
	private CardLayout screens;
	
	public GUI () {
		super ("That's Life!");
		
		importFont ("./graphics/BebasNeue-Regular.ttf");
		
		window = new JPanel (new CardLayout (0, 0));
		startScreen = new StartScreen ();
		gameScreen = new GameScreen ();
		nameScreen = new NameScreen ();
		endScreen = new EndScreen ();
		screens = (CardLayout)(window.getLayout ());
		
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setResizable (false);
		
		window.setPreferredSize (WINDOW_SIZE);
		
		window.add (startScreen, "Start Screen");
		window.add (nameScreen, "Name Screen");
		window.add (gameScreen, "Game Screen");
		window.add (endScreen, "End Screen");
		
		add (window);
		
		pack ();
		setVisible (true);
	}
	
	/* Methods for GUI */	
	public void setActionListener (ActionListener listener) {
		startScreen.setActionListener (listener);
		nameScreen.setActionListener (listener);
		gameScreen.setActionListener (listener);
	}
	
	public void setMouseListener (MouseListener listener) {
		gameScreen.setMouseListener (listener);
	}
	
	public void displayScreen (String screen) {
		screens.show (window, screen);
	}
	
	public void importFont (String filename) {
		try {
			GraphicsEnvironment ge = 
				GraphicsEnvironment.getLocalGraphicsEnvironment();
			Font newFont = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
			ge.registerFont(newFont);
			GAME_FONT = newFont.getName ();
		} catch (IOException|FontFormatException e) {
			 GAME_FONT = "Dialog";
		}
	}
	
	
	
	// /* Methods for classes in GUI */
	
	/* Methods for NameScreen */
	public void enableFields (int num) {
		nameScreen.enableFields (num);
	}
	
	public void clearFields () {
		nameScreen.clearFields ();
	}
	
	public String[] getNames () {
		return nameScreen.getNames ();
	}
	
	
	
	
	/* Methods for GameScreen - PlayerBar */
	public void updatePlayerBar () {
		gameScreen.updatePlayerBar ();
	}
	
	/* Methods for GameScreen - GameArea */
	public void drawVariableObjects (Player[] players, int num) {
		gameScreen.drawVariableObjects (players, num);
	}
	
	public void drawMap (int pos) {
		gameScreen.drawMap (pos);
	}
	
	/* Methods for GameScreen - StatBox */
	public boolean toggleStats (PlayerButton selected) {
		return gameScreen.toggleStats (selected);
	}
	
	/* Methods for GameScreen - Bank */
	public boolean toggleBank () {
		return gameScreen.toggleBank ();
	}
	
	public boolean toggleMapDisplay () {
		return gameScreen.toggleMapDisplay ();
	}
	
	public void setBankMode (int mode) {
		gameScreen.setBankMode (mode);
	}
	
	public void bankChangeDisplayAmount (int change) {
		gameScreen.bankChangeDisplayAmount (change);
	}
	
	public void bankCancelTransaction () {
		gameScreen.bankCancelTransaction ();
	}
	
	public void bankConfirmTransaction () {
		gameScreen.bankConfirmTransaction ();
	}
	
	public void changeCurAccount (Player player) {
		gameScreen.changeCurAccount (player);
	}
	
	/* Methods for GameScreen - Map */
	public void loadMap (ThatsLife game) {
		gameScreen.loadMap (game);
	}
	
	public void setBtnDiceStatus (boolean status) {
		gameScreen.setBtnDiceStatus (status);
	}
	
	public void setBtnEndStatus (boolean status) {
		gameScreen.setBtnEndStatus (status);
	}
	
	/* Methods for GameScreen - Board */
	public void promptJunctionMode (int mode) {
		gameScreen.promptJunctionMode (mode);
	}
	
	public void displayPromptScreen (String name) {
		gameScreen.displayPromptScreen (name);
	}
	
	public void promptNotificationMode (int mode) {
		gameScreen.promptNotificationMode (mode);
	}
	
	public void drawPromptScreenCards (int num) {
		gameScreen.drawPromptScreenCards (num);
	}
	
	public void displayPromptCard (Color color) {
		gameScreen.displayPromptCard (color);
	}
	
	public void updatePromptButtons (String text1, String text2) {
		gameScreen.updatePromptButtons (text1, text2);
	}
	
	public void updatePromptCardText (Card card) {
		gameScreen.updatePromptCardText (card);
	}
	
	public void displayPromptHouseCards (int num) {
		gameScreen.displayPromptHouseCards (num);
	}
	
	public void promptRemoveHouseCard (int index) {
		gameScreen.promptRemoveHouseCard (index);
	}
	
	public void resetPrompt () {
		gameScreen.resetPrompt ();
	}
	
	public void updatePlayerTurn (String name) {
		gameScreen.updatePlayerTurn (name);
	}
	
	
	
	/* Methods for EndScreen */
	public void setEndScreenText (Player[] players) {
		endScreen.setText (players);
	}
	
	public void setEndScreenIcons (String[] names) {
		endScreen.setIcons (names);
	}
}