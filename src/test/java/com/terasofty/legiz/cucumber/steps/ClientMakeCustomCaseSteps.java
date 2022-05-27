package com.terasofty.legiz.cucumber.steps;

import com.terasofty.legiz.api.domain.service.LegalServicesService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;

public class ClientMakeCustomCaseSteps {
// Functional Test
    public static WebDriver driver;
    public String username, password;
    @Given("the client credentials {string} {string}")
    public void theClientCredentials(String username, String password) {
        this.username = username;
        this.password = password;
        driver = new ChromeDriver();
    }

    @Given("the client click login button")
    public void theClientClickLoginButton() {
        driver.get("http://localhost:3000");
        driver.findElement(By.id("login")).click();
    }

    @When("the client fills the login form")
    public void theClientFillsTheLoginForm() throws InterruptedException {
        driver.findElement(By.id("username_field")).sendKeys(username);
        driver.findElement(By.id("password_field")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        sleep(1000);
    }

    @When("the client navigates to Custom Cases page")
    public void theClientNavigatesToCustomCasesPage() throws InterruptedException {
        driver.findElement(By.id("nav-services")).click();
        sleep(1000);
        driver.findElement(By.id("custom-cases")).click();
    }

    String lawyerUsername, description;
    @Given("the following case {string} {string}")
    public void theFollowingCase(String arg0, String arg1) {
        this.lawyerUsername = arg0;
        this.description = arg1;
    }

    @Then("the client clicks on the Add Custom Case button")
    public void theClientClicksOnTheAddCustomCaseButton() throws InterruptedException {
        driver.findElement(By.id("add-case")).click();
        sleep(1000);
        driver.findElement(By.id("username")).sendKeys(lawyerUsername);
        driver.findElement(By.id("description")).sendKeys(description);
        sleep(1000);
        driver.findElement(By.id("submit")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE);
        sleep(1000);
        driver.close();
    }

}
