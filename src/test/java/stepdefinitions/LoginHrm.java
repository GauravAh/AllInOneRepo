package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginHrm {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Given("I am on home page")
    public void i_am_on_home_page() {
       driver = DriverFactory.getDriver();
       loginPage = new LoginPage(driver);
       dashboardPage = new DashboardPage(driver);
       String expectedTitle = "OrangeHRM";
       Assert.assertEquals(loginPage.getExpectedTitle(), expectedTitle);
    }
    @When("And i verify the Login text")
    public void and_i_verify_the_login_text() {
        String expectedText = "Login";
        Assert.assertEquals(loginPage.getLoginText(), expectedText);
    }
    @When("I also verify the logo")
    public void i_also_verify_the_logo() {
        Assert.assertTrue(loginPage.getLoginLogo());
    }
    @When("I enter the details")
    public void i_enter_the_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> enterDetail = dataTable.asMaps();
        String usernameValue = enterDetail.get(0).get("username");
        String passValue = enterDetail.get(0).get("password");

        loginPage.sendUserName(usernameValue);
        Thread.sleep(2000);
        loginPage.sendPassword(passValue);
        Thread.sleep(2000);

    }
    @When("I click on continue button")
    public void i_click_on_continue_button() {
       // loginPage.clickLoginBtn();
    }
    @Then("Page should be redirected to the dashboard")
    public void page_should_be_redirected_to_the_dashboard() {
        String expectedText = "Dashboard";
        Assert.assertEquals(dashboardPage.verifyDashboardText(), expectedText);
    }
    @When("I enter the details as {string} and {string}")
    public void i_enter_the_details_as_and(String username, String password) {
        loginPage.sendUserName(username);
        loginPage.sendPassword(password);
    }
}
