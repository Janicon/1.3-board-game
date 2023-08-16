public class driver {
	public static void main (String[] args) {
		ThatsLife game = new ThatsLife ();
		GUI gui = new GUI ();
		Controller controller = new Controller (gui, game);
	}
}