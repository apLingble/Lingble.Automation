package framework.listener;

import framework.environment.BaseTestCase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Please do not modify
 * @author @author alexander.v.pangilinan
 */
public class ScreenshotListener extends TestListenerAdapter { 
	private int m_count = 0;
	private Calendar calendar = Calendar.getInstance();
	private SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	private SimpleDateFormat scformat = new SimpleDateFormat("_MM_dd_yyyy");
	private String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
			+ "/target/surefire-reports/failed_steps_screenshots/failed_steps_screenshots_of"+scformat.format(calendar.getTime());

	@Override
	public void onTestFailure(ITestResult tr) {
		log(tr.getName() + "--Test method failed\n");
		WebDriver driver = BaseTestCase.getDriver();
		String methodName = tr.getName();
		Path directory = Paths.get(reportDirectory);
		if (!tr.isSuccess()) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				File destFile = new File((String) reportDirectory + "/" + methodName + "_"+ formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(scrFile, destFile);
				String filePath = destFile.toString();
				String path = "<img src=\""+filePath+"\" height='800' width='1200' alt=\"\"\"/\" / >";
				Reporter.log(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log(tr.getName() + "--Test method skipped\n");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log(tr.getName() + "--Test method success\n");
		Path directory = Paths.get(reportDirectory);
	}

	private void log(String string) {
		System.out.print(string);
		if (++m_count % 40 == 0) {
			System.out.println("");
		}
	}

	public static void deleteFolderAndItsContent(final Path folder) throws IOException {
		Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				if (exc != null) {
					throw exc;
				}
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

}
