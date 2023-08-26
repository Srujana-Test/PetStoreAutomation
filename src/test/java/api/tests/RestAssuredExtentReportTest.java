package api.tests;

import api.utilities.ExtentReportManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentReportManager.class) // Apply ExtentReportManager as a TestNG listener
public class RestAssuredExtentReportTest {

    @Test(description = "Testing a GET request")
    public void testGetRequest() {
        // Rest Assured test logic
    }

    @Test(description = "Testing another request")
    public void testAnotherRequest() {
        // Rest Assured test logic
    }
}
