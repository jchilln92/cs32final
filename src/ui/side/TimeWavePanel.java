package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Runner;
import src.core.Game;

public class TimeWavePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	private static final String waveText = "Wave: ";
	private static final String nextWaveText = "Next wave in: ";
	private static final String elapsedText = "Time elapsed: ";
	private static final String nextWaveButtonText = "Next Wave!";
	private static final String fastForwardButtonText = ">>";
	private static final int waveTime = 60;
	
	private JLabel waveNumberLabel;
	private JLabel waveNumberValueLabel;
	
	private JLabel nextWaveValueLabel;
	private JLabel nextWaveLabel;
			
	private JLabel elapsedLabel;
	private JLabel elapsedValueLabel;

	private JButton nextWaveButton;
	private JButton fastForwardButton;
	
	public TimeWavePanel(Game g) {
		super(new GridBagLayout());
		
		game = g;
		
		waveNumberLabel = new JLabel(waveText);
		waveNumberValueLabel = new JLabel("1");
		
		nextWaveLabel = new JLabel(nextWaveText);
		nextWaveValueLabel = new JLabel();
		
		elapsedLabel = new JLabel(elapsedText);
		elapsedValueLabel = new JLabel();
		
		nextWaveButton = new JButton(nextWaveButtonText);
		fastForwardButton = new JButton(fastForwardButtonText);		

		GridBagConstraints c = new GridBagConstraints();
		//c.anchor = GridBagConstraints.LINE_END;

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(waveNumberLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(waveNumberValueLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(nextWaveLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		add(nextWaveValueLabel, c);

		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		add(elapsedLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.NONE;
		add(elapsedValueLabel, c);				

		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		add(nextWaveButton, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		add(fastForwardButton, c);		

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateDisplay();
	}
	
	public void updateDisplay() {
		// TODO: find a better way to format this (with a preexisting class)
		
		//update wave number
		
		//update Next Wave in
		
		//update Time elapsed		
		long secondsElapsed = (game.getElapsedTime() * Runner.tickDuration) / 1000;
		
		long hours = secondsElapsed / 3600;
		String hoursText = Long.toString(hours);
		if(hours < 10)
			hoursText = "0"+hoursText;
		secondsElapsed = secondsElapsed % 3600;
		
		long minutes = secondsElapsed / 60;
		String minutesText = Long.toString(minutes);
		if(minutes < 10)
			minutesText = "0"+minutesText;
		secondsElapsed = secondsElapsed % 60;
		
		long seconds = secondsElapsed;
		
		String secondsText = Long.toString(seconds);
		if(seconds < 10)
			secondsText = "0"+secondsText;
		
		String time = hoursText + ":" + minutesText + ":" + secondsText;
		elapsedValueLabel.setText(time);
	}
}
