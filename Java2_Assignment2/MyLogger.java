/* class: MyLogger.java*/

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//MyLogger class extending PrintStream class
public class MyLogger extends PrintStream {
	//instance variable to format the timestamp
    private SimpleDateFormat dateFormat;

    public MyLogger(OutputStream out) {
        super(out);
        // Initialize the date format with the desired pattern
        this.dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy 'at' HH:mm:ss:SSS zzz");
        // Set the timezone to EDT (Eastern Daylight Time)
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    }

    // Override print/println methods for each parameter type
    
    @Override
    public void println(int x) {
        printWithTimestamp("printing an integer", String.valueOf(x));
    }

    @Override
    public void println(boolean x) {
        printWithTimestamp("printing a boolean", String.valueOf(x));
    }

    @Override
    public void println(char x) {
        printWithTimestamp("printing a character", String.valueOf(x));
    }

    @Override
    public void println(char[] x) {
        printWithTimestamp("printing an array of characters", String.valueOf(x));
    }

    @Override
    public void println(double x) {
        printWithTimestamp("printing a double", String.valueOf(x));
    }

    @Override
    public void println(float x) {
        printWithTimestamp("printing a float", String.valueOf(x));
    }

    @Override
    public void println(long x) {
        printWithTimestamp("printing a long", String.valueOf(x));
    }

    @Override
    public void println(Object x) {
        printWithTimestamp("printing a String", String.valueOf(x));
    }

    @Override
    public void println(String x) {
        printWithTimestamp("printing a String", x);
    }

    // Helper method to print with timestamp
    private void printWithTimestamp(String type, String output) {
        // Get the current timestamp
        String timestamp = dateFormat.format(new Date());
        //log message constructed with the timestamp, type, and output
        String log = String.format("%s: %s: %s%n", timestamp, type, output);
        //superclass method to print log
        super.print(log);
    }
}
