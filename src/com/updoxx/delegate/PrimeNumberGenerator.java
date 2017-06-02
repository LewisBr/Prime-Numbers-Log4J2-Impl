package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;

/**
 * Interface used to generate a list of prime numbers and their applicable properties.  Provided with code challenge.
 */
interface PrimeNumberGenerator {
    ResponseWrapper generate(int startingValue, int EndingValue);

    boolean isPrime(int value);
}
