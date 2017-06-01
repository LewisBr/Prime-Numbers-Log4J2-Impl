package com.updoxx.domain;

import com.updoxx.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ResponseWrapper {
    private List<Integer> listOfPrimes;
    private String        responseCode;
    private String        responseMessage;
    private int           lowerValue;
    private int           higherValue;
    private long          runtime;

    public ResponseWrapper() {
        this.responseCode = Constants.SUCCESS;  // Set the value to SUCCESS by default, then we only need to transition to ERROR in ERROR States.
        this.listOfPrimes = new ArrayList<>();  // Initialize list so that we can avoid errant NPE's
    }

    public ResponseWrapper(int lowerValue, int higherValue) {
        this.responseCode = Constants.SUCCESS;  // Set the value to SUCCESS by default, then we only need to transition to ERROR in ERROR States.
        this.listOfPrimes = new ArrayList<>();  // Initialize list so that we can avoid errant NPE's
        this.lowerValue = lowerValue;
        this.higherValue = higherValue;
    }

    public ResponseWrapper(List<Integer> listOfPrimes, String responseCode, String responseMessage, int lowerValue, int higherValue) {
        this.listOfPrimes = listOfPrimes;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.lowerValue = lowerValue;
        this.higherValue = higherValue;
    }

    public List<Integer> getListOfPrimes() {
        return listOfPrimes;
    }

    public void setListOfPrimes(List<Integer> listOfPrimes) {
        this.listOfPrimes = listOfPrimes;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(int lowerValue) {
        this.lowerValue = lowerValue;
    }

    public int getHigherValue() {
        return higherValue;
    }

    public void setHigherValue(int higherValue) {
        this.higherValue = higherValue;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }
}
