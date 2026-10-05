package utilities;

import base.DriverFactory;
import constants.ConstantClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    static WebDriver driver;
    static String screenshotPath = System.getProperty("user.dir") + ConstantClass.screenshotPath;

    public static String takeScreenshot(String screenshotName) throws IOException {
         driver = DriverFactory.getDriver();
         TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
         File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
         String destFile = screenshotPath + screenshotName + ".png";
         File destfile = new File(destFile);
         FileUtils.copyFile(srcFile,destfile);
         return destFile;
    }

}
