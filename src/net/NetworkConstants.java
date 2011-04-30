package src.net;

import java.util.ArrayList;

import src.core.Creep;
import src.core.Tower;

import com.esotericsoftware.kryo.Kryo;

public class NetworkConstants {
	public static final int tcpPort = 9999;
	public static final int udpPort = 10000;
	
	public static final int gameDisoveryTimeout = 1000;
	public static final int gameConnectTimeout = 4000;
	
	public static void registerKryoClasses(Kryo k) {
		k.register(GameNegotiationMessage.class);
		k.register(GameNegotiationMessage.Type.class);
		k.register(AvailableGame.class);
		k.register(ArrayList.class);
		k.register(Tower.class);
		k.register(Creep.class);
	}
}
