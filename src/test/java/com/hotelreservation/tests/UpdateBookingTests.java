package com.hotelreservation.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest {

    @Test
    public void updateBookingTest() {
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .body(bookingObject("Fatih", "Terim", 500, false))
                .put("/booking/" + createBookingId());


        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Fatih", firstName);
        Assertions.assertEquals("Terim", lastName);
        Assertions.assertEquals(500, totalPrice);
        Assertions.assertEquals(false, response.jsonPath().getJsonObject("depositpaid"));
    }
}
