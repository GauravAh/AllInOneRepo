package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    static ThreadLocal<WebDriver> lDriver = new ThreadLocal<>();

    @Parameters("browser")
    public static WebDriver initializeDriver(String browserName){
        if(browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeDriver chromeDriver = new ChromeDriver(getChromeOptions());
            lDriver.set(chromeDriver);
        }
        return getDriver();
    }

    public static WebDriver getDriver(){
        return lDriver.get();
    }

    private static ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--incognito");
        return options;

    }

}

