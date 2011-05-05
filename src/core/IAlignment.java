package src.core;

import java.awt.Color;

/**
 * An interface allowing for the setting and getting of a tower or creeps element
 */
public interface IAlignment {
	public IAlignment.Alignment getAlignment();
	public void setAlignment(IAlignment.Alignment alignment);
	
	public enum Alignment implements IPurchasable {
		NEUTRAL, BLUE, RED, YELLOW, GREEN;
		private static double ALIGNMENT_COST = 5;

		/**
		 * Returns the Alignment that deals double damage against the current Alignment.
		 */
		public Alignment getWeakTo() {
			Alignment counter = NEUTRAL;
			switch(this) {
			case BLUE:
				counter = YELLOW;
				break;
			case RED:
				counter = BLUE;
				break;
			case YELLOW:
				counter = GREEN;
				break;
			case GREEN:
				counter = RED;
				break;
			}
			return counter;
		}
		
		/**
		 * Returns the Alignment that deals only half damage against the current Alignment.
		 */
		public Alignment getStrength() {
			Alignment defeats = NEUTRAL;
			switch(this) {
			case BLUE:
				defeats = RED;
				break;
			case RED:
				defeats = GREEN;
				break;
			case YELLOW:
				defeats = BLUE;
				break;
			case GREEN:
				defeats = YELLOW;
				break;
			}
			return defeats;
		}
		
		/**
		 * Returns the Color representing the Alignment.
		 */
		public Color getColor() {
			Color alColor = Color.GRAY;
			switch(this) {
			case BLUE:
				alColor = Color.BLUE;
				break;
			case YELLOW:
				alColor = Color.YELLOW;
				break;
			case RED:
				alColor = Color.RED;
				break;
			case GREEN:
				alColor = Color.GREEN;
				break;
			default:
				alColor = Color.GRAY;
			}
			return alColor;
		}
		
		/**
		 * Returns the color of the Alignment in string form.
		 */
		public String toString() {
			String desc = "";
			switch(this) {
			case BLUE:
				desc = "Blue";
				break;
			case RED:
				desc = "Red";
				break;
			case YELLOW:
				desc = "Yellow";
				break;
			case GREEN:
				desc = "Green";
				break;
			case NEUTRAL:
				desc = "Neutral";
				break;
			default:
				desc = "$_ALIGNMENT";
			}
			return desc;
		}

		@Override
		public double getPrice() {
			return ALIGNMENT_COST;
		}
		
		//setPrice does nothing here as alignments always have a set price
		@Override
		public void setPrice(double p) {
			
		}
	}	
}

