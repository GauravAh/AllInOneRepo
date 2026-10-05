package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.ConstantClass;
import org.testng.annotations.Test;

public class ExtentManager {

    static ExtentReports reports;

    public static ExtentReports getInstance(){
        String repPath = System.getProperty("user.dir") + ConstantClass.reportPath + "aa.html";
        if(reports == null){
            ExtentSparkReporter reporter = new ExtentSparkReporter(repPath);
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setReportName("Automation Report");
            reports = new ExtentReports();
            reports.setSystemInfo("Browser","Chrome");
            reports.setSystemInfo("Operating System", "Windows");
            reports.attachReporter(reporter);
        }
        return reports;
    }
}
