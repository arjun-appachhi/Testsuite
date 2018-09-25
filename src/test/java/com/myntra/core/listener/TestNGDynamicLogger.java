package com.myntra.core.listener;

import com.myntra.utils.logger.ILogger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestNGDynamicLogger implements IInvokedMethodListener, ILogger {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        LOG.debug(String.format(">> Started - %s::%s", method.getTestMethod()
                                                             .getRealClass()
                                                             .getName(), method.getTestMethod()
                                                                               .getMethodName()));

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        LOG.info(String.format(">> Finished - %s::%s", method.getTestMethod()
                                                             .getRealClass()
                                                             .getName(), method.getTestMethod()
                                                                               .getMethodName()));
    }
}
