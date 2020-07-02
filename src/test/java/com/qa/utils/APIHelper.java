package com.qa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class APIHelper extends TestBase {

    private static Logger logger = LogManager.getLogger(APIHelper.class);
    private static RestClient client;
    private String url;
    private static Response response;
    private ObjectMapper objectMapper;
    private PostApiPojo postApi;
    private static String prodId;

//    public APIHelper() throws IOException {
//    }

    public Response getCallAPI() {

        url = prop.getProperty("apiGetUrl");
        client = new RestClient();
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("page","2");

        response = client.get(url,200,queryParams);
        logger.info("Response of GET call: " + response.getBody().prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
        APIAssertions.assertGetCall(response);
        return response;
    }

    public Response postCallAPI() {
        url = prop.getProperty("apiGetUrl");
        client = new RestClient();
        HashMap<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        objectMapper = new ObjectMapper();
        postApi = new PostApiPojo();
        postApi.setName("SanMan");
        postApi.setJob("QA");
        String body = null;
        try {
            body = objectMapper.writeValueAsString(postApi);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response = client.post(url,201,body,header);
        logger.info("Response of POST call: " + response.getBody().prettyPrint());
        Assert.assertEquals(response.statusCode(),201);
        APIAssertions.assertPostCall(response);
        return response;
    }

    public void deleteCallAPI() {
        url = prop.getProperty("apiGetUrl")+"/2";
        client = new RestClient();
        response = client.delete(url,204,"",null);
        Assert.assertEquals(response.statusCode(),204);
        logger.info("DELETE call assertion completed successfully");
    }

    public Response putCallAPI() {
        url = prop.getProperty("apiGetUrl") + "/2";
        client = new RestClient();
        HashMap<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        response = client.put(url,200,body,header);
        APIAssertions.assertPutCall(response);
        return response;
    }

    public Response validateBigGetAPI() {
        url = prop.getProperty("apiUrl");
        client = new RestClient();
        HashMap<String,String> header = new HashMap<>();
        header.put("Authorization",prop.getProperty("basicAuth"));
        response = client.get(url,200,null,header);
        Assert.assertEquals(response.statusCode(),200);
        APIAssertions.assertBigGetAPICall(response);
        return response;
    }

    public Response postShopifyCallAPI() {
        url = prop.getProperty("shopifyBaseUri")+"/products.json";
        logger.info("Url is :" + url);
        client = new RestClient();
        HashMap<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        String body = Payloads.PostPayloadShopify();
        response = client.post(url,201,body,header,
                prop.getProperty("shopifyUn"),prop.getProperty("shopifyPwd"));
        logger.info("Response of POST call: " + response.getBody().prettyPrint());
        Assert.assertEquals(response.statusCode(),201);
        Map<String,Object> product = response.jsonPath().getMap("product");
        prodId = product.get("id").toString();
        logger.info("prodId : "+ prodId);
        //APIAssertions.assertPostCall(response);
        return response;
    }

    public Response getShopifyCallAPI() {
        url = prop.getProperty("shopifyBaseUri")+"/products/"+prodId+".json";
        logger.info("Url is :" + url);
        client = new RestClient();
        HashMap<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        //String body = Payloads.PostPayloadShopify();
        response = client.get(url,200,
                prop.getProperty("shopifyUn"),prop.getProperty("shopifyPwd"),header);
        logger.info("Response of GET call: " + response.getBody().prettyPrint());
        Assert.assertEquals(response.statusCode(),200);
        //APIAssertions.assertPostCall(response);
        return response;
    }

}
