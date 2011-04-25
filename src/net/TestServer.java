package src.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class TestServer {
	public static void main(String[] args) {
		Server server = new Server();
		
		Kryo kryo = server.getKryo();
		kryo.register(TestMessage.class);
		
		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof TestMessage) {
					TestMessage m = (TestMessage)object;
					System.out.println("Received this message from client: " + m.message);
					
					TestMessage response = new TestMessage();
					response.message = "Hello There!";
					connection.sendTCP(response);
				}
			}
		});
		
		server.start();
		
		try {
			server.bind(9999, 10000);
		} catch (Exception e) {
			System.err.println("Server could not bind ports.");
		}
	}
}
