import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerBar extends JPanel{
	private FlowLayout layout;
	private PlayerButton pButtons[];
	
	public PlayerBar () {
		layout = new FlowLayout ();
		pButtons = new PlayerButton[3];
		layout.setHgap (256);
		layout.setVgap (16);
		
		setLayout (layout);
		setBackground (GUI.BACKGROUND_COLOR);
	}
	
	public void setMouseListener (MouseListener listener) {
		for (int i = 0; i < 3; i++)
			if (pButtons[i] != null)
				pButtons[i].addMouseListener (listener);
	}
	
	public void drawVariableObjects (Player[] players, int num) {
		removeAll ();
		
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				pButtons[i] = new PlayerButton (players[i]);
				add (pButtons[i]);
			}
			
		}
		
		if (num == 3)
			layout.setHgap (128);
	}
	
	public void toggleHighlight (PlayerButton selected, boolean status) {
		for (int i = 0; i < 3; i++)
			if (pButtons[i] == selected)
				pButtons[i].toggleHighlight (status);
	}
	
	public void updatePlayerDisplay () {
		for (int i = 0; i < 3; i++)
			if (pButtons[i] != null)
				pButtons[i].updateDisplay ();
	}
}