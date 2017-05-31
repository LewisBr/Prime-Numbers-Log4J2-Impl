package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;

/**
 * Created by Bryan on 5/30/2017.
 */
public class PrimeDelegate implements PrimeNumberGenerator {
    @Override
    public ResponseWrapper generate(int startingValue, int EndingValue) {
        ResponseWrapper ret = new ResponseWrapper();
        return ret;
    }

    @Override
    public boolean isPrime(int value) {
        return false;
    }
}
