package LearnSelenium.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {

	public static ExtentReports getReportObjcet() {//marking the static ->we want to access without creating the object
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("AutomationTestReport");
		report.config().setDocumentTitle("Test Results");

		ExtentReports externobject = new ExtentReports();
		externobject.attachReporter(report);
		externobject.setSystemInfo("Tester", "naresh.murugan");
		return externobject;

	}

}
