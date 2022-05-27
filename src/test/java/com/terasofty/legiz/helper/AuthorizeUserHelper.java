package com.terasofty.legiz.helper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizeUserHelper {
    public static String authorizeUser(String username, String password) {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.body("{ \"username\":\"" + "hyper" + "\", \"password\":\"" + "12345" + "\"}")
                .post("/api/login");
        String jsonString = response.asString();
        return JsonPath.from(jsonString).get("access_token");
    }
}
