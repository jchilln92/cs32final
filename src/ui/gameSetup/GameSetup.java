package src.ui.gameSetup;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.GameMain;
import src.core.Map;
import src.ui.MapComponent;

public class GameSetup extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String createGameText = "Create your game:";
	private static final String createNameText = "Game name:";

	private JLabel createGameLabel;
	private JLabel createNameLabel;
	private JLabel mapLabel;
	
	private JTextField createNameField;
	
	private JButton playButton;
	private JButton cancelButton;
	
	private JScrollPane mapListPane;
	private JList mapList;

	private MapComponent mc;
	private GameMain gm;
	
	
	public GameSetup(GameMain gameMain) {
		super(new GridBagLayout());
		setSize(800, 600);
		
		this.gm = gameMain;
		
		mc = new MapComponent();
		mc.setGridOn(true);
		mc.setSize(400, 400);
	
		createGameLabel = new JLabel(createGameText);
		createNameLabel = new JLabel(createNameText);
		mapLabel = new JLabel("Map:");
		
		createNameField = new JTextField("");
		
		playButton = new JButton("Begin Game");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gm.createGame(mc.getMap());
				gm.showGameScreen();
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gm.showTitleScreen();
			}
		});
		
		Object mapNames[] = Map.getMapNames().toArray();
		mapList = new JList(mapNames);
		mapList.setSelectedIndex(0);
		mc.setMap(Map.getMapByName((String)mapNames[0]));
		mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mapList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mc.setMap(Map.getMapByName((String)mapList.getSelectedValue()));
			}
		});
		
		mapListPane = new JScrollPane(mapList);
		createSinglePlayerSetup();
	}


	public void createSinglePlayerSetup(){
		removeAll();
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,0,10,0);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		add(createGameLabel,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(mapLabel,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		add(mapListPane,c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,20,0,0);
		mc.setPreferredSize(new Dimension(400, 400));
		add(mc,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.ipady = 20;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0,0,0,0);
		add(cancelButton,c);
		
		c.gridx = 3;
		c.gridy = 3;
		c.ipady = 20;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_END;
		add(playButton,c);
		/*c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipady = 20;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		add(cancelButton,c);
		
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(mapLabel,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		add(mapListPane,c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,20,0,0);
		mc.setPreferredSize(new Dimension(400, 400));
		add(mc,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 5;
		c.ipady = 20;
		c.insets = new Insets(0,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(playButton,c);
		
		validate();*/
	}
	//i might move this to a different class because the button functions are somewhat different! :D
	public void createMultiplayerSetup(){
		removeAll();
		
		playButton.setText("Create Game");
		cancelButton.setText("Cancel");
		

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,0,20,0);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		add(createGameLabel,c);
		

		c.insets = new Insets(0,0,0,0);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(createNameLabel,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		add(createNameField,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		add(mapLabel,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		add(mapListPane,c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,20,0,0);
		mc.setPreferredSize(new Dimension(400, 400));
		add(mc,c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.ipady = 20;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0,0,0,0);
		add(cancelButton,c);
		
		c.gridx = 3;
		c.gridy = 4;
		c.ipady = 20;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_END;
		add(playButton,c);
		
		
		validate();
	}
}
