package src;
import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//http://www.dreamincode.net/forums/topic/190944-creating-an-updater-in-java/
public class Updater {
	public static String webVersion = "";
	private static String versionURL = "";
	private static String jarURL = "";
	Gson g;
	
	public Updater() {
		try {
			g = new Gson();
			versionURL = "https://api.github.com/repos/RedLeeder/AutoM8Client_Java/releases/latest";			
			getLatestVersion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getLatestVersion() throws Exception{
		String data = getData(versionURL);
		data = data.substring(0, data.length() - 1);
		JsonObject obj = JsonParser.parseString(data).getAsJsonObject();
		webVersion = obj.get("tag_name").getAsString();
		jarURL = obj.get("assets").getAsJsonArray().get(0).getAsJsonObject().get("browser_download_url").getAsString();
		System.out.println("Local Version: " + AutoM8.version);
		System.out.println("Web Version: " + webVersion);
//		System.out.println("Download URL: " + jarURL);
	}
	
	public void deleteOldVersions() {
		File directory= new File(System.getProperty("user.dir"));
		for (File file : directory.listFiles())
		{
			String name = file.getName();
		    if (name.contains("AutoM8") && name.contains("jar") && !name.contains(webVersion)) {
		        try {
			    	System.out.println("Removing Outdated version: " + name);
			    	ProcessBuilder processBuilder = new ProcessBuilder();
			    	processBuilder.directory(new File(System.getProperty("user.dir")));
			        processBuilder.command("cmd.exe", "/c", "del /f \"" + name + "\"");
					processBuilder.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
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
	
	private void UpdateWindow(String downloadURL) {        
        // Download File
		try {
	        System.out.println("Downloading update.");
			URL url = new URL(downloadURL);
	        URLConnection conn = url.openConnection();
	        InputStream is = conn.getInputStream();
	        BufferedOutputStream fOut = null;
	        fOut = new BufferedOutputStream(new FileOutputStream(new File("AutoM8" + webVersion + ".Jar")));
	        byte[] buffer = new byte[32 * 1024];
	        int bytesRead = 0;
	        while ((bytesRead = is.read(buffer)) != -1) {
//	        	System.out.println(bytesRead);
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
    	String[] run = {"java","-jar","AutoM8" + webVersion + ".jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        AutoM8.log.WriteLog("Updated AutoM8 from " + AutoM8.version + " to " + webVersion);
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
