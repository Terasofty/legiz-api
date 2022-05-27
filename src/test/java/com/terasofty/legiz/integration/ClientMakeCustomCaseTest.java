package com.terasofty.legiz.integration;

import com.terasofty.legiz.api.domain.enumerable.LegalServiceStatus;
import com.terasofty.legiz.api.domain.models.*;
import com.terasofty.legiz.api.domain.persistence.CustomCaseRepository;
import com.terasofty.legiz.api.domain.persistence.LegalServiceRepository;
import com.terasofty.legiz.api.domain.service.ClientsService;
import com.terasofty.legiz.api.domain.service.LegalServicesService;
import com.terasofty.legiz.api.domain.service.implementation.LegalServicesServiceImpl;
import com.terasofty.legiz.api.forms.LegalServiceForm;
import com.terasofty.legiz.helper.AuthorizeUserHelper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

class ClientMakeCustomCaseTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    private static RequestSpecification request;
    private static Response response;
    private static String token;
    private LegalServiceForm payload;
    private LegalService service;
    @BeforeEach
    void setUp() {
        String BASE_URL = "http://localhost:8080";
        token = AuthorizeUserHelper.authorizeUser("hyper", "12345");
        payload = new LegalServiceForm("hyper","john", "custom", "Представительство");
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
