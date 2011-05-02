package src.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SunlabAutodiscoverHack {
	private static ArrayList<InetAddress> addresses;

	public static ArrayList<InetAddress> getSunlabAddresses() {
		if (addresses == null) {
			addresses = new ArrayList<InetAddress>();

			try {
				addresses.add(InetAddress.getByName("cslab8a"));
				addresses.add(InetAddress.getByName("cslab8b"));
				addresses.add(InetAddress.getByName("cslab8c"));
				addresses.add(InetAddress.getByName("cslab8d"));
				addresses.add(InetAddress.getByName("cslab8e"));
				addresses.add(InetAddress.getByName("cslab8f"));
				addresses.add(InetAddress.getByName("cslab8g"));
				addresses.add(InetAddress.getByName("cslab8h"));
				addresses.add(InetAddress.getByName("cslab7a"));
				addresses.add(InetAddress.getByName("cslab7b"));
				addresses.add(InetAddress.getByName("cslab7c"));
				addresses.add(InetAddress.getByName("cslab7d"));
				addresses.add(InetAddress.getByName("cslab7e"));
				addresses.add(InetAddress.getByName("cslab7f"));
				addresses.add(InetAddress.getByName("cslab7g"));
				addresses.add(InetAddress.getByName("cslab7h"));
				addresses.add(InetAddress.getByName("cslab6a"));
				addresses.add(InetAddress.getByName("cslab6b"));
				addresses.add(InetAddress.getByName("cslab6c"));
				addresses.add(InetAddress.getByName("cslab6d"));
				addresses.add(InetAddress.getByName("cslab6e"));
				addresses.add(InetAddress.getByName("cslab6f"));
				addresses.add(InetAddress.getByName("cslab6g"));
				addresses.add(InetAddress.getByName("cslab6h"));
				addresses.add(InetAddress.getByName("cslab5a"));
				addresses.add(InetAddress.getByName("cslab5b"));
				addresses.add(InetAddress.getByName("cslab5c"));
				addresses.add(InetAddress.getByName("cslab5d"));
				addresses.add(InetAddress.getByName("cslab5e"));
				addresses.add(InetAddress.getByName("cslab5f"));
				addresses.add(InetAddress.getByName("cslab5g"));
				addresses.add(InetAddress.getByName("cslab5h"));
				addresses.add(InetAddress.getByName("cslab4a"));
				addresses.add(InetAddress.getByName("cslab4b"));
				addresses.add(InetAddress.getByName("cslab4c"));
				addresses.add(InetAddress.getByName("cslab4d"));
				addresses.add(InetAddress.getByName("cslab4e"));
				addresses.add(InetAddress.getByName("cslab4f"));
				addresses.add(InetAddress.getByName("cslab4g"));
				addresses.add(InetAddress.getByName("cslab4h"));
				addresses.add(InetAddress.getByName("cslab3a"));
				addresses.add(InetAddress.getByName("cslab3b"));
				addresses.add(InetAddress.getByName("cslab3c"));
				addresses.add(InetAddress.getByName("cslab3d"));
				addresses.add(InetAddress.getByName("cslab3e"));
				addresses.add(InetAddress.getByName("cslab3f"));
				addresses.add(InetAddress.getByName("cslab3g"));
				addresses.add(InetAddress.getByName("cslab3h"));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return addresses;
	}
}
