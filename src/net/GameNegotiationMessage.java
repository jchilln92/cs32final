package src.net;

public class GameNegotiationMessage {
	public Type type;
	public Object data;
	
	public enum Type {
		GAME_DISCOVER,
		GAME_DISCOVER_RESPONSE,
		ATTEMPT_TO_JOIN,
		ATTEMPT_TO_JOIN_RESPONSE,
		CREEPS_UPDATE,
		TOWERS_UPDATE,
		WAVE
	}
}
