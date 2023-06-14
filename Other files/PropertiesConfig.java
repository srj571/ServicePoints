import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropertiesConfig {
	private static final Logger logger = Logger.getLogger(LogFileConfigExample.class);

	public static void main(String[] args) {
		// Load the log4j properties file
		PropertyConfigurator.configure(System.getProperty("log4j2.properties"));

		// Get the current date
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(currentDate);

		// Replace the placeholder in the log file path with the formatted date
		String logFilePath = System.getProperty("logs");
		String datedLogFilePath = logFilePath + "/Mylogs" + formattedDate + ".log";
		System.setProperty("filename", datedLogFilePath);

	}
}
