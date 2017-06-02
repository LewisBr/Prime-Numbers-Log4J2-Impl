package com.updoxx;

import com.updoxx.delegate.DelegateTestSuite;
import com.updoxx.performance.PerformanceTestSuite;
import com.updoxx.util.UtilTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        DelegateTestSuite.class,
        PerformanceTestSuite.class,
        UtilTestSuite.class
})
public class CodeChallengeTestingSuite {
    // Empty Constructor for Testing Suite
}
