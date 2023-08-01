import constants.Endpoints;
import constants.FilePaths;
import helpers.ApiHelper;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.request.CreateUser;
import pojo.response.CsvReader;
import validators.CreateUserResponseValidator;

import java.util.List;

/**
 * @author Pratikshit Bansal
 */
public class CreateUserTest {

    @Test (dataProvider = "createUserDataProvider")
    public static void createUserTestMethod(String testDescription, CreateUser createUser) {
//        CreateUser createUser=getCreateUser("Nickel","Cartoonist");
        Response response= ApiHelper.executePostApi(Endpoints.CREATE_USER,createUser,FilePaths.CREATE_USER_JSON_SCHEMA_FILE);
        System.out.println(response.getBody().asString());

        CreateUserResponseValidator.createUserResponseValidator(response,createUser);
    }

    public static CreateUser getCreateUser(String name, String job){
        return CreateUser.builder().name(name).job(job).build();
    }

    @DataProvider(name = "createUserDataProvider")
    public Object[][] getDataProvider(){
        List<List<String>> cells= CsvReader.getCellValues(FilePaths.CREATE_USER_DATA_FILE);
        Object[][] result=new Object[cells.size()][];
        int i=0;
        for(List<String> row:cells){
            result[i]=new Object[]{"test description",CreateUser.builder().name(CsvReader.getCellValue(row,0)).job(CsvReader.getCellValue(row,1)).build()};
            i++;
        }
        return result;
    }
}
