package framework.listener;

import framework.commonFunctions.CSVReadWrite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Please do not modify
 * @author alexander.v.pangilinan
 */

public class TestCaseReporter extends TestListenerAdapter{
	 private int m_count = 0;
	 private String summary;
	 private String module;
	 private CSVReadWrite csvReadWrite;
	 private ITestContext iTestContext;

	 @Override
		public void onTestStart(ITestResult tr) {
	 	super.onTestStart(tr);
		 csvReadWrite = new CSVReadWrite();
		 Object[] params = tr.getParameters();
		 summary = (String)params[1];
		 module = tr.getTestContext().getCurrentXmlTest().getParameter("module");
		 iTestContext = tr.getTestContext();

		}
	 	@Override
		public void onTestFailure(ITestResult tr) {
			try {
				csvReadWrite.updateTestData(module, "Status", summary, "Failed");
			}catch (Exception e){

			}
		}

		@Override
		public void onTestSkipped(ITestResult tr) {
			  log(tr.getName()+ "--Test method skipped\n");
		}
		@Override
		public void onTestSuccess(ITestResult tr) {
	 		try{
				csvReadWrite.updateTestData(module , "Status", summary, "Passed");
			}catch(Exception e){

			}
		   }

		   private void log(String string) {
			  System.out.print(string);
			  if (++m_count % 40 == 0) {
				 System.out.println("");
			  }
		   }
}
