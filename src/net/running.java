package src.net;


import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryo.serialize.CollectionSerializer;
import com.esotericsoftware.kryo.serialize.FieldSerializer;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;
import com.esotericsoftware.kryonet.rmi.RemoteObject;
import com.esotericsoftware.kryonet.rmi.TimeoutException;


public class running {
	
	public static void main(String[] args) throws IOException
	{
		/**
		 * 
		 * Both the object operations using the ObjectSpace 
		 * and the Client/Server-only work; this is test code
		 * to allow you to see what goes on.
		 * 
		 * For extra help, refer to the kryonet site.
		 * http://code.google.com/p/kryonet/ if you are too lazy.
		 * 
		 */
		
		
		Mess ex = new RepMes("beetlejuice ", 3);
		 
		Client cli = new Client();
		cli.start();
		cli.connect(5000, "138.16.4.84", 30302, 20302);
		System.out.println("Client has started.");
		
		Kryo k2 = cli.getKryo();
		k2.register(Mess.class);
		k2.register(RepMes.class);
		k2.register(ObjectSpace.InvokeMethod.class);
		ObjectSpace.registerClasses(k2);
		System.out.println("Client has registered classes.");
		
		
		//ObjectSpace cospace = new ObjectSpace(cli);		
		//cospace.register(83, ex);
		//System.out.println("Example message registered.");
		
		Mess read = ObjectSpace.getRemoteObject(cli, 83, Mess.class);
		System.out.println("Client has obtained remote object.\n");
		//System.out.println("Expected output:");
		//System.out.println(ex.state());
		//ex.hello();
		System.out.println("\n=-=-=-=-=-=-=-=-=-=\n");
	
			System.out.println("Output:\nAttempting to read object...");
			//read.hello();
			System.out.println(read.state());
			
			InetAddress ad = cli.discoverHost(20302, 5000);
			System.out.println(ad);
						
			System.out.println("We're done here.");
			cli.close();
			System.exit(0);
		
		
		/**
		  //THIS WORKS.
		 
		Server serv = new Server();
		serv.start();
		serv.bind(30302);
		
		Kryo kry1 = serv.getKryo();
		kry1.register(ArrayList.class);
		kry1.register(CTxtWave.class);
		kry1.register(Creep.class);
		
		serv.addListener(new Listener() {
			   public void received (Connection connection, Object object) {
			      if (object instanceof CTxtWave) {
			         CTxtWave msg = (CTxtWave)object;
			         msg.contains(); 
			      }
			   }
			});
		
		Client client = new Client();
		client.start();
		client.connect(5000, "127.0.0.1", 30302);
		
		Kryo kry2 = client.getKryo();
		kry2.register(ArrayList.class);
		kry2.register(CTxtWave.class);
		kry2.register(Creep.class);
		
		CTxtWave wv = new CTxtWave("Dom");
		
		wv.add(Creep.randCreep());
		wv.add(Creep.randCreep());
		wv.add(Creep.randCreep());
		wv.add(Creep.randCreep());
		wv.add(Creep.randCreep());
		
		System.out.println("Client has created creep wave\nSending to server over TCP:");
		client.sendTCP(wv);
		
		client.close();
		serv.close();
		System.exit(0);*/
	}
}




