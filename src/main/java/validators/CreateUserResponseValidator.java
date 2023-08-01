package validators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.request.CreateUser;
import pojo.response.CreateUserResponse;

/**
 * @author Pratikshit Bansal
 */
public class CreateUserResponseValidator {
    public static void createUserResponseValidator(Response response, CreateUser request){
        try {
            CreateUserResponse createUserResponse= new ObjectMapper().readValue(response.getBody().asString(),CreateUserResponse.class);
            Assert.assertTrue(createUserResponse.getName().equals(request.getName()));
            Assert.assertTrue(createUserResponse.getJob().equals(request.getJob()));
            Assert.assertTrue(!createUserResponse.getId().isEmpty());
            Assert.assertTrue(!createUserResponse.getCreatedAt().isEmpty());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
