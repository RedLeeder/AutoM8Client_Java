package src;
import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//http://www.dreamincode.net/forums/topic/190944-creating-an-updater-in-java/
public class Updater {
	public static String webVersion = "";
	private static String versionURL = "";
	private static String jarURL = "";
	private static String zipURL = "";
	
	public Updater() {
		try {
			// Published Version
			versionURL = "https://raw.githubusercontent.com/Shadow-Labs/Cheezit/published/ArkBot/ArkBotFiles/Version/CurrentVersion.txt";
			zipURL = "https://github.com/Shadow-Labs/Cheezit/archive/published.zip";
			
			webVersion = getLatestVersion();
			
			// Published Version
			jarURL = "https://github.com/Shadow-Labs/Cheezit/blob/master/ArkBot/ArkBot" + webVersion + ".jar?raw=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getLatestVersion() throws Exception{
		String data = getData(versionURL);
		webVersion = data.substring(0,6);
		System.out.println("Local Version: " + AutoM8.version);
		System.out.println("Web Version: " + webVersion);
		return data.substring(0, 6);
	}
	
	public static String getData(String address) {
		try {
			URL url = new URL(address);
			
			InputStream html = null;
			html = url.openStream();
			
			int c = 0;
			StringBuffer buffer = new StringBuffer("");
			while (c != -1) {
				c = html.read();
				buffer.append((char) c);
			}
			return buffer.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void DownloadZip() {
		UpdateWindow(zipURL);
	}
	
	private void UpdateWindow(String downloadURL) {        
        // Download File
		try {
			URL url = new URL(downloadURL);
	        URLConnection conn = url.openConnection();
	        InputStream is = conn.getInputStream();
	        BufferedOutputStream fOut = null;
	        if (downloadURL == zipURL) {
	        	fOut = new BufferedOutputStream(new FileOutputStream(new File("ArkBot" + webVersion + ".zip")));
	        } else if (downloadURL == jarURL) {
	        	fOut = new BufferedOutputStream(new FileOutputStream(new File("ArkBot" + webVersion + ".Jar")));
	        }
	        byte[] buffer = new byte[32 * 1024];
	        int bytesRead = 0;
	        while ((bytesRead = is.read(buffer)) != -1) {
	        	System.out.println(bytesRead);
	            fOut.write(buffer, 0, bytesRead);
	        }
	        fOut.flush();
	        fOut.close();
	        is.close();
	        System.out.println("Download Complete.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void download() throws HeadlessException, Exception
    {
    	if (newVersionExists()) {
	        try {
	        	UpdateWindow(jarURL);
	        	
	            System.out.println("Update Finished.");
	            System.out.println("Lauching AutoM8" + webVersion);
	            launch();
	        } catch (Exception ex) {
	        	System.out.println("JAVA ERROR: " + ex.getMessage());
	            ex.printStackTrace();
	        }
    	}
    }
    private void launch() throws FileNotFoundException, UnsupportedEncodingException
    {
    	
    	String[] run = {"java","-jar","ArkBot" + webVersion + ".jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Update Version
        System.out.println("Updated CurrentVersion.txt");
        PrintWriter writer = new PrintWriter ("AutoM8Files\\Version.conf", "UTF-8");
        writer.print(webVersion);
        writer.close();
        
        AutoM8.log.CloseLog();
        System.exit(0);
    }
    
    private boolean newVersionExists() throws Exception {
    	boolean version = false;
    	if (!AutoM8.version.equals(webVersion)) {
    		version = true;
    	}
    	return version;
    }
}
