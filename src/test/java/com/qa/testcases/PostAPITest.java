package com.qa.testcases;

import com.qa.utils.APIHelper;
import org.testng.annotations.Test;

public class PostAPITest {
    private final APIHelper apiHelper;

    public PostAPITest(){
        apiHelper = new APIHelper();
    }

    @Test
    public void postCallAPI() {
        apiHelper.postCallAPI();
    }

}
