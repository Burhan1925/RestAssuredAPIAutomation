package com.hotelreservation.tests;

import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingDates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTest {

    @Test
    public void createBookingTest() {
        Response response = createBooking();

        Assertions.assertEquals("Burhan", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Aydın", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));

    }

    @Test
    public void createBookingWithPojo() {
        BookingDates bookingDates = new BookingDates("2023-01-01", "2023-01-02");
        Booking booking = new Booking("Udemy", "Kurs", 350, true, bookingDates, "Tek kisilik yatak");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);
    }
}