package reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void startTest(String scenarioName){
        ExtentTest test = ExtentManager.getInstance().createTest(scenarioName);
        extentTest.set(test);
    }

    public static ExtentTest getTest(){
        return extentTest.get();
    }

}
