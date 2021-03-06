package helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;

class HTTPRequest {
    private static final String baseUrl = "https://api.trello.com/1/";

    private static RequestSpecification generateRequest(Map<String, String> otherParams) {
        Map<String, String> fields = new HashMap<>();
        fields.put("key", ConfigLoader.getValueByKey("key"));
        fields.put("token", ConfigLoader.getValueByKey("token"));
        if (otherParams != null) {
            fields.putAll(otherParams);
        }
        return rest().given().contentType(ContentType.JSON).and().queryParams(fields);
    }

    public static Response getRequest(Map<String, String> otherParams, String path) {
        String link = baseUrl + path;
        return generateRequest(otherParams).when().get(link);
    }

    public static Response postRequest(Map<String, String> otherParams, String path) {
        String link = baseUrl + path;
        return generateRequest(otherParams).when().post(link);
    }

    public static Response deleteRequest(Map<String, String> otherParams, String path) {
        String link = baseUrl + path;
        return generateRequest(otherParams).when().delete(link);
    }

    public static Response putRequest(Map<String, String> otherParams, String path) {
        String link = baseUrl + path;

        return generateRequest(otherParams).when().put(link);
    }
}
