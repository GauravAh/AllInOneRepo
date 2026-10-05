package hooks;

import base.BaseClass;
import base.DriverFactory;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import reporting.CustomReport;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utilities.ConfigUtility;
import utilities.ScreenshotUtil;

import java.io.IOException;

public class HookClass {

    WebDriver driver;

    @Before
    public void setUp(Scenario scenario){
        driver = DriverFactory.initializeDriver(ConfigUtility.getProp("browser"));
        driver.get(BaseClass.initializeApplication());
        String url = "Url - " + "opensource-demo.orangehrmlive.com";
      // ExtentTestManager.startTest(scenario.getName());
        String scenarioName = scenario.getName();
        Reporter.getCurrentTestResult().setAttribute("scenarioName", scenarioName);
        CustomReport.info(url);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        ITestResult result = Reporter.getCurrentTestResult();
        String scenarioName = scenario.getName();
        Reporter.getCurrentTestResult().setAttribute("scenarioName", scenarioName);
        if(scenario.isFailed()){
            /*ExtentTestManager.getTest().fail("Scenario Failed").
                    addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(scenario.getName()));*/
            String screenshotPath = ScreenshotUtil.takeScreenshot(scenario.getName());
            result.setAttribute("screenshotPath", screenshotPath);
        }

    }

}
