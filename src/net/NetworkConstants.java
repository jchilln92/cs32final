package src.net;

import com.esotericsoftware.kryo.Kryo;

public class NetworkConstants {
	public static final int tcpPort = 9999;
	public static final int udpPort = 10000;
	
	public static void registerKryoClasses(Kryo k) {
		k.register(GameNegotiationMessage.class);
		k.register(GameNegotiationMessage.Type.class);
		k.register(AvailableGame.class);
	}
}
