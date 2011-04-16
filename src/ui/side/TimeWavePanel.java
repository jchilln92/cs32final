package src.ui.side;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Runner;
import src.core.Game;

public class TimeWavePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	private static final String elapsedText = "Elapsed Time:";
	
	private JLabel elapsedLabel;
	private JLabel elapsedValueLabel;
	
	public TimeWavePanel(Game g) {
		super(new GridBagLayout());
		
		game = g;
		
		elapsedLabel = new JLabel(elapsedText);
		elapsedValueLabel = new JLabel();
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(elapsedLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		add(elapsedValueLabel, c);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateDisplay();
	}
	
	public void updateDisplay() {
		// TODO: find a better way to format this (with a preexisting class)
		long secondsElapsed = (game.getElapsedTime() * Runner.tickDuration) / 1000;
		
		long hours = secondsElapsed / 3600;
		secondsElapsed = secondsElapsed % 3600;
		
		long minutes = secondsElapsed / 60;
		secondsElapsed = secondsElapsed % 60;
		
		long seconds = secondsElapsed;
		
		String time = Long.toString(hours) + ":" + Long.toString(minutes) + ":" + Long.toString(seconds);
		elapsedValueLabel.setText(time);
	}
}
