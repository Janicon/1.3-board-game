import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameArea extends JPanel {
	
	private StatBox stats;
	private BoardDisplay board;
	private BankDisplay bank;
	private MapDisplay map;
	
	private boolean statsVisible;
	private boolean bankVisible;
	private boolean mapVisible;
	
	public GameArea () {
		stats = new StatBox ();
		board = new BoardDisplay ();
		bank = new BankDisplay ();
		map = new MapDisplay ();
		statsVisible = false;
		bankVisible = false;
		mapVisible = false;
		
		JPanel statWindow = new JPanel ();
		statWindow.setPreferredSize (new Dimension (1248, 552));
		statWindow.setAlignmentX (0.0F);
		statWindow.setAlignmentY (0.0F);
		statWindow.setOpaque (false);
		statWindow.add (stats);
		
        setPreferredSize(new java.awt.Dimension(1248, 552));
		setLayout (new OverlayLayout (this));
		
		stats.setVisible (false);
		bank.setVisible (false);
		map.setVisible (false);
		
		add (statWindow);
		add (bank);
		add (map);
		add (board);
	}
	
	/* Enables overlapping of panels */
	public boolean isOptimizedDrawingEnabled() {
		return false;
	}
	
	public boolean toggleStats (PlayerButton selected) {
		if (statsVisible = !statsVisible);
			stats.updateStatSummary (selected.getPlayer ());
		
		stats.setVisible (statsVisible);
		
		return statsVisible;
	}
	
	public boolean toggleBank () {
		bankVisible = !bankVisible;
		
		bank.setVisible (bankVisible);
		
		return bankVisible;
	}
	
	public boolean toggleMapDisplay () {
		mapVisible = !mapVisible;
		
		map.setVisible (mapVisible);
		
		return mapVisible;
	}
	
	
	
	/* Methods for GameArea */
	public void setActionListener (ActionListener listener) {
		board.setActionListener (listener);
		bank.setActionListener (listener);
	}
	
	
	
	/* Methods for BoardDisplay */
	public void promptJunctionMode (int mode) {
		board.setJunctionMode (mode);
	}
	
	public void displayPromptScreen (String name) {
		board.displayScreen (name);
	}
	
	public void drawPromptScreenCards (int num) {
		board.drawCards (3);
	}
	
	public void promptNotificationMode (int mode) {
		board.notificationMode (mode);
	}
	
	public void displayPromptCard (Color color) {
		board.displayPromptCard (color);
	}
	
	public void updatePromptButtons (String text1, String text2) {
		board.updateButtons (text1, text2);
	}
	
	public void updatePromptCardText (Card card) {
		board.updatePromptCardText (card);
	}
	
	public void displayPromptHouseCards (int num) {
		board.displayHouseCards (num);
	}
	
	public void promptRemoveHouseCard (int index) {
		board.removeHouseCard (index);
	}
	
	public void resetPrompt () {
		board.resetPrompt ();
	}
	
	
	public void loadMap (ThatsLife game) {
		board.loadMap (game);
	}
	
	public void drawMap (int pos) {
		board.drawMap (pos);
	}
	
	
	
	/* Methods for StatBox */
	public void updateStatSummary (Player player) {
		stats.updateStatSummary (player);
	}
	
	
	
	/* Methods for BankDisplay */
	public void setBankMode (int mode) {
		bank.setBankMode (mode);
	}
	
	public void bankChangeDisplayAmount (int change) {
		bank.changeDisplayAmount (change);
	}
	
	public void bankCancelTransaction () {
		bank.resetDisplay ();
	}
	
	public void bankConfirmTransaction () {
		bank.confirmTransaction ();
		bank.resetDisplay ();
	}
	
	public void changeCurAccount (Player player) {
		bank.changeCurAccount (player);
	}
	
	
	
	/* Methods for MapDisplay */
	public void drawVariableObjects (Player[] players, int num) {
		if (num == 3)
			map.drawVariableObjects ();
		map.setNames (players);
		bank.drawVariableObjects (num);
	}
}