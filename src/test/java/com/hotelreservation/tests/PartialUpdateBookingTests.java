package com.hotelreservation.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTests extends BaseTest {

    @Test
    public void partialUpdateBookingTest(){
        JSONObject body = new JSONObject();
        body.put("firstname","Icardi");
        body.put("lastname","Mertens");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .body(body.toString())
                .when()
                .patch("/booking/" + createBookingId());

        // assertion/testleri yazı
        Assertions.assertEquals("Icardi", response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Mertens", response.jsonPath().getJsonObject("lastname"));
    }
}
