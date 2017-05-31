package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;

/**
 * Interface used to generate
 */
public interface PrimeNumberGenerator {
    ResponseWrapper generate(int startingValue, int EndingValue);
    boolean isPrime(int value);
}
