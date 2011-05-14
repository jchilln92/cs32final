package src.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Helps set up connections for games in the SunLab.
 * (We had problems making auto-discovery work in the sunlab. For the sake of making multiplayer work in the sunlab without unnecessary hassle,
 * we created this class)
 */
public class SunlabAutodiscoverHack {
	private static ArrayList<InetAddress> addresses;

	public static ArrayList<InetAddress> getSunlabAddresses() {
		if (addresses == null) {
			addresses = new ArrayList<InetAddress>();

			try {
				addresses.add(InetAddress.getByName("cslab6a"));
				addresses.add(InetAddress.getByName("cslab6b"));
				addresses.add(InetAddress.getByName("cslab6c"));
				addresses.add(InetAddress.getByName("cslab6d"));
				addresses.add(InetAddress.getByName("cslab6e"));
				addresses.add(InetAddress.getByName("cslab6f"));
				addresses.add(InetAddress.getByName("cslab6g"));
				addresses.add(InetAddress.getByName("cslab6h"));
			} catch (UnknownHostException e) {
				System.out.println("Sunlab autodiscovery hack failed to find an address");
			}
		}

		return addresses;
	}
}
