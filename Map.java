import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Map extends JPanel {
	private final static Dimension TILESIZE = new Dimension (128, 64);
	
	private ThatsLife game; //Remove
	private ArrayList<ArrayList<JPanel>> panels;
	
	public Map () {
		panels = new ArrayList<ArrayList<JPanel>> (3);
		GridLayout layout = new GridLayout (3, 9);
		layout.setHgap (-1);
		layout.setVgap (0);
		
		setLayout (layout);
		setOpaque (false);
		
		for (int i = 0; i < 27; i++)
			add (createPanel (GUI.CLEAR));
		
		for (int i = 0; i < 3; i++)
			panels.add (new ArrayList <> ());
	}
	
	private JPanel createPanel (Color color) {
		JPanel panel = new JPanel ();
		
		panel.setPreferredSize (TILESIZE);
		panel.setBackground (color);
		
		if (color.equals (GUI.CLEAR)) {
			panel.setBorder (new EmptyBorder (2, 2, 2, 2));
			panel.setOpaque (false);
		}
		else
			panel.setBorder (new LineBorder (Color.BLACK, 2));
		
		return panel;
	}
	
	private JPanel getPanel (int row, int col) {
		return (JPanel)getComponent (9*row + col);
	}
	
	public void loadMap (ThatsLife game) {
		this.game = game;
		
		int count = 0;
		int branches[][] = game.getBranchPoints ();
	
		for (int i = 0; i < game.getTileCount (); i++) {
			if (i == branches[count][0]) {
				for (int j = 0; j < game.getJunction (branches[count][1]).getLength (); j++) {
					panels.get (0).add (createPanel (game.getTileColor (branches[count][1], j)));
					if (j == 0)
						panels.get(1).add (createPanel (game.getTileColor (i)));
					else if (j + 1 == game.getJunction (branches[count][1]).getLength ())
						panels.get(1).add (createPanel (game.getTileColor (i + 1)));
					else
						panels.get (1).add (createPanel (GUI.CLEAR));
					panels.get (2).add (createPanel (game.getTileColor (branches[count][2], j)));
				}
				i++;
				if (count + 1 < branches.length)
					count++;
			}
			else {
				panels.get (0).add (createPanel (GUI.CLEAR));
				panels.get (1).add (createPanel (game.getTileColor (i)));
				panels.get (2).add (createPanel (GUI.CLEAR));
			}
		}
		
		drawMap (0);
	}
	
	private void clearColor () {
		JPanel curPanel;
	
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++) {
				curPanel = getPanel (i, j);
				curPanel.removeAll ();
				curPanel.setOpaque (false);
				curPanel.setBorder (new EmptyBorder(2, 2, 2, 2));
			}
	}
	
	private void drawPanel (JPanel source, JPanel target) {
		target.setBackground (source.getBackground ());
		target.setBorder (source.getBorder ());
		target.setOpaque (source.isOpaque ());
	}
	
	public void drawMap (int pos) {
		int start, end, tileGet;
		
		/* If less than 4 tiles to the left of current position exist */
		if (pos < 4) {
			start = 4 - pos;
			end = 9;
			tileGet = pos - 4;
		}
		else {
			start = 0;
			tileGet = (pos - 4);
			/* If more than 4 tiles to right of current position exist */
			if (pos + 5 < game.getTotalTiles ())
				end = 9;
			else
				end = 4 + (game.getTotalTiles () - pos);
		}
		
		clearColor ();
		
		for (int i = start; i < end; i++) {
			drawPanel (panels.get (0).get (tileGet + i), getPanel (0 , i));
			drawPanel (panels.get (1).get (tileGet + i), getPanel (1, i));
			drawPanel (panels.get (2).get (tileGet + i), getPanel (2 , i));
		}
	}
}