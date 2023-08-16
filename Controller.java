import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.*;

public class Controller implements ActionListener, MouseListener {
	private GUI gui;
	private ThatsLife game;
	private int playerCount;
	
	JPanel hoveredPanel;
	private String[] nameList;
	private static int pos;
	private boolean turnOver;
	private boolean showStatsEnabled;
	
	boolean status;
	boolean status1;
	boolean status2;
	
	Controller (GUI gui, ThatsLife game) {
		this.gui = gui;
		this.game = game;
		playerCount = 2;
		
		hoveredPanel = null;
		pos = 1;
		turnOver = false;
		showStatsEnabled = true;
		
		gui.loadMap (game);
		gui.setActionListener (this);
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		switch (e.getActionCommand ()) {
			/* StartScreen buttons */
			case "Two player mode": {
				playerCount = 2;
				gui.enableFields (playerCount);
				gui.displayScreen ("Name Screen");
			} break;
			
			case "Three player mode": {
				playerCount = 3;
				gui.enableFields (playerCount);
				gui.displayScreen ("Name Screen");
			} break;
			
			/* NameScreen buttons */
			case "Back to start screen": {
				gui.displayScreen ("Start Screen");
				gui.clearFields ();
			} break;
			
			case "Confirm names": {
				startGame ();
			} break;
			
			/* GameScreen - GameArea - Prompt buttons */
			case "Draw card": {
				if (game.getCurCard () instanceof ActionCard)
					status = ((ActionCard)game.getCurCard ()).getHasTarget ();
				else
					status = false;
				if (status) {
					gui.promptNotificationMode (3);
					gui.displayPromptScreen ("Notification");
					showStatsEnabled = false;
				}
				else {
					game.readCard ();
					gui.updatePromptCardText (game.getCurCard ());
					gui.updatePlayerBar ();
					finishTurn ();
				}
			} break;
			
			case "College path": {
				game.setChosenJunction (0);
				startTurn ();
			} break;
			
			case "College alternate path": {
				game.setChosenJunction (1);
				startTurn ();
			} break;
			
			case "Family path": {
				game.setChosenJunction (2);
				startTurn ();
			} break;
			
			case "Family alternate path": {
				game.setChosenJunction (3);
				startTurn ();
			} break;
			
			case "Career path": {
				game.setChosenJunction (4);
				startTurn ();
			} break;
			
			case "Career alternate path": {
				game.setChosenJunction (5);
				startTurn ();
			} break;
			
			case "Career card 1": {
				if (status1 || status2) {
					// gui.updatePromptButtons (game.getHandCard (0).getTitle (), game.getHandCard (1).getTitle ());
					game.selectCard (0);
					if (game.readCard ()) {
						gui.updatePlayerBar ();
						game.clearHand ();
						status1 = true;
						status2 = true;
						for (int i = 0; i < 2; i++)
							game.addSalaryCard ();
						gui.displayPromptScreen ("Select salary card");
					}
					else
						status1 = false;
				}
				else {
					game.clearHand ();
					status1 = true;
					status2 = true;
					for (int i = 0; i < 2; i++)
						game.addCareerCard ();
					gui.updatePromptButtons (game.getHandCard (0).getTitle (), game.getHandCard (1).getTitle ());
				}
			} break;
			
			case "Career card 2": {
				if (status1 || status2) {
					gui.updatePromptButtons (game.getHandCard (0).getTitle (), game.getHandCard (1).getTitle ());
					game.selectCard (1);
					if (game.readCard ()) {
						gui.updatePlayerBar ();
						game.clearHand ();
						status1 = true;
						status2 = true;
						for (int i = 0; i < 2; i++)
							game.addSalaryCard ();
						gui.displayPromptScreen ("Select salary card");
					}
					else
						status2 = false;
				}
				else {
					game.clearHand ();
					status1 = true;
					status2 = true;
					for (int i = 0; i < 2; i++)
						game.addCareerCard ();
				}
			} break;
			
			case "Salary card 1": {
				game.selectCard (0);
				if (game.readCard ()) {
					game.clearHand ();
					finishTurn ();
					gui.resetPrompt ();
				}
			} break;
			
			case "Salary card 2": {
				gui.updatePromptButtons (game.getHandCard (0).getDescription (), game.getHandCard (1).getDescription ());
				game.selectCard (1);
				if (game.readCard ()) {
					game.clearHand ();
					finishTurn ();
					gui.resetPrompt ();
				}
			} break;
			
			case "House card 1": {
				game.selectCard (0);
				if (game.readCard ()) {
					gui.promptRemoveHouseCard (0);
					gui.updatePlayerBar ();
					game.clearHand ();
					finishTurn ();
				}
			} break;
			
			case "House card 2": {
				game.selectCard (1);
				if (game.readCard ()) {
					gui.promptRemoveHouseCard (1);
					gui.updatePlayerBar ();
					game.clearHand ();
					finishTurn ();
				}
			} break;
			
			case "House card 3": {
				game.selectCard (2);
				if (game.readCard ()) {
					gui.promptRemoveHouseCard (2);
					gui.updatePlayerBar ();
					game.clearHand ();
					finishTurn ();
				}
			} break;
			
			case "House card 4": {
				game.selectCard (3);
				if (game.readCard ()) {
					gui.promptRemoveHouseCard (3);
					gui.updatePlayerBar ();
					game.clearHand ();
					finishTurn ();
				}
			} break;
			
			case "House card 5": {
				game.selectCard (4);
				if (game.readCard ()) {
					gui.promptRemoveHouseCard (4);
					gui.updatePlayerBar ();
					game.clearHand ();
					finishTurn ();
				}
			} break;
			
			case "Keep current career": {
				game.clearHand ();
				finishTurn ();
			} break;
			
			case "Change career": {
				game.selectCard (0);
				game.readCard ();
				game.selectCard (1);
				game.readCard ();
				game.clearHand ();
				gui.updatePlayerBar ();
				finishTurn ();
			} break;
			
			/* GameScreen - BankDisplay buttons */
			case "Loan from bank": {
				gui.setBankMode (1);
			} break;
			
			case "Pay loan": {
				gui.setBankMode (-1);
			} break;
			
			case "Cancel transaction": {
				gui.bankCancelTransaction ();
			} break;
			
			case "Increase amount": {
				gui.bankChangeDisplayAmount (1);
			} break;
			
			case "Decrease amount": {
				gui.bankChangeDisplayAmount (-1);
			} break;
			
			case "Confirm transaction": {
				gui.bankConfirmTransaction ();
			} break;
			
			case "Player 1 transaction": {
				gui.changeCurAccount (game.getPlayer (0));
			} break;
			
			case "Player 2 transaction": {
				gui.changeCurAccount (game.getPlayer (1));
			} break;
			
			case "Player 3 transaction": {
				gui.changeCurAccount (game.getPlayer (2));
			} break;
			
			/* GameScreen - ActionBar buttons */
			case "Roll dice": {
				startTurn ();
			} break;
			
			case "Bank": {
				if (!gui.toggleBank ()) {
					gui.setBtnDiceStatus (!turnOver);
					gui.setBtnEndStatus (turnOver);
				}
			} break;
			
			case "Map": {
				if (!gui.toggleMapDisplay ()) {
					gui.setBtnDiceStatus (!turnOver);
					gui.setBtnEndStatus (turnOver);
				}
			} break;
			
			case "End turn": {
				game.endTurn ();
				turnOver = false;
				gui.setBtnDiceStatus (!turnOver);
				gui.setBtnEndStatus (turnOver);
				gui.resetPrompt ();
				gui.updatePlayerTurn (game.getCurPlayer ().getName ());
			} break;
			
			/* Debug buttons */			
			case "End game": {
				endGame ();
			} break;
			
			case "Debug" :{
				gui.displayPromptScreen("Blank");
			}				
			
			case "Debug 2":{
				gui.displayPromptScreen("Select house card");
			}				
		}
	}
	
