package src;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;

import java.io.Console;

@SuppressWarnings("unused")
public class AutoM8 {
	public static Robot bot;
	public static WSClient ws;
	public static Client client;
	public static int pause;
	public static boolean init;
	public static MouseListener ML;
	public static KeyListener KL;
	public static TypeConverter TC;
	public static M8Logger log;
	public static SmartRecord SR;
	public static Updater updt;
	public static Console cnsl;
	public static WelcomeWindow ww;
	
	public static String version = "v0.1.2";
	
	public static void main(String[] args) {
		cnsl = System.console();
		client = new Client();
		try {
			ws = new WSClient(new URI("ws://autom8.cloud:3132"));
//			ws = new WSClient(new URI("ws://localhost:8080"));
			ws.connect();
		} catch (URISyntaxException e2) {
			e2.printStackTrace();
			System.err.println("Error connecting to AutoM8 Server: " + e2);
		}
		log = new M8Logger();
		updt = new Updater();
		// Check for Updated Version
		try {
			updt.deleteOldVersions();
			updt.download();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		init = false;
		try {
			bot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Load .dlls
		/**
         * `System.getProperty("os.arch")`
         * It'll tell us on which platform Java Program is executing. Based on that we'll load respective DLL file.
         * Placed under same folder of program file(.java/.class).
         */
        String libFile = System.getProperty("os.arch").equals("amd64") ? "jacob-1.20-x64.dll" : "jacob-1.20-x86.dll";
        File temporaryDll = null;
        try {
            /* Read DLL file*/
            InputStream inputStream = AutoM8.class.getResourceAsStream(libFile);
//        	InputStream inputStream = new FileInputStream("AutoM8Files/lib/" + libFile);
            /**
             *  Step 1: Create temporary file under <%user.home%>\AppData\Local\Temp\jacob.dll 
             *  Step 2: Write contents of `inputStream` to that temporary file.
             */
            temporaryDll = File.createTempFile("jacob", ".dll");
            FileOutputStream outputStream = new FileOutputStream(temporaryDll);
            byte[] array = new byte[8192];
            for (int i = inputStream.read(array); i != -1; i = inputStream.read(array)) {
                outputStream.write(array, 0, i);
            }
            outputStream.close();
            /**
             * `System.setProperty(LibraryLoader.JACOB_DLL_PATH, temporaryDll.getAbsolutePath());`
             * Set System property same like setting java home path in system.
             * 
             * `LibraryLoader.loadJacobLibrary();`
             * Load JACOB library in current System.
             */
            System.setProperty(LibraryLoader.JACOB_DLL_PATH, temporaryDll.getAbsolutePath());
            LibraryLoader.loadJacobLibrary();
            inputStream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
            System.exit(1);
	    }
		
		try {
            GlobalScreen.registerNativeHook();
            // Clear previous logging configurations.
            LogManager.getLogManager().reset();

            // Get the logger for "org.jnativehook" and set the level to off.
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
            
            // Clear Screen
            try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }


		// Construct the example object.
		ML = new MouseListener();
		KL = new KeyListener();
		TC = new TypeConverter();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(ML);
		GlobalScreen.addNativeMouseMotionListener(ML);
		GlobalScreen.addNativeKeyListener(KL);

		pause = 80;
		
		while (client.LinkCode.length() < 4) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		init = true;
		ww = new WelcomeWindow();
		ww.welcome();
		
		Thread alive = new Thread() {
		    public void run() {
		        try {
		        	int i = 1;
		            while(true) {
		            	ws.status("alive", Integer.toString(i++));
			            Thread.sleep(3000);
		            }
		        } catch(InterruptedException v) {
		            System.out.println(v);
		        }
		    }  
		};
		alive.start();
		
//		pList = new ProcessList();
//		try {
//			ProcessManager.readFromFile();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//ProcessManager.Load();
//		
//		SR = new SmartRecord();
//		
//		
//		try {
//			ProcessManager.writeToFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/* Temporary file will be removed after terminating-closing-ending the application-program */
		temporaryDll.deleteOnExit();
		//ProcessManager.Close();
		log.CloseLog();
//		System.out.println("Exiting");
//		System.exit(0);
	}

}
