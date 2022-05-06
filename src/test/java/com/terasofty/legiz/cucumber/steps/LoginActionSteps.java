package com.terasofty.legiz.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginActionSteps {
    public static WebDriver driver;
    @Given("User is on Home Page")
    public void userIsOnHomePage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:3000");
    }

    @When("User Navigate to LogIn Page")
    public void userNavigateToLogInPage() {
        driver.findElement(By.id("login")).click();
    }

    @And("User enters {string} and {string}")
    public void userEntersAnd(String arg0, String arg1) {
        driver.findElement(By.id("username_field")).sendKeys("hyper");
        driver.findElement(By.id("password_field")).sendKeys("12345");
        driver.findElement(By.id("submit")).click();
    }

    @Then("Message displayed Login Successfully")
    public void messageDisplayedLoginSuccessfully() {
        driver.getPageSource().contains("Login successful.");
    }
}
