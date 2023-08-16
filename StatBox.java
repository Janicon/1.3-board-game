import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class StatBox extends JPanel {
	private static final Dimension STAT_BOX_SIZE = new Dimension (512, 528);
	// private static final Dimension STAT_AREA_SIZE = new Dimension (496, 480);
	private static final Dimension STAT_AREA_SIZE = new Dimension ((int)STAT_BOX_SIZE.getWidth () - 16, (int)STAT_BOX_SIZE.getHeight () - 48);
	
	private JPanel stats;
	private ArrayList<JLabel> statList;
	
	public StatBox () {
		stats = new JPanel ();
		statList = new ArrayList<> ();
		
		setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
		setAlignmentX (0.5F);
		setAlignmentY (0.5F);
		setMaximumSize (STAT_BOX_SIZE);
		setBackground (new Color (255, 255, 255, 200));
		
		JLabel title = new JLabel ("Player details ");
		title.setAlignmentX (0.5F);
		title.setFont (new Font (GUI.GAME_FONT, Font.PLAIN, 32));
		title.setBorder (new EmptyBorder (8, 0, 0, 0));
		
		stats.setPreferredSize (STAT_AREA_SIZE);
		GridLayout layout = new GridLayout (12, 2);
		layout.setVgap (0);
		stats.setLayout (layout);
		stats.setBackground (GUI.CLEAR);
		stats.setBorder (new EmptyBorder (16, 16, 16, 16));
		
		for (int i = 0; i < 24; i++)
			stats.add (new JLabel (""));
		createDescriptions ();
		// updateStatSummary (new Player ());
		
		add (title);
		add (stats);
	}
	
	public void updateStatSummary (Player player) {
		HouseCard home = player.getHouseCard ();
				
		statList.clear ();
		statList.add (new JLabel ("" + player.getName ()));
		statList.add (new JLabel ("$" + NumberFormat.getNumberInstance (Locale.US).format (player.getMoney ())));
		statList.add (new JLabel ("$" + NumberFormat.getNumberInstance (Locale.US).format (player.getLoan ())));
		if (player.getPos () == -1)
			statList.add (new JLabel ("Not started"));
		else
			statList.add (new JLabel ("" + player.getPos ()));
		
		if (player.getDegree ())
			statList.add (new JLabel ("Yes"));
		else
			statList.add (new JLabel ("No"));
		
		if (player.getCareer () == null)
			statList.add (new JLabel ("None"));
		else
			statList.add (new JLabel (player.getCareer ()));
		
		statList.add (new JLabel ("$" + NumberFormat.getNumberInstance(Locale.US).format(player.getSalary ())));
		statList.add (new JLabel ("$" + NumberFormat.getNumberInstance(Locale.US).format(player.getTax ())));
		if (home == null) {
			statList.add (new JLabel ("None"));
			statList.add (new JLabel ("$0"));
		}
		else {
			statList.add (new JLabel (home.getTitle ()));
			statList.add (new JLabel ("$" + NumberFormat.getNumberInstance(Locale.US).format(home.getValue ())));
		}
		
		if (player.getIsMarried ())
			statList.add (new JLabel ("Married"));
		else
			statList.add (new JLabel ("Single"));
		
		if (player.getNumChildren () == 0)
			statList.add (new JLabel ("None"));
		else
			statList.add (new JLabel ("" + player.getNumChildren ()));
		
		for (int i = 0; i < 12; i++) {
			stats.remove (2*i + 1);
			stats.add (statList.get (i), 2*i + 1);
		}
		
		setFont ();
	}
	
	public void createDescriptions () {
		stats.remove (0);
		stats.add (new JLabel ("Player name"), 0);
		
		stats.remove (2);
		stats.add (new JLabel ("Money"), 2);
		
		stats.remove (4);
		stats.add (new JLabel ("Loaned money"), 4);
		
		stats.remove (6);
		stats.add (new JLabel ("Position"), 6);
		
		stats.remove (8);
		stats.add (new JLabel ("College graduate"), 8);
		
		stats.remove (10);
		stats.add (new JLabel ("Career"), 10);
		
		stats.remove (12);
		stats.add (new JLabel ("Salary"), 12);
		
		stats.remove (14);
		stats.add (new JLabel ("Tax due"), 14);
		
		stats.remove (16);
		stats.add (new JLabel ("Property"), 16);
		
		stats.remove (18);
		stats.add (new JLabel ("Property value"), 18);
		
		stats.remove (20);
		stats.add (new JLabel ("Status"), 20);
		
		stats.remove (22);
		stats.add (new JLabel ("Children"), 22);
	}
	
	public void setFont () {
		JLabel temp;
		for (int i = 0; i < 24; i++) {
			temp = (JLabel)stats.getComponent (i);
			temp.setFont (new Font (GUI.GAME_FONT, Font.PLAIN, 32));
		}
	}
}