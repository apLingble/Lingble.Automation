package framework.core.helpers;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Component
public class commonHelper {

    public static String getCurrentFilename(){
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
    }
    public static String generateTime(String pattern) {
        String result = "";
        result = DateTimeFormatter.ofPattern(pattern).format(ZonedDateTime.now());
        return result;
    }
    public static String getPastDateString(int x) {
        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        return dateFormat.format(yesterday(x));
    }
    public static String getDateString(int x,String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, x);
        return dateFormat.format(cal.getTime());
    }
    private static Date yesterday(int x) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -x);
        return cal.getTime();
    }
    protected void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