	@Override
	public void mouseClicked (MouseEvent e) {
		// hoveredPanel = (JPanel)e.getComponent ();
		// if (hoveredPanel instanceof PlayerButton && showStatsEnabled) 
			// gui.toggleStats ((PlayerButton)hoveredPanel);
	}
	
	@Override
	public void mouseEntered (MouseEvent e) {
		hoveredPanel = (JPanel)e.getComponent ();
		if (hoveredPanel instanceof PlayerButton)
			hoveredPanel.setBorder (new LineBorder (Color.BLACK, 1, true));
	}
	
	@Override
	public void mouseExited (MouseEvent e) {
		hoveredPanel = (JPanel)e.getComponent ();
		if (hoveredPanel instanceof PlayerButton)
			hoveredPanel.setBorder (new LineBorder (GUI.BACKGROUND_COLOR, 1, true));
	}
	
	
	@Override
	public void mousePressed (MouseEvent e) {
		hoveredPanel = (JPanel)e.getComponent ();
		if (hoveredPanel instanceof PlayerButton)
			if (showStatsEnabled)
				gui.toggleStats ((PlayerButton)hoveredPanel);
			else {
				hoveredPanel.setBackground (new Color (247, 249, 143));
				game.setChosenPlayer (((PlayerButton)hoveredPanel).getPlayer ());
				if (game.readCard ()) {
					showStatsEnabled = true;
					gui.resetPrompt ();
				}
			}
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
		hoveredPanel = (JPanel)e.getComponent ();
		if (hoveredPanel instanceof PlayerButton)
			if (showStatsEnabled)
				gui.toggleStats ((PlayerButton)hoveredPanel);
			else
				hoveredPanel.setBackground (new Color (238, 238, 238));
	}
	
