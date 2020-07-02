package com.qa.testcases;

import com.qa.utils.APIHelper;
import org.testng.annotations.Test;

public class GetBigAPITest {
    private final APIHelper apiHelper;
    public GetBigAPITest() {
        apiHelper = new APIHelper();
    }

    @Test
    public void validateBigGetAPI() {
        apiHelper.validateBigGetAPI();
    }
}
