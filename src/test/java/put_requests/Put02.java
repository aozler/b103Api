package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import util.ObjectMapperUtils;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
        /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    @Test
    public void put02() {
        //Set the URL
        spec.pathParams("first", "update", "second", 21);

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        System.out.println("expectedData = " + expectedData);//Update için gönderilecek data
        DummyRestApiResponseBodyPojo expectedBodyPojo = new DummyRestApiResponseBodyPojo("success", expectedData, "Successfully! Record has been updated.");

        //Send the request and get the response
        Response response = given().spec(spec).when().body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        DummyRestApiResponseBodyPojo actualdData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponseBodyPojo.class);
        System.out.println("actualdData = " + actualdData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedBodyPojo.getStatus(), actualdData.getStatus());
        assertEquals(expectedBodyPojo.getMessage(), actualdData.getMessage());

        assertEquals(expectedData.getEmployee_name(), actualdData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualdData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualdData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualdData.getData().getProfile_image());


    }
}
