package com.terasofty.legiz.cucumber.steps;

import com.terasofty.legiz.api.domain.models.LegalService;
import com.terasofty.legiz.api.domain.service.LegalServicesService;
import com.terasofty.legiz.api.forms.LegalServiceForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class ClientMakeCustomCase {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private LegalServicesService legalServicesService;

    private String BASE_URL;
    private static Response response;
    private static String token;

    private static String jsonString;
    private static String bookId;

    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String url) {
        BASE_URL = url;
    }

    @Given("I am an Authorized User")
    public void iAmAnAuthorizedUser() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body("{ \"username\":\"" + "hyper" + "\", \"password\":\"" + "12345" + "\"}")
                .post("/api/login");
        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("access_token");
    }
    @When("A Custom Case Request is sent with values {string} {string} {string} {string}")
    public void aCustomCaseRequestIsSentWithValues(String client, String lawyer, String type, String description) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", token);
        response = request.body("{ \"clientUsername\":\"" + client + "\"," +
                                    " \"lawyerUsername\":\"" + lawyer + "\"," +
                                    " \"type\":\"" + type + "\"," +
                                    " \"description\":\"" + description + "\"}")
                .post("/api/cases");
    }

    @Then("A Response with {int} is received")
    public void aResponseWithIsReceived(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("A Custom Case Resource is created")
    public void aCustomCaseResourceIsCreated() {
        String jsonString = response.asString();
        Integer id = JsonPath.from(jsonString).get("id");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", token);

        response = request.get("/api/cases/" + id.toString());
        Assert.assertEquals(200, response.getStatusCode());
    }
}
