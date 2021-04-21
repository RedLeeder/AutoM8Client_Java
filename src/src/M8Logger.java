package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class M8Logger{
	File logfile;
	File errlogfile;
	PrintWriter writer;
	private boolean wrote;
	public static M8PrintStream ps;
	
	public M8Logger() {
		wrote = false;
		String time = new SimpleDateFormat("ddmmyyHHmmss").format(Calendar.getInstance().getTime());
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		
		//Create /Logs directory if not exists
		File logsDir = new File("AutoM8Files/Logs");
		if (!logsDir.exists()) {
			System.out.println("Creating Logs Directory.");
			logsDir.mkdir();
		}
		
		
		try {
			logfile = new File("AutoM8Files/Logs/", "AutoM8Log_" + AutoM8.version + "_" + time + ".log");
			logfile.getParentFile().mkdirs();
			System.out.println("AutoM8Files/Logs/AutoM8Log_" + AutoM8.version + "_" + time + ".log");
			if (logfile.createNewFile()){
				//Uhhh
				}else{
				//Duhhh
			  }
		} catch (IOException e1) {
			StringWriter error = new StringWriter();
			e1.printStackTrace(new PrintWriter(error));
			System.out.println("ERROR: Unable to create file.");
			System.out.println(error.toString());
			e1.printStackTrace();
		}
		
		try {
			writer = new PrintWriter ("AutoM8Files\\Logs\\AutoM8Log_" + AutoM8.version + "_" + time + ".log", "UTF-8");
		} catch (FileNotFoundException e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			WriteLog("ERROR: Log File was not created.");
			WriteLog(error.toString());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			WriteLog("ERROR: Unsupported Log Format.");
			WriteLog(error.toString());
			e.printStackTrace();
		}
		
		FileOutputStream fos;
		try {
			errlogfile = new File("AutoM8Files/Logs/", "AutoM8Log_" + AutoM8.version + "_" + time + "Errors.log");
			fos = new FileOutputStream(errlogfile);
			ps = new M8PrintStream(fos, System.out);
			System.setErr(ps);
		} catch (FileNotFoundException e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			WriteLog("ERROR: Unable to create output stream.");
			WriteLog(error.toString());
			e.printStackTrace();
		}
		
		// Log Header
		writer.println("AutoM8 Log");
		writer.println("AutoM8 Version: " + AutoM8.version);
		writer.println("Begin Timestamp: " + timeStamp);
		writer.println("==================================================================================");
	}
	
	public void WriteLog(String text) {
		if (text == null) {
		} else {
			wrote = true;
			writer.println(text);
			writer.flush();
			ps.flush();
		}
	}
	
	public void CloseLog() {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
       // String display = String.format("%02d:%02d:%02d", (elapsed / (1000 * 60 * 60)) % 24, (elapsed / (1000 * 60)) % 60, (elapsed / 1000) % 60);
        
		// Log Footer
		writer.println("==================================================================================");
		writer.println("End Timestamp: " + timeStamp);
		//writer.println("Runtime: " + display);
		writer.close();
		if (!wrote) {
			try {
				Files.deleteIfExists(logfile.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ps.flush();
		ps.close();
		if (!M8PrintStream.wrote) {
			try {
				Files.deleteIfExists(errlogfile.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}	
}
