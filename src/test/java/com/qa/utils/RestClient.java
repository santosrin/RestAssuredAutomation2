package com.qa.utils;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RestClient {

    private static Logger logger = LogManager.getLogger(RestClient.class);
    RequestSpecification httpRequest;

    public RestClient() {
        httpRequest = RestAssured.given();
    }

    public RestClient(String baseURI,String port) {
        if(baseURI != null) {
            RestAssured.baseURI = baseURI;
            logger.info("Base URI : "+ baseURI);
        }
        if(port!= null) {
            RestAssured.port = Integer.valueOf(port);
        }
        httpRequest = RestAssured.given();
    }

    public Response get(String methodURI,int expectedStatusCode) {
        logger.info("URI is : " + methodURI);
        Response response = httpRequest.request(Method.GET,methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response get(String methodURI,int expectedStatusCode , Map<String,String> queryParams) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key : queryParams.keySet()) {
            requestSpecification.queryParam(key,queryParams.get(key));
        }
        Response response = requestSpecification.get(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response get(String methodURI,int expectedStatusCode,String userName,String password,
                        Map<String,String> headers) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        if(headers!=null){
            for(String key : headers.keySet()) {
                requestSpecification.header(key,headers.get(key));
            }
        }
        Response response = requestSpecification.auth().basic(userName,password).get(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response get(String methodURI,int expectedStatusCode , Map<String,String> queryParams,
                        Map<String, String> headers) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        if(queryParams!= null) {
            for(String key : queryParams.keySet()) {
                requestSpecification.queryParam(key,queryParams.get(key));
            }
        }
        if(headers!= null) {
            for(String key : headers.keySet()) {
                requestSpecification.header(key,headers.get(key));
            }
        }
        Response response = requestSpecification.get(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().prettyPrint());
        return response;
    }

    public Response post(String methodURI,int expectedStatusCode , String jsonString) {
        logger.info("URI is : "+ methodURI);
        httpRequest.header("Content-Type","application/json");

        //add json to body of the request
        httpRequest.body(jsonString);

        Response response = httpRequest.post(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response post(String methodURI,int expectedStatusCode,String jsonString,
                         Map<String,String> headers) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key : headers.keySet()) {
            requestSpecification.header(key,headers.get(key));
        }

        //add body
        requestSpecification.body(jsonString);

            Response response = requestSpecification.post(methodURI).then().statusCode(expectedStatusCode).
                    extract().response();
            logger.info("Response to Get Call is : "+ response.getBody().asString());
            return response;
    }

    public Response post(String methodURI,int expectedStatusCode,Map<String,String> headers) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key : headers.keySet()){
            requestSpecification.header(key,headers.get(key));
        }

        Response response = requestSpecification.post(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response post(String methodURI,int expectedStatusCode,String jsonString,
                         Map<String,String> headers,String userName,String password) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key : headers.keySet()) {
            requestSpecification.header(key,headers.get(key));
        }

        //add body
        requestSpecification.body(jsonString);

        //Post
        Response response = requestSpecification.auth().basic(userName,password).post(methodURI).
                then().statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response put(String methodURI,int expectedStatusCode,String jsonString,
                        Map<String,String> headers,String userName,String password) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key : headers.keySet()) {
            requestSpecification.header(key,headers.get(key));
        }

        //add body
        requestSpecification.body(jsonString);

        //post
        Response response = requestSpecification.auth().basic(userName,password).put(methodURI).
                then().statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response put(String methodURI,int expectedStatusCode,Map<String,String> headers,
                        String userName,String password) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key: headers.keySet()) {
            requestSpecification.header(key,headers.get(key));
        }

        Response response = requestSpecification.auth().basic(userName,password).put(methodURI).
                then().statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response put(String methodURI,int expectedStatusCode, String jsonString) {
        logger.info("URI is : "+ methodURI);
        httpRequest.header("Content-Type","application/json");

        if(jsonString!= null){
            httpRequest.body(jsonString);
        }

        Response response = httpRequest.put(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response put(String methodURI,int expectedStatusCode,String jsonString,
                        Map<String,String> headers) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key: headers.keySet()) {
            requestSpecification.header(key,headers.get(key));
        }

        //body
        if(jsonString!=null){
            requestSpecification.body(jsonString);
        }

        Response response = requestSpecification.put(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().prettyPrint());
        return response;
    }

    public Response delete(String methodURI,int expectedStatusCode,String jsonString,
                           Map<String,String> headers) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        if(headers!=null) {
            for(String key:headers.keySet()) {
                requestSpecification.header(key,headers.get(key));
            }
        }
        if(jsonString!=null) {
            requestSpecification.body(jsonString);
        }

        Response response = requestSpecification.delete(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response delete(String methodURI, int expectedStatusCode,Map<String,String> queryParams,
                           Map<String,String> headers) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key: queryParams.keySet()) {
            requestSpecification.queryParam(key,queryParams.get(key));
        }

        for(String key: headers.keySet()) {
            requestSpecification.header(key,headers.get(key));
        }

        Response response = requestSpecification.delete(methodURI).then().
                statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public Response delete(String methodURI,int expectedStatusCode, String jsonString,Map<String,String> headers,
                           String userName,String password) {
        logger.info("URI is : "+ methodURI);
        RequestSpecification requestSpecification = httpRequest;
        for(String key : headers.keySet()) {
            requestSpecification.header(key,headers.get(key));
        }

        //add body
        requestSpecification.body(jsonString);

        Response response = requestSpecification.auth().basic(userName,password).delete(methodURI).
                then().statusCode(expectedStatusCode).extract().response();
        logger.info("Response to Get Call is : "+ response.getBody().asString());
        return response;
    }

    public String getResponseBody(Response response) {
        return response.getBody().asString();
    }

    public int getResponseCode(Response response) {
        return  response.getStatusCode();
    }

    public Map<String,String> getResponseCookies(Response response) {
        return  response.getCookies();
    }

    public Headers getResponseHeaders(Response response) {
        return response.getHeaders();
    }

    public void printAllResponseHeaders(Response response) {
        Headers headers = response.getHeaders();
        for(Header header : headers) {
            logger.info("Header: " + header.getName() + " Value: " + header.getValue());
        }
    }

    public Cookies getDetailedCookies(Response response) {
        return  response.getDetailedCookies();
    }

    public String getSessionID(Response response) {
        return response.getSessionId();
    }

    public String getStatusMessage(Response response) {
        return response.getStatusLine();
    }
    public long getTime(Response response) {
        return response.getTimeIn(TimeUnit.MILLISECONDS);
    }

    public JsonPath getJsonResponse(Response response) {
        return response.jsonPath();
    }

    public JsonPath getJsonResponseData(Response response,String parameter) {
        return response.jsonPath().get(parameter);
    }

}
