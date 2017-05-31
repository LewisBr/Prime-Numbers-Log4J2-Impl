package com.updoxx.domain;

import com.updoxx.util.Constants;

import java.util.List;

/**
 *
 * Created by Bryan on 5/30/2017.
 */
public class ResponseWrapper {
    private List<Integer> listOfPrimes;
    private String        responseCode;
    private String        responseMessage;

    public ResponseWrapper() {
        this.responseCode = Constants.SUCCESS;   // Set the value to SUCCESS by default, then we only need to transition to ERROR in ERROR States.
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
}
