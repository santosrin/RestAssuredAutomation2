package com.qa.testcases;

import com.qa.utils.APIAssertions;
import com.qa.utils.APIHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class GetAPITest {
    private final APIHelper apiHelper;
    public GetAPITest() {
        apiHelper = new APIHelper();
    }

    @Test
    public void getCallAPI() {
        apiHelper.getCallAPI();
    }
}
