package com.qa.testcases;

import com.qa.utils.APIHelper;
import org.testng.annotations.Test;

public class DeleteAPITest {
    private final APIHelper apiHelper;

    public DeleteAPITest() {
        apiHelper = new APIHelper();
    }

    @Test
    public void deleteCallAPI() {
        apiHelper.deleteCallAPI();
    }
}
