package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import util.ObjectMapperUtils;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Map extends JsonPlaceHolderBaseUrl {
    /*
       Given
           https://jsonplaceholder.typicode.com/todos/198
       When
            I send GET Request to the URL
        Then
            Status code is 200
            And response body is like {
                                       "userId": 10,
                                       "id": 198,
                                       "title": "quis eius est sint explicabo",
                                       "completed": true
                                     }
    */
    @Test
    public void get14() {
        //Set the URL
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
//        String json = "{\n" +
//                "   \"userId\": 10,\n" +
//                "    \"id\": 198,\n" +                                    //Test classında bunu istemiyoruz
//                "    \"title\": \"quis eius est sint explicabo\",\n" +
//                "    \"completed\": true\n" +
//                "  }";

        String json = JsonPlaceHolderTestData.expectedDataInString(10, "quis eius est sint explicabo", true);

        Map<String, Object> expectedData = ObjectMapperUtils.convertJsonToJava(json, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
