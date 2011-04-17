package src.ui.side;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.Border;

import src.GameController;

/**
 * This is a panel that displays information on a player's gold and health,
 * which is typically shown in the sidebar.
 */
public class PauseQuitPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameController gc;
	
	private PopupFactory popupGenerator;
	private JButton pauseButton;
	private JButton quitButton;
	private JButton confirmQuitButton;
	private JButton returnToGameButton;
	
	private JPanel quitPanel;
	private Popup quitPopup;
	
	
	public PauseQuitPanel(GameController controller) {
		super(new GridBagLayout());
		
		this.gc = controller;

		popupGenerator = PopupFactory.getSharedInstance();
		
		quitPanel = new JPanel();
		quitPanel.setLayout(new BorderLayout());
		Border borderLine = BorderFactory.createLineBorder(Color.BLACK);
		quitPanel.setBorder(borderLine);
		quitPanel.add(new JLabel("Quit current game?"), BorderLayout.PAGE_START);
		
		confirmQuitButton = new JButton("Totes!");
		confirmQuitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//return to the main menu

			}
		});
		returnToGameButton = new JButton("So Inapropro...");
		returnToGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gc.togglePause(false);
				quitPopup.hide();

			}
		});		
		quitPanel.add(confirmQuitButton, BorderLayout.LINE_START);
		quitPanel.add(returnToGameButton, BorderLayout.LINE_END);

		

				
		

		pauseButton = new JButton("Pause");
		pauseButton.setActionCommand("pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("pause")) {
					gc.togglePause(true);
					pauseButton.setActionCommand("resume");
					pauseButton.setText("Resume");
				} else if (e.getActionCommand().equals("resume")) {
					gc.togglePause(false);
					pauseButton.setActionCommand("pause");
					pauseButton.setText("Pause");
				}
			}
		});
		
		quitButton = new JButton("Quit");
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gc.togglePause(true);
				makePopup();

			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(pauseButton, c);

		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(quitButton, c);
	}
	
	public void makePopup(){

		quitPopup = popupGenerator.getPopup(this, quitPanel, 
				320-(int)quitPanel.getSize().getWidth()/2, 
				240-(int)quitPanel.getSize().getHeight()/2);
		
		quitPopup.show();	
	}

}

