import constants.Endpoints;
import helpers.ApiHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * @author Pratikshit Bansal
 */
public class GetUserTest {

    @Test
    public static void getUserTestMethod() {
        Response response= ApiHelper.executeGetApi(Endpoints.GET_USER);

        System.out.println(response.getBody().asString());


    }
}