	private void startGame () {
		nameList = gui.getNames ();
		Player[] players = new Player[nameList.length];
		for (int i = 0; i < nameList.length; i++) {
			game.addPlayer (nameList[i]);
			players[i] = game.getPlayer (i);
		}
		gui.drawVariableObjects (players, playerCount);
		gui.setMouseListener (this);
		gui.updatePlayerTurn (game.getCurPlayer ().getName ());
		gui.displayScreen ("Game Screen");
	}
	
	private void startTurn () {
		game.setRandNum ();
		
		while (game.getRandNum () > 0) {
			gui.drawMap (game.getCurPos ());
			game.advancePlayer ();
			gui.updatePlayerBar ();
		}
		// Timer delay = new Timer ();
		// TimerTask move = new TimerTask () {
			// @Override public void run () {
				// System.out.println ("tick");
				// gui.drawMap (game.getCurPos ());
				// // game.advancePlayer ();
				 // if (game.getRandNum () == 0)
					// delay.cancel ();
			// }
		// };
		
		// delay.schedule (move, 0, 500);
		switch (game.getCurTile ().getTitle ()) {
			case "Blue Space":
			case "Orange Space": {
				game.drawCard ();
				gui.displayPromptCard (game.getCurTile ().getColor ());
				gui.displayPromptScreen ("Card draw");
				turnOver = false;
				gui.setBtnDiceStatus (false);
				gui.setBtnEndStatus (false);
			} break;
			
			case "Pay Day": {
				gui.promptNotificationMode (1);
				game.applyTile ();
				turnOver = true;
				gui.setBtnDiceStatus (!turnOver);
				gui.setBtnEndStatus (turnOver);
				gui.displayPromptScreen ("Notification");
			} break;
			case "Pay Raise": {
				gui.promptNotificationMode (2);
				game.applyTile ();
				turnOver = true;
				gui.setBtnDiceStatus (!turnOver);
				gui.setBtnEndStatus (turnOver);
				gui.displayPromptScreen ("Notification");
			} break;
			
			case "Go to College": {
				gui.displayPromptScreen ("Junction selection");
				gui.promptJunctionMode (1);
			} break;
			
			case "Start a Family": {
				gui.displayPromptScreen ("Junction selection");
				gui.promptJunctionMode (2);
			} break;
			
			case "Change Career": {
				gui.displayPromptScreen ("Junction selection");
				gui.promptJunctionMode (3);
			} break;
			
			case "Graduation Space":
			case "Get Married": 
			case "Have Baby": 
			case "Have Twins": 
			case "Retirement": {
				game.applyTile ();
				turnOver = false;
				gui.setBtnDiceStatus (!turnOver);
				gui.setBtnEndStatus (turnOver);
			} break;
			
			case "College Career Choice": {
				for (int i = 0; i < 2; i++)
					game.addCareerCard ();
				status1 = true;
				status2 = true;
				gui.displayPromptScreen ("Select career card");
			} break;
			
			case "Buy a House": {
				for (int i = 0; i < game.getAvailableHouseCards (); i++)
					game.addHouseCard ();
				gui.displayPromptHouseCards (game.getAvailableHouseCards ());
				gui.displayPromptScreen ("Select house card");
			} break;
			
			case "Job Search": {
				game.addCareerCard ();
				game.addSalaryCard ();
				gui.displayPromptScreen ("");
			} break;
		}
	}
	
	private void finishTurn () {
		turnOver = true;	
		gui.setBtnDiceStatus (!turnOver);
		gui.setBtnEndStatus (turnOver);
	}
	
	private void endGame () {
		gui.setEndScreenText (game.endGame ());
		gui.setEndScreenIcons (nameList);
		gui.displayScreen ("End Screen");
	}
}