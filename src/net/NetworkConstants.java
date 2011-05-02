package src.net;

import java.util.ArrayList;

import src.core.Creep;
import src.core.Damage;
import src.core.IAlignment;
import src.core.TargetingInfo;
import src.core.Tower;
import src.core.Upgrade;

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
		k.register(Tower.Type.class);
		k.register(TargetingInfo.class);
		k.register(TargetingInfo.Strategy.class);
		k.register(Damage.class);
		k.register(IAlignment.Alignment.class);
		k.register(Creep.class);
		k.register(Upgrade.class);
	}
}
