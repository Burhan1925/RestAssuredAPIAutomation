package com.hotelreservation.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests extends BaseTest {
    // curl -i https://restful-booker.herokuapp.com/booking

    @Test
    public void getAllBookingTest(){
        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }

    @Test
    public void getBookingsWithFirstnameFilterTest(){
        // yeni rezervasyon oluşturmak
        int bookingId = createBookingId();

        // çağrıya query parametresi eklemek
        spec.queryParam("firstname","Burhan");
        spec.queryParam("lastname","Aydın");

        // çağrıyı gerçekleştirmek
        Response response = given(spec)
                .when()
                .get("/booking");

        // Assertion / test yaz
        response
                .then()
                .statusCode(200);

        List<Integer> filterReservation = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filterReservation.contains(bookingId));
    }


}
