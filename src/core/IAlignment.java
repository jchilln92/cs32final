package src.core;
/**
 * An interface allowing for the setting and getting of a tower or creeps element
 */
public interface IAlignment {
	public IAlignment.Alignment getAlignment();
	public void setAlignment(IAlignment.Alignment alignment);
	
	public enum Alignment {
		NEUTRAL, BLUE, RED, YELLOW, GREEN;
		
		/**
		 * Returns the Alignment that deals double damage against the current Alignment.
		 * @return 
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
	}	
}

