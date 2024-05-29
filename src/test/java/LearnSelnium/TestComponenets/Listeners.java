package LearnSelnium.TestComponenets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import LearnSelenium.resources.ExtendReporterNG;

public class Listeners extends BaseTest implements ITestListener {// extends BaseTest -reson screenshot code present in
																	// BaseTest
	ExtentTest test;
	ExtentReports externobject = ExtendReporterNG.getReportObjcet();// ExternReports
																	// ecternreportobject=classname.methodname;
	ThreadLocal<ExtentTest> threadobject = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {// result->which holds all the information about the test

		// TODO Auto-generated method stub
		test = externobject.createTest(result.getMethod().getMethodName());
		threadobject.set(test);// It set unique thread->test->failed case

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadobject.get().log(Status.PASS, "The Test is Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// test.log(Status.FAIL, "The Test is Passed");
		// test.fail(result.getThrowable());//Print the error message of report
		threadobject.get().fail(result.getThrowable());// Print the error message of report
		// take screenshot + Attach to the report

		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		threadobject.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		externobject.flush();// end of the test case we need write flush method in order to create report.

	}

}
