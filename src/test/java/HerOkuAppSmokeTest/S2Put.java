package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import util.ObjectMapperUtils;

import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static util.AuthenticationHerOkuApp.generateToken;

public class S2Put extends HerOkuAppBaseUrl {
    /*
    Given
       1) https://restful-booker.herokuapp.com/booking/{id}
       2) {
            "firstname" : "Ali",
            "lastname" : "Can",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        Send Put request
    Then
        Status code should be 200
    And
          {
            "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }
     */

    @Test
    public void put01() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).put("/{first}/{second}");

        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }
}

