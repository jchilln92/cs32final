package src;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import src.ui.TitleScreen;

/**
 * The main entry point for the entire application.  Displays the main window.
 */
public class GameMain extends JFrame {
	private static final long serialVersionUID = 1L;	
	
	private JPanel mainPanel;
	private static final GameMain main = new GameMain();
	private static Thread thread = new Thread();
	private JPanel gamePanel;

	public GameMain() {
		// set up window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600); // TODO: Figure out resize and/or move this to
							// another file
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());
		getContentPane().add(mainPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				
				TitleScreen title = new TitleScreen(main);
				main.showScreen(title);
				main.setVisible(true);

				// refresh the window at about 30 fps
				Timer t = new Timer(33, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						main.repaint();
					}
				});

				t.start();
			}
		});
	}
	
	public void showScreen(JPanel screen) {
		mainPanel.removeAll();
		mainPanel.add(screen, "visible-panel");
		validate();
	}
	
	public void resetGame(){
		showScreen(new TitleScreen(main));
		
		try {
			thread.interrupt();
		} catch (Exception e) {
			
		}
		
		mainPanel.validate();
	}
}