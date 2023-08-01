package helpers;

import io.restassured.module.jsv.JsonSchemaValidator;
import utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pratikshit Bansal
 */
public class ApiHelper {

    static RequestSpecification requestSpecification;

    public static Response executeApiWithoutJsonSchemaValidation(String method, String endPoint, Object body, Map<String, Object> queryParams, Map<String, String> headerMap, String pathParams) {
        RestAssured.baseURI = ConfigManager.getInstance().getPropertyValue("baseuri");
        Response response = null;

        if (queryParams == null)
            queryParams = new HashMap<>();
        if (headerMap == null)
            headerMap = new HashMap<>();

        requestSpecification = RestAssured.given().log().all().contentType(ContentType.JSON).queryParams(queryParams).headers(headerMap);

        if (pathParams != null)
            requestSpecification.queryParams("pathParam", pathParams);

        switch (method.toUpperCase()) {
            case "GET":
                response = requestSpecification.get(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            case "POST":
                response = requestSpecification.body(body)
                        .post(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            case "PUT":
                response = requestSpecification.body(body)
                        .put(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            case "DELETE":
                response = requestSpecification.delete(endPoint)
                        .then().log().all()
                        .extract().response();
                break;
            default:
                response = null;
                break;
        }
        return response;
    }


    public static Response executeApi(String method, String endPoint, Object body, Map<String, Object> queryParams, Map<String, String> headerMap, String pathParams, String jsonSchemaFilePath) {
        RestAssured.baseURI = ConfigManager.getInstance().getPropertyValue("baseuri");
        Response response = null;

        if (queryParams == null)
            queryParams = new HashMap<>();
        if (headerMap == null)
            headerMap = new HashMap<>();

        requestSpecification = RestAssured.given().log().all().contentType(ContentType.JSON).queryParams(queryParams).headers(headerMap);

        if (pathParams != null)
            requestSpecification.queryParams("pathParam", pathParams);

        switch (method.toUpperCase()) {
            case "GET":
                response = requestSpecification.get(endPoint)
                        .then().log().all()
                        .assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(String.valueOf(jsonSchemaFilePath))))
                        .extract().response();
                break;
            case "POST":
                response = requestSpecification.body(body)
                        .post(endPoint)
                        .then().log().all()
                        .assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(String.valueOf(jsonSchemaFilePath))))
                        .extract().response();
                break;
            case "PUT":
                response = requestSpecification.body(body)
                        .put(endPoint)
                        .then().log().all()
                        .assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(String.valueOf(jsonSchemaFilePath))))
                        .extract().response();
                break;
            case "DELETE":
                response = requestSpecification.delete(endPoint)
                        .then().log().all()
                        .assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(String.valueOf(jsonSchemaFilePath))))
                        .extract().response();
                break;
            default:
                response = null;
                break;
        }
        return response;
    }



    public static Response executeGetApi(String endpoint) {
        return executeApiWithoutJsonSchemaValidation("get", endpoint, null, null, null, null);
    }
    public static Response executePostApi(String endPoint, Object body) {
        return executeApiWithoutJsonSchemaValidation("post", endPoint, body, null, null, null);
    }



    public static Response executePostApi(String endPoint, Object body, String jsonschema) {
        return executeApi("post", endPoint, body, null, null, null, jsonschema);
    }
    public static Response executeGetApi(String endpoint, String jsonschema) {
        return executeApi("get", endpoint, null, null, null, null, jsonschema);
//        return executeGetApiWithAllOptions(endpoint, null, null, null);
    }




    public static Response executePostApiWithAllOptions(String endPoint, Object body, Map<String, Object> queryParams, Map<String, String> headerMap, String pathParams) {
        RestAssured.baseURI = ConfigManager.getInstance().getPropertyValue("baseuri");

        if (queryParams == null)
            queryParams = new HashMap<>();
        if (headerMap == null)
            headerMap = new HashMap<>();
        requestSpecification = RestAssured.given().log().all().contentType(ContentType.JSON);
        if (pathParams != null)
            requestSpecification.queryParams("pathParam", pathParams);

        return requestSpecification.queryParams(queryParams)
                .headers(headerMap)
                .body(body)
                .post(endPoint)
                .then().log().all()
                .extract().response();

    }

    public static Response executeGetApiWithAllOptions(String endPoint, Map<String, Object> queryParams, Map<String, String> headersMap, String pathParams) {
        RestAssured.baseURI = ConfigManager.getInstance().getPropertyValue("baseuri");
        if (queryParams == null)
            queryParams = new HashMap<>();
        if (headersMap == null)
            headersMap = new HashMap<>();
        requestSpecification = RestAssured.given().log().all().contentType(ContentType.JSON);
        if (pathParams != null)
            requestSpecification.pathParams("pathparam", pathParams);

        return requestSpecification.queryParams(queryParams)
                .headers(headersMap)
                .when().get(endPoint)           //get(pathParams!=null?"/{pathparam}":"")
                .then().log().all()
                .extract().response();
    }
}
