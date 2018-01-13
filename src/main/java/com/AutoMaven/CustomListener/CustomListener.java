package com.AutoMaven.CustomListener;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.AutoMaven.Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListener extends TestBase implements ITestListener {
	private static final Logger log = Logger.getLogger(CustomListener.class.getName());
	private static String extentReportPath, extentReportName;

	// initialize, set path and name for extent reports
	static {
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		// extent object is defined in TestBase.java class
		extentReportName = "Report_" + formater.format(calender.getTime());
		extentReportPath=System.getProperty("user.dir") + "/reports/"+extentReportName;
//		use below for windows
//		extentReportPath=System.getProperty("user.dir") + "\\reports\\" + "Report_" + formater.format(calender.getTime());
		extent = new com.relevantcodes.extentreports.ExtentReports(extentReportPath + ".html", false);
	}

	/***
	 * take screenshot
	 * 
	 * @param screenshotFileName
	 *            name of the screenshot file
	 */
	private void takeScreenshot(String screenshotFileName) {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();
		// Directory where to copy screenshot
		String reportDirectory = System.getProperty("user.dir") + "/screenshots/";
		//use below for windows
//		String reportDirectory = System.getProperty("user.dir") + "\\screenshots\\";
		// custom name of screenshot Based on Test Method Name and Current time
		String fileName = screenshotFileName + "_" + dateFormat.format(date) + ".jpg";

		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
		File outputFile = new File(reportDirectory + fileName);

		try {
			FileUtils.copyFile(screenshotFile, outputFile);
			log.info("Screenshot [" + fileName + "] has been copied to [" + reportDirectory + "]");
		} catch (IOException e) {
			log.error("Got Exception while taking Screenshot");
			e.printStackTrace();
		}
	}

	/***
	 * get the result after test execution, so it can be reported in Extent
	 * Reports
	 * 
	 * @param result
	 */
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test [" + result.getName() + "] is Passed");
			log.info("Test Case [" + result.getMethod().getMethodName() + "] is [Passed] ");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP,
					"Test [" + result.getName() + "] is Skipped Because of [" + result.getThrowable() + "]");
			log.info("Test Case [" + result.getMethod().getMethodName() + "] is [Skipped] ");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR,
					"Test [" + result.getName() + "] is Failed Because of [" + result.getThrowable() + "]");
			log.info("Test Case [" + result.getMethod().getMethodName() + "] is [Failed] and reason is ["
					+ result.getThrowable() + "]");
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, "Test [" + result.getName() + "] is Started");
			log.info("Test Case [" + result.getMethod().getMethodName() + "] is [Started] ");
		}
	}

	/***
	 * this method is called from ExcelReader Class and it will help to write
	 * Test Results to the Excel Data file
	 * @param SheetName
	 * @param TestcaseName
	 * @param result
	 */
	public void setResultIntoExcelFile(String SheetName, String TestcaseName, String result) {
		excel.excelDataWriter(SheetName, TestcaseName, "Result", result);
	}

	/***
	 * things need to do after executing all the test suites (or testcases) is defined here
	 * it will set the result of testcase after execution to the Excel DataFile
	 */
	public void onFinish(ITestContext arg0) {
		extent.flush();
		log.info("Report has been copied to [" + extentReportPath+".html" + "]");
//		use below for windows
//		log.info("Report has been copied to [" + System.getProperty("user.dir") + "\\reports\\"+extentReportPath+".html" + "]");
		log.info("############################ Automation Test [E-N-D] ###################################");
		log.info("########################################################################################");

	}

	/***
	 * things need to do before executing all the test suites (or testcases) is defined here
	 */
	public void onStart(ITestContext testcase) {
		log.info("########################################################################################");
		log.info("########################## Automation Test is [Running] ################################");
	}

	/***
	 * If testcase is failed but few Steps after successful, this method will be called
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		getResult(result);
		takeScreenshot(result.getMethod().getMethodName());
		log.error("########   [" + result.getMethod().getMethodName() + "] is failed!  #######");
		log.info("==================================================================================");
		log.info("+++++++++++++++++++++ E-N-D [" + result.getMethod().getMethodName() + "] +++++++++++++++++++++");
		log.info("==================================================================================");

	}

	/***
	 * when testcase is failed this method will be called
	 */
	public void onTestFailure(ITestResult result) {
		getResult(result);
		takeScreenshot("FAILED_"+result.getMethod().getMethodName());
		log.error("########   [" + result.getMethod().getMethodName() + "] is failed!  #######");
		log.info("==================================================================================");
		log.info("+++++++++++++++++++++ E-N-D [" + result.getMethod().getMethodName() + "] +++++++++++++++++++++");
		log.info("==================================================================================");

	}

	/***
	 * when testcase is skipped this method will be called
	 */
	public void onTestSkipped(ITestResult result) {
		getResult(result);
		log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.error("########  Testcase [" + result.getMethod().getMethodName() + "] is skipped!  #######");
		log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	/***
	 * this method will be called just before start executing of any testcase
	 */
	public void onTestStart(ITestResult result) {

		log.info("==================================================================================");
		log.info("++++++++++++ Starting Testcase: [" + result.getMethod().getMethodName() + "] ++++++++++++");
		log.info("==================================================================================");
		test = extent.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, "Test [" + result.getMethod().getMethodName() + "] is started");

	}

	/***
	 * this method will be called just after finish executing of any testcase
	 * But only If testcase is Passed
	 */
	public void onTestSuccess(ITestResult result) {
		getResult(result);
		takeScreenshot("PASSED_"+result.getMethod().getMethodName());
		log.info("==================================================================================");
		log.info("+++++++++++++++++++++ E-N-D [" + result.getMethod().getMethodName()
				+ ": PASSED] +++++++++++++++++++++");
		log.info("==================================================================================");

	}

}
