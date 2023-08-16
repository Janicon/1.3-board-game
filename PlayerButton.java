import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.lang.Math;
import java.text.NumberFormat;
import java.util.Locale;

public class PlayerButton extends JPanel {
	private static final Dimension SIZE = new Dimension (256, 72);
	private static int count = 1;
	
	private Player player;
	private JLabel name;
	private JLabel money;
	private JLabel career;
	private JProgressBar life;
	
	public PlayerButton (Player player) {
		this.player = player;
		name = new JLabel ();
		money = new JLabel ();
		career = new JLabel ();
		
		setPreferredSize (SIZE);
		setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
		setBorder (new LineBorder (GUI.BACKGROUND_COLOR));
		
		name.setAlignmentX (Component.CENTER_ALIGNMENT);
		money.setAlignmentX (Component.CENTER_ALIGNMENT);
		career.setAlignmentX (Component.CENTER_ALIGNMENT);
		
		/* Creates progress bar to represent game progression */
		life = new JProgressBar ();
		life.setMaximumSize (new Dimension (240, 20));
		life.setBackground (new Color (160, 160, 160));
		life.setForeground (Color.WHITE);
		life.setBorder (new LineBorder (new Color (238, 238, 238)));
		life.setString ("Age");
		life.setStringPainted (true);
		life.setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.BLACK; }
			protected Color getSelectionForeground() { return Color.BLACK; }
		});
		life.setValue (0);
		
		
		updateDisplay (this.player);
		
		add (name);
		add (money);
		add (career);
		add (life);
		count++;
	}
	
	public Player getPlayer () {
		return player;
	}
	
	private int findPercent (int num, int limit) {		
		return Math.round (num * 100.0f / limit);
	}
	
	public void toggleHighlight (boolean status) {
		if (status) {
			setBorder (new LineBorder (Color.BLACK, 1, true));
			setBackground (new Color (247, 249, 143));
		}
		else {
			setBorder (new LineBorder (GUI.BACKGROUND_COLOR, 1, true));
			setBackground (new Color (238, 238, 238));
		}
	}
	
	public void updateDisplay (Player player) {
		name.setText (player.getName ());
		money.setText ("$ " + NumberFormat.getNumberInstance(Locale.US).format(player.getMoney ()));
		if (player.getCareer () == null)
			career.setText ("No career");
		else
			career.setText (player.getCareer ());
		// life.setValue (findPercent (player.getPos (), board.getSize ()));
		life.setValue (0);
	}
	
	public void updateDisplay () {
		updateDisplay (this.player);
	}
}