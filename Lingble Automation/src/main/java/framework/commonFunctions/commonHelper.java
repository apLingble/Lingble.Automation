package framework.commonFunctions;

import framework.environment.BaseTestCase;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class commonHelper {
    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
    private static SimpleDateFormat scformat = new SimpleDateFormat("_MM_dd_yyyy_hh_mm");
    protected static String reportDirectory;

    public static void takeScreenshot(Scenario scenario) {
        reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
                + "/target/surefire-reports/steps_screenshots/" + scenario.getName() + "_" + scformat.format(calendar.getTime());
        WebDriver driver = BaseTestCase.getDriver();
        Path directory = Paths.get(reportDirectory);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File destFile = new File((String) reportDirectory + "/"+scenario.getName()+""+ formater.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, destFile);
            String filePath = destFile.toString();
            String path = "<img src=\"" + filePath + "\" height='800' width='1200' alt=\"\"\"/\" / >";
            Reporter.log(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getCurrentFilename() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
    }

    public static String generateTime(String pattern) {
        String result = "";
        result = DateTimeFormatter.ofPattern(pattern).format(ZonedDateTime.now());
        return result;
    }

    public static String getPastDate(int x) {
        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        return dateFormat.format(yesterday(x));
    }

    public static String getFutureDate(int x, String pattern) {
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
