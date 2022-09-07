package framework.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ProjectProcessListener extends TestListenerAdapter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProjectProcessListener.class);
    private static final String HIGHLIGHT = "===============";

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.warn(HIGHLIGHT + " TEST CASE [" +
                iTestResult.getParameters()[0] +
                "] START (" + iTestResult.getTestClass().getName() + " " + iTestResult.getName() + ") " + HIGHLIGHT );
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.warn(HIGHLIGHT + " TEST CASE [" +
                iTestResult.getParameters()[0] +
                "] PASSED (" + iTestResult.getTestClass().getName() + " " + iTestResult.getName() + ") " + HIGHLIGHT );
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.warn(HIGHLIGHT + " TEST CASE [" +
                iTestResult.getParameters()[0] +
                "] FAILED (" + iTestResult.getTestClass().getName() + " " + iTestResult.getName() + ") " + HIGHLIGHT );
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.warn(HIGHLIGHT + " TEST CASE [" +
                iTestResult.getParameters()[0] +
                "] SKIPPED (" + iTestResult.getTestClass().getName() + " " + iTestResult.getName() + ") " + HIGHLIGHT );
    }

}