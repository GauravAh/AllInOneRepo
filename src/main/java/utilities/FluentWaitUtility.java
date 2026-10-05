package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class FluentWaitUtility {

    private FluentWait<WebDriver> fluentWait;

    public FluentWaitUtility(WebDriver driver){
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    public void returnDashboardTxt(By dashboardText){
         fluentWait.until(ExpectedConditions.visibilityOfElementLocated(dashboardText));
    }

}
