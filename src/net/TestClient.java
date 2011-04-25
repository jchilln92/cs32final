package src.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class TestClient {
	public static void main(String[] args) {
		final Client client = new Client();
		
		client.addListener(new Listener() {
			public void received(Connection c, Object o) {
				if (o instanceof TestMessage) {
					TestMessage m = (TestMessage)o;
					System.out.println("Recieved message from host: " + m.message);
				}
			}
		});
		
		Kryo kryo = client.getKryo();
		kryo.register(TestMessage.class);
		
		client.start();
		
		//InetAddress host = client.discoverHost(10000, 5000);
		InetAddress host = null;
		try {
			host = InetAddress.getByName("cslab7g");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			client.connect(5000, host, 9999, 10000);
			TestMessage m = new TestMessage();
			m.message = "Hello World!";
			int sent = client.sendTCP(m);
			System.out.println("Sent " + sent + " bytes");
		} catch (Exception e) {
			System.err.println("Could not connect to host: " + host.getHostAddress());
		}
		
		while (true) {}
	}
}
