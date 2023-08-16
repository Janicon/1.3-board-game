import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ActionBar extends JPanel {
	private static final Dimension SIZE = new Dimension ((int)GUI.WINDOW_SIZE.getWidth (), 64);
	private static final Border BORDER = new EmptyBorder (8, (int)GameScreen.BORDER_SIZE.getWidth (), 8, (int)GameScreen.BORDER_SIZE.getWidth ());

	private JButton btnPlayer;
	private JButton btnDice;
	private JButton btnBank;
	private JButton btnMap;
	private JButton btnEnd;
	private JButton debugEnd;
	private JButton debug1;
	private JButton debug2;
	
	public ActionBar () {
		btnPlayer = new JButton ("");
		btnDice = new JButton ("Roll dice");
		btnBank = new JButton ("Bank");
		btnMap = new JButton ("Map");
		btnEnd = new JButton ("End turn");
		debugEnd = new JButton ("End game");
		debug1 = new JButton ("Debug");
		debug2 = new JButton ("Debug 2");
		
		setPreferredSize (SIZE);
		setLayout (new FlowLayout (FlowLayout.LEFT, 0, 0));
		setBackground (GUI.BACKGROUND_COLOR);
		setBorder (BORDER);
		
		btnEnd.setEnabled (false);
		/*
		btnDice.setAlignmentX (Component.CENTER_ALIGNMENT);
		debugStep.setAlignmentX (Component.CENTER_ALIGNMENT);
		debugReset.setAlignmentX (Component.CENTER_ALIGNMENT);
		*/
		
		btnPlayer.setEnabled (false);
		
		add (btnPlayer);
		add (Box.createRigidArea (new Dimension (16, 0)));
		add (btnDice);
		add (Box.createRigidArea (new Dimension (64, 0)));
		add (btnBank);
		add (Box.createRigidArea (new Dimension (64, 0)));
		add (btnMap);
		add (Box.createRigidArea (new Dimension (64, 0)));
		add (btnEnd);
		add (Box.createRigidArea (new Dimension (64, 0)));
		add (debugEnd);
		add (debug1);
		add (debug2);
	}
	
	public void setActionListener (ActionListener listener) {
		btnDice.addActionListener (listener);
		btnBank.addActionListener (listener);
		btnMap.addActionListener (listener);
		btnEnd.addActionListener (listener);
		debugEnd.addActionListener (listener);
		debug1.addActionListener (listener);
		debug2.addActionListener (listener);
	}
	
	public void setBtnDiceStatus (boolean status) {
		btnDice.setEnabled (status);
	}		
	public void setBtnBankStatus (boolean status) {
		btnBank.setEnabled (status);
	}	
	public void setBtnMapStatus (boolean status) {
		btnMap.setEnabled (status);
	}	
	public void setBtnEndStatus (boolean status) {
		btnEnd.setEnabled (status);
	}
	public void setBtnEndGameStatus (boolean status) {
		debugEnd.setEnabled (status);
	}
	
	public void setBtnDebugStatus (boolean status){
		debugEnd.setEnabled(status);
	}
	
	public void updatePlayerTurn (String name) {
		btnPlayer.setText (name);
	}
}