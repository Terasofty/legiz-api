package com.terasofty.legiz.integration;

import com.terasofty.legiz.api.domain.models.*;
import com.terasofty.legiz.api.forms.LegalServiceForm;
import com.terasofty.legiz.helper.AuthorizeUserHelper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

class ClientMakeCustomCaseTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    private static RequestSpecification request;
    private static Response response;
    private static String token;
    private LegalServiceForm payload;
    @BeforeEach
    void setUp() {
        String BASE_URL = "http://localhost:8080";
        token = AuthorizeUserHelper.authorizeUser("hyper", "12345");
        payload = new LegalServiceForm("help","hyper","john", "custom", "Представительство");
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", token);
    }

    @Test
    void makeCustomCase() {
        response = request.body(payload.toJson()).post("/api/cases");
        Assertions.assertEquals(201, response.getStatusCode());
    }

    @Test
    void getCustomCase() {
        response = request.body(payload.toJson()).post("/api/cases");
        String caseId = JsonPath.from(response.asString()).getString("id");
        String caseDescription = JsonPath.from(response.asString()).get("customCase.description");
        response = request.get("/api/cases/" + caseId);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(caseDescription, "Представительство");
    }
}
