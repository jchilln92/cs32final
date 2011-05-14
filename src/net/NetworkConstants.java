package src.net;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import src.core.Bullet;
import src.core.Creep;
import src.core.CreepPath;
import src.core.Damage;
import src.core.DamageApplication;
import src.core.IAlignment;
import src.core.TargetingInfo;
import src.core.Tower;
import src.core.Upgrade;

import com.esotericsoftware.kryo.Kryo;

public class NetworkConstants {
	public static final int tcpPort = 9999;
	public static final int udpPort = 10000;
	
	public static final int bufferSize = 128 * 1024; // in bytes
	
	public static final int gameDisoveryTimeout = 1000;
	public static final int gameConnectTimeout = 4000;
	
	/**
	 * Classes need to be registered with Kryo serialization in the exact same order on both sides.
	 * This methods helps us do that.
	 */
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
		k.register(HashMap.class);
		k.register(CreepPath.class);
		k.register(Point2D.Double.class);
		k.register(Creep.Type.class);
		k.register(DamageApplication.class);
		k.register(Bullet.class);
	}
}
