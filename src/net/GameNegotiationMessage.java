package src.net;

public class GameNegotiationMessage {
	public Type type;
	public Object data;
	
	public enum Type {
		GAME_DISCOVER,
		GAME_DISCOVER_RESPONSE,
		ATTEMPT_TO_JOIN,
		START_GAME,
		BOOT
	}
}
