package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import utilities.FluentWaitUtility;

public class DashboardPage {

    WebDriver driver;
    FluentWaitUtility fluentWaitUtility;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        fluentWaitUtility = new FluentWaitUtility(driver);
    }

    By dashboardText = By.cssSelector("span[class='oxd-topbar-header-breadcrumb']>h6[class*='breadcrumb']");

    public String verifyDashboardText(){
        fluentWaitUtility.returnDashboardTxt(dashboardText);
        return driver.findElement(dashboardText).getText();
    }

}
