package src.core;

import java.awt.Color;

/**
 * An interface allowing for the setting and getting of a tower or creeps element
 */
public interface IAlignment {
	public IAlignment.Alignment getAlignment();
	public void setAlignment(IAlignment.Alignment alignment);
	
	/**
	 *  Enum representing all of the different possible Alignments. Contains methods for checking alignment strengths and weaknesses
	 */
	public enum Alignment implements IPurchasable {
		NEUTRAL, BLUE, RED, YELLOW, GREEN;

		/**
		 * Returns the Alignment that deals bonus damage against this current Alignment.
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
		 * Returns the Alignment that deals less damage against this current Alignment.
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
		 * Returns the Color representing this Alignment.
		 */
		public Color getColor() {
			Color alignmentColor = Color.GRAY;
			switch(this) {
			case BLUE:
				alignmentColor = Color.BLUE;
				break;
			case YELLOW:
				alignmentColor = Color.YELLOW;
				break;
			case RED:
				alignmentColor = Color.RED;
				break;
			case GREEN:
				alignmentColor = Color.GREEN;
				break;
			default:
				alignmentColor = Color.GRAY;
			}
			
			return alignmentColor;
		}
		
		/**
		 * Returns the color of the Alignment in string form.
		 */
		public String toString() {
			String color = "";
			switch(this) {
				case BLUE:
					color = "Blue";
					break;
				case RED:
					color = "Red";
					break;
				case YELLOW:
					color = "Yellow";
					break;
				case GREEN:
					color = "Green";
					break;
				case NEUTRAL:
					color = "Neutral";
					break;
				default:
					color = "$_ALIGNMENT";
			}
			
			return color;
		}

		@Override
		public double getPrice() {
			return CoreConstants.ALIGNMENT_COST;
		}
		
		//setPrice does nothing here as alignments all have the same pre-set price
		@Override
		public void setPrice(double p) {
			
		}
	}	
}

