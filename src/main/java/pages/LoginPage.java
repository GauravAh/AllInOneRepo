package pages;

import base.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.FluentWaitUtility;
import utilities.WaitUtility;

public class LoginPage {

    static WebDriver driver;
    WaitUtility waitUtility;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        waitUtility = new WaitUtility(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[starts-with(@class,'orangehrm-login-logo')]/following-sibling::h5")
    private WebElement loginText;

    @FindBy(xpath = "//div[starts-with(@class,'orangehrm-login-branding')]/img[starts-with(@src,'/web/images/ohrm_branding') and @alt = 'company-branding']")
    private WebElement loginLogo;

    @FindBy(name = "username")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "button[class$='login-button']")
    private WebElement lognBtn;

    public String getExpectedTitle(){
        return driver.getTitle();
    }

    public String getLoginText(){
        waitUtility.waitforElement(loginText);
        return loginText.getText();
    }

    public boolean getLoginLogo(){
         waitUtility.waitforElement(loginLogo);
         return loginLogo.isDisplayed();
    }

    public void sendUserName(String name){
        waitUtility.waitforElement(userName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='"+name+"';", userName);
    }

    public void sendPassword(String pass){
        waitUtility.waitforElement(password);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='"+pass+"';", password);
    }

    public void clickLoginBtn(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",lognBtn);
    }


}
