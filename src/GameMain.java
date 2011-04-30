package src;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import src.core.Game;
import src.core.Map;
import src.core.Player;
import src.core.Tower;
import src.ui.MapComponent;
import src.ui.title.TitleScreen;
import src.ui.side.Sidebar;
import src.ui.gameSetup.GameSetup;
import src.ui.lobby.Lobby;

/**
 * The main entry point for the entire application.  Displays the main window.
 */
public class GameMain extends JFrame {
	private static final long serialVersionUID = 1L;	
	
	private JPanel mainPanel;

	private static Thread thread = new Thread();
	private TitleScreen ts;
	private GameSetup gs;
	private Lobby lobby;
	private JPanel gamePanel;
	
	private enum PanelID {
		TITLE_SCREEN, GAME_SETUP, GAME_SCREEN, LOBBY;
	}

	public GameMain() {
		// set up window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600); // TODO: Figure out resize and/or move this to
							// another file
		
		ts = new TitleScreen(this);
		gs = new GameSetup(this);
		lobby = new Lobby(this);
		gamePanel = new JPanel(); //gotta convert this into a class later probably
		
		// initialize the card layout panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());
		mainPanel.add(ts, PanelID.TITLE_SCREEN.toString());
		mainPanel.add(lobby, PanelID.LOBBY.toString());
		
		getContentPane().add(mainPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final GameMain main = new GameMain();
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

	public void showTitleScreen() {
		CardLayout layout = (CardLayout) mainPanel.getLayout();
		layout.show(mainPanel, PanelID.TITLE_SCREEN.toString());
	}
	
	public void showGameSetup(boolean multiplayer) {
		mainPanel.remove(gs);
		if(multiplayer){
			gs.createMultiplayerSetup();
		}
		else{
			gs.createSinglePlayerSetup();			
		}
		mainPanel.add(gs, PanelID.GAME_SETUP.toString());
		CardLayout layout = (CardLayout) mainPanel.getLayout();
		layout.show(mainPanel, PanelID.GAME_SETUP.toString());
	}
	
	public void showGameScreen(){
		mainPanel.add(gamePanel, PanelID.GAME_SCREEN.toString());
		CardLayout layout = (CardLayout) mainPanel.getLayout();
		layout.show(mainPanel, PanelID.GAME_SCREEN.toString());	
	}
	
	public void showLobby(){
		CardLayout layout = (CardLayout) mainPanel.getLayout();
		layout.show(mainPanel, PanelID.LOBBY.toString());	
	}
	
	public void createGame(Map selectedMap){
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		
		// set up some test data that will be run
		Map m = selectedMap;

		// initialize a game
		Game g = new Game();

		// single creep
		/*Creep c = new Creep();
		c.setPath(m.getPath());
		c.setPosition(m.getPath().getPoint(0));
		*/
		ArrayList<Tower> towers = new ArrayList<Tower>();

		// a player
		Player p = new Player();

		g.setMap(m);
		g.setTowers(towers);
		g.setPlayer(p);
		
		// set up a gamecontroller to mediate interaction between backend and frontend
		GameController gc = new GameController();
		gc.setGame(g);
		gc.setGameMain(this);

		// map component to draw everything
		MapComponent mc = new MapComponent(false);
		mc.setMap(m);
		mc.setSize(600, 600);
		mc.setGridOn(true);
		mc.setGameController(gc);
		gamePanel.add(mc, BorderLayout.CENTER);

		// setup sidebar
		Sidebar s = new Sidebar(gc);
		gamePanel.add(s, BorderLayout.LINE_END);
		
		gc.setSidebar(s);

		Runner r = new Runner();
		r.setGameController(gc);
		thread = new Thread(r, "Runner Thread");
		thread.start();
		
		gamePanel.validate();
	}
	
	public void resetGame(){
		mainPanel.remove(gamePanel);
		gamePanel = new JPanel();
		
		try {
			thread.interrupt();
		} catch (Exception e) {
			
		}
		
		gamePanel.validate();
		showTitleScreen();
	}
}
