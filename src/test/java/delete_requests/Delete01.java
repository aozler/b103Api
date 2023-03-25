package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
      /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
         I send DELETE Request to the Url
      Then
         Status code is 200
         And Response body is { }
     */

    @Test
    public void delete01() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        Map<String, String> expectedData = new HashMap<>();

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200, response.statusCode());

        //1. Yol
        assertEquals(expectedData, actualData);

        //2. Yol
        assertTrue(actualData.isEmpty());

        //3. Yol
        assertEquals(0, actualData.size());

    }
}
