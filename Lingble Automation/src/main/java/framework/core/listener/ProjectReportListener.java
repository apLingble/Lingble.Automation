package framework.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProjectReportListener implements IReporter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProjectReportListener.class);

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> iSuites, String outputDirectory) {
        for(ISuite iSuite:iSuites){
            Map<String,ISuiteResult> iSuiteResultMap = iSuite.getResults();
            LOGGER.warn("============== Test Suite Result ==============");

            for(ISuiteResult iSuiteResult: iSuiteResultMap.values()){
                ITestContext iTestContext = iSuiteResult.getTestContext();
                Set<ITestResult> iTestResultSetPassed = iTestContext.getPassedTests().getAllResults();
                Set<ITestResult> iTestResultSetFailed = iTestContext.getFailedTests().getAllResults();
                Set<ITestResult> iTestResultSetSkipped = iTestContext.getSkippedTests().getAllResults();

                int total = (iTestResultSetPassed.size() + iTestResultSetFailed.size() + iTestResultSetSkipped.size());
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                String passrate = numberFormat.format((float) iTestResultSetPassed.size() / (float) total * 100);

                LOGGER.warn("Suite name: " + iTestContext.getName());
                LOGGER.warn("Total Cases:   " + total);
                LOGGER.warn("Total Passed:  " + iTestResultSetPassed.size());
                LOGGER.warn("Total Failed:  " + iTestResultSetFailed.size());
                LOGGER.warn("Total Skipped: " + iTestResultSetSkipped.size());
                LOGGER.warn("Pass Rate:     " + passrate + "%");
            }
        }
    }
}