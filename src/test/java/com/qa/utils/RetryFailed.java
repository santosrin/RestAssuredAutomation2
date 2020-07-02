package com.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailed implements IRetryAnalyzer {

    private static Logger logger = LogManager.getLogger(RetryFailed.class);
    private int count = 0;
    private int maxRetry = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
       if(!iTestResult.isSuccess()) {
           logger.info("Check if max retries reached to rerun failed tests");
           if(count<maxRetry) {
               count++;
               iTestResult.setStatus(ITestResult.FAILURE);
               return true;
           } else {
            logger.info("TestNG mark the status as failed as max retries are done");
            iTestResult.setStatus(ITestResult.FAILURE);
           }
       } else {
           logger.info("Test successful , TestNG marks status as success");
           iTestResult.setStatus(ITestResult.SUCCESS);
       }
       return false;
    }
}
