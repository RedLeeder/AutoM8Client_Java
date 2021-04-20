package src;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;

import javafx.application.Platform;

@SuppressWarnings("unused")
public class AutoM8 {
	public static Robot bot;
	public static int pause;
	public static boolean init;
	public static MouseListener ML;
	public static KeyListener KL;
	public static TypeConverter TC;
	public static M8Logger log;
	public static SmartRecord SR;
	public static Updater updt;
	
	public static String version = "v0.1";
	
	public static void main(String[] args) {
		
		// Get Version
		try {
			File vfile = new File("AutoM8Files/Version.conf");
			FileInputStream vfis;
			vfis = new FileInputStream(vfile);
			byte[] vdata = new byte[(int) vfile.length()];
			vfis.read(vdata);
			vfis.close();
			version = new String(vdata);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		updt = new Updater();
		// Check for Updated Version
		try {
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
        String libFile = System.getProperty("os.arch").equals("amd64") ? "jacob-1.18-x64.dll" : "jacob-1.18-x86.dll";
        File temporaryDll = null;
        try {
            /* Read DLL file*/
            //InputStream inputStream = VBAManager.class.getResourceAsStream(libFile);
        	InputStream inputStream = new FileInputStream("AutoM8Files/lib/" + libFile);
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
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
		
		log = new M8Logger();	
		
		try {
			GlobalScreen.registerNativeHook();
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
//		init = true;
//		System.out.println("yo");
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
		System.out.println("Exiting");
		System.exit(0);
	}

}
