package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.ConstantClass;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomReport implements IReporter {

    private static ExtentReports reports;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        createExtentReport();

        for(ISuite suite : suites){
            Map<String, ISuiteResult> results = suite.getResults();
            for(ISuiteResult suiteResult : results.values()){
                ITestContext iTestContext = suiteResult.getTestContext();
                try {
                    buildTestNodes(iTestContext);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        reports.flush();
    }

    private void buildTestNodes(ITestContext context) throws IOException {
        ExtentTest suiteTest = reports.createTest(context.getName());
        buildPassedNodes(suiteTest, context.getPassedTests().getAllResults());
        buildFailedNodes(suiteTest, context.getFailedTests().getAllResults());
    }

    private void buildPassedNodes(ExtentTest suiteTest, Collection<ITestResult> results){
        for (ITestResult result : results) {
            String scenarioName = getScenarioName(result);
            ExtentTest scenarioTest = suiteTest.createNode(scenarioName);
            List<String> logs = (List<String>) result.getAttribute("logs");
            if (logs != null) {
                for (String log : logs) {
                    scenarioTest.info(log);
                }
            }
            scenarioTest.pass("Scenario Passed");
        }
    }

    private void buildFailedNodes(ExtentTest suiteTest, Collection<ITestResult> results) throws IOException {
        for (ITestResult result : results) {
            if(result.getStatus() == ITestResult.FAILURE){
                String scenarioName = getScenarioName(result);
                ExtentTest scenarioTest = suiteTest.createNode(scenarioName);
                List<String> logs = (List<String>) result.getAttribute("logs");
                if (logs != null) {
                    for (String log : logs) {
                        scenarioTest.info(log);
                    }
                }
                String screenshotImage = (String) result.getAttribute("screenshotPath");
                if(screenshotImage!=null){
                    scenarioTest.addScreenCaptureFromPath(screenshotImage);
                }
                scenarioTest.fail("Scenario Failed");
            }
        }
    }

    private String getScenarioName(ITestResult result) {

        Object scenarioName = result.getAttribute("scenarioName");
        if (scenarioName != null) {
            return scenarioName.toString();
        }
        return result.getName();
    }

    private void createExtentReport(){
        String reportPath = System.getProperty("user.dir") + ConstantClass.reportPath + "aa.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Automation Report");
        reporter.config().setTheme(Theme.DARK);
        reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Browser", "Chrome");
        reports.setSystemInfo("Project", "HRM Automation");
        reports.setSystemInfo("Framework", "Cucumber + TestNG");
        reports.setSystemInfo("Environment", "QA");
    }

    public static void info(String message){
        ITestResult result = Reporter.getCurrentTestResult();
        List<String> logs  = (List<String>) Reporter.getCurrentTestResult().getAttribute("logs");
        if (logs  == null) {
            logs  = new ArrayList<>();
            result.setAttribute("logs", logs);
        }
        logs.add(message);
        Reporter.log(message,true);
    }

}
