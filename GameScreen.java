import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameScreen extends JPanel {
	public static final Dimension BORDER_SIZE = new Dimension (16, 720);
	
	private PlayerBar players;
	private GameArea game;
	private ActionBar actions;
	
	public GameScreen () {
		players = new PlayerBar ();
		game = new GameArea ();
		actions = new ActionBar ();
		
		setPreferredSize (GUI.WINDOW_SIZE);
		setLayout (new BorderLayout ());
		
		add (players, BorderLayout.NORTH);
		add (createPadding (), BorderLayout.WEST);
		add (game, BorderLayout.CENTER);
		add (createPadding (), BorderLayout.EAST);
		add (actions, BorderLayout.SOUTH);
	}
	
	/* Methods for GameScreen */	
	private JPanel createPadding () {
		JPanel border = new JPanel ();	
		border.setBackground (GUI.BACKGROUND_COLOR);
		border.setPreferredSize (BORDER_SIZE);
		return border;
	}
	
	public void setActionListener (ActionListener listener) {
		actions.setActionListener (listener);
		game.setActionListener (listener);
	}
	
	public void setMouseListener (MouseListener listener) {
		players.setMouseListener (listener);
	}
	
	
	
	/* Methods for classes in GameScreen */
	
	/* Methods for PlayerBar */
	public void drawVariableObjects (Player[] players1, int num) {
		players.drawVariableObjects (players1, num);
		game.drawVariableObjects (players1, num);
	}
	
	public void updatePlayerBar () {
		players.updatePlayerDisplay ();
	}
	
	
	
	/* Methods for GameArea */
	public boolean toggleStats (PlayerButton selected) {
		boolean status = game.toggleStats (selected);
		players.toggleHighlight (selected, status);
		return status;
	}
	
	// public boolean toggleBank () {
		// boolean status = !game.toggleBank ();
		
		// actions.setBtnDiceStatus (status);
		// actions.setBtnMapStatus (status);
		// actions.setBtnEndStatus (status);
		// actions.setBtnDebugStatus(status);
		
		// return !status;
	// }
	
	// public boolean toggleMapDisplay () {
		// boolean status = !game.toggleMapDisplay ();
		
		// actions.setBtnDiceStatus (status);
		// actions.setBtnBankStatus (status);
		// actions.setBtnEndStatus (status);
		// actions.setBtnDebugStatus(status);
		
		// return !status;
	// }
	
	public boolean toggleBank () {
		boolean status = !game.toggleBank ();
		
		actions.setBtnDiceStatus (status);
		actions.setBtnMapStatus (status);
		actions.setBtnEndStatus (status);
		actions.setBtnDebugStatus (status);
		
		return !status;
	}
	
	public boolean toggleMapDisplay () {
		boolean status = !game.toggleMapDisplay ();
		
		actions.setBtnDiceStatus (status);
		actions.setBtnBankStatus (status);
		actions.setBtnEndStatus (status);
		actions.setBtnDebugStatus (status);
		
		return !status;
	}

	/* Methods for GameArea - Bank */
	public void setBankMode (int mode) {
		game.setBankMode (mode);
	}
	
	public void bankChangeDisplayAmount (int change) {
		game.bankChangeDisplayAmount (change);
	}
	
	public void bankCancelTransaction () {
		game.bankCancelTransaction ();
	}
	
	public void bankConfirmTransaction () {
		game.bankConfirmTransaction ();
		players.updatePlayerDisplay ();
	}
	
	public void changeCurAccount (Player player) {
		game.changeCurAccount (player);
	}
	
	/* Methods for GameArea - Board */
	public void promptJunctionMode (int mode) {
		game.promptJunctionMode (mode);
	}
	
	public void displayPromptScreen (String name) {
		game.displayPromptScreen (name);
	}
	
	public void drawPromptScreenCards (int num) {
		game.drawPromptScreenCards (num);
	}
	
	public void promptNotificationMode (int mode) {
		game.promptNotificationMode (mode);
	}
	
	public void displayPromptCard (Color color) {
		game.displayPromptCard (color);
	}
	
	public void updatePromptButtons (String text1, String text2) {
		game.updatePromptButtons (text1, text2);
	}
	
	public void updatePromptCardText (Card card) {
		game.updatePromptCardText (card);
	}
	
	public void displayPromptHouseCards (int num) {
		game.displayPromptHouseCards (num);
	}
	
	public void promptRemoveHouseCard (int index) {
		game.promptRemoveHouseCard (index);
	}
	
	public void resetPrompt () {
		game.resetPrompt ();
	}
	
	/* Methods for GameArea - Board - Map */
	public void loadMap (ThatsLife game1) {
		game.loadMap (game1);
	}
	
	public void drawMap (int pos) {
		game.drawMap (pos);
	}
	
	/* Methods for ActionBar */
	public void setBtnDiceStatus (boolean status) {
		actions.setBtnDiceStatus (status);
	}
	
	public void setBtnEndStatus (boolean status) {
		actions.setBtnEndStatus (status);
	}
	
	public void updatePlayerTurn (String name) {
		actions.updatePlayerTurn (name);
	}
}