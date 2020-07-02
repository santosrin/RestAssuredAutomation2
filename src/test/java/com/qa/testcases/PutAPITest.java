package com.qa.testcases;

import com.qa.utils.APIHelper;
import org.testng.annotations.Test;

public class PutAPITest {
    private final APIHelper apiHelper;

    public PutAPITest() {
       apiHelper = new APIHelper();
    }

    @Test
    public void putCallAPI() {
        apiHelper.putCallAPI();
    }
}
