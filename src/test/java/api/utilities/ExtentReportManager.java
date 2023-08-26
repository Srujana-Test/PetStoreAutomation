package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;
    private ExtentSparkReporter sparkReporter;

    String repName;
   String reportPath = "src/test/resources/reports"; // Define report path
    String sparkReportPath = "src/test/resources/reports"; // Define spark report path


    public void initializeExtentReport(String s, String s1) {
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        sparkReporter = new ExtentSparkReporter(sparkReportPath);
        extent.attachReporter(htmlReporter, sparkReporter);
    }

    public void onStart(ITestContext context) {
        // Called before any test starts executing
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        initializeExtentReport("test-output", "spark-report_" + timestamp);
    }

    public void onTestStart(ITestResult result) {
        // Called when a test method starts executing
        startTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
    }

    public void onTestSuccess(ITestResult result) {
        // Called when a test method succeeds
        logTestResult(true, "Test passed.");
    }

    public void onTestFailure(ITestResult result) {
        // Called when a test method fails
        logTestResult(false, "Test failed.");
    }

    public void onTestSkipped(ITestResult result) {
        // Called when a test method is skipped
        logTestResult(false, "Test skipped.");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Called when a test method fails within a success percentage
        logTestResult(false, "Test failed but within success percentage.");
    }

    public void onFinish(ITestContext context) {
        // Called after all tests have finished executing
        closeExtentReport();
    }

    public void startTest(String testName, String testDescription) {
        test = extent.createTest(testName, testDescription);
    }

    public void logTestResult(boolean isSuccess, String message) {
        if (test != null) {
            if (isSuccess) {
                test.pass(message);
            } else {
                test.fail(message);
            }
        }
    }

    public Response sendGetRequest(String url) {
        return RestAssured.get(url);
    }

    public void closeExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // ... Other methods ...
}
