package src.core;
/**
 * An interface allowing for the setting and getting of a tower or creeps element
 */
public interface IAlignment {
	public IAlignment.Alignment getAlignment();
	public void setAlignment(IAlignment.Alignment alignment);
	public enum Alignment{
		NEUTRAL, BLUE, RED, YELLOW, GREEN;
	}	
}

