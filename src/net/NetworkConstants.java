package src.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

public class NetworkConstants {
	public static final int tcpPort = 9999;
	public static final int udpPort = 10000;
	
	public static void registerKryoClasses(Kryo k) {
		k.register(GameNegotiationMessage.class);
		k.register(GameNegotiationMessage.Type.class);
		k.register(AvailableGame.class);
		k.register(TestData.class);
		k.register(ITestData.class);
		k.register(ObjectSpace.InvokeMethod.class);
		k.register(ObjectSpace.InvokeMethodResult.class);
	}
}
