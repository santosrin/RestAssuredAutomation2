package com.qa.testcases;

import com.qa.utils.APIHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

public class PostAndGetShopifyAPI {
    private final APIHelper apiHelper;
    private static String prodId;
    Response response;
    public PostAndGetShopifyAPI(){
        apiHelper = new APIHelper();
    }

    @Test
    public void shopifyPostGetCallAPI() {
        apiHelper.postShopifyCallAPI();
        apiHelper.getShopifyCallAPI();
    }
}
