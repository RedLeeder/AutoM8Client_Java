package src;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

public class Client {
	
	public String OSName;
	public String OSVer;
	public String OSArch;
	public String JVer;
	public String HostName;
	public String MACAddress;
	public String ClientID;
	
	public Client() {
		OSName = System.getProperty("os.name");
		OSVer = System.getProperty("os.version");
		OSArch = System.getProperty("os.arch");
		JVer = System.getProperty("java.version");
		HostName = getHostName();
		MACAddress = getMAC();
		ClientID = HostName + "@" + MACAddress;
	}
	
	public void printClient() {
		System.out.println("Host: \t" + HostName);
		System.out.println("Operating System: \t" + OSName + "v" + OSVer + " " + OSArch);
		System.out.println("Java Version: \t" + JVer);
		System.out.println("MAC Address: \t" + MACAddress);
	}
	
	private String getHostName()
	{
	    Map<String, String> env = System.getenv();
	    if (env.containsKey("COMPUTERNAME"))
	        return env.get("COMPUTERNAME");
	    else if (env.containsKey("HOSTNAME"))
	        return env.get("HOSTNAME");
	    else
	        return "Unknown Computer";
	}
	
	private String getMAC() {
		String macAddress = "";
		try {
			InetAddress localHost;
			localHost = InetAddress.getLocalHost();
			NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
			byte[] hardwareAddress = ni.getHardwareAddress();
			String[] hexadecimal = new String[hardwareAddress.length];
			for (int i = 0; i < hardwareAddress.length; i++) {
			    hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
			}
			macAddress = String.join("-", hexadecimal);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return macAddress;
	}
	
}
