package com.myntra.core.central;

import com.myntra.core.listener.TestNGDynamicLogger;
import com.myntra.utils.logger.ILogger;
import org.testng.TestRunner;

import java.util.HashMap;
import java.util.Map;

public class SessionContext implements ILogger {
    private static Map<String, TestExecutionContext> allTestsExecutionContext = new HashMap<>();
    private static boolean isTestNGDynamicLoggerListenerAdded = false;

    SessionContext() {
        LOG.info("SessionContext default constructor - setting isTestNGDynamicLoggerListenerAdded = false");
        isTestNGDynamicLoggerListenerAdded = false;
    }

    public static synchronized void addContext(long threadId, TestExecutionContext testExecutionContext) {
        LOG.info(String.format("Adding context for thread - %s", threadId));
        allTestsExecutionContext.put(String.valueOf(threadId), testExecutionContext);
    }

    public static synchronized TestExecutionContext getTestExecutionContext(long threadId) {
        return allTestsExecutionContext.get(String.valueOf(threadId));
    }

    public static synchronized void remove(long threadId) {
        LOG.info(String.format("Removing context for thread - %s", threadId));
        allTestsExecutionContext.remove(String.valueOf(threadId));
    }

    public static void addDynamicLoggerListener(TestRunner context) {
        if (isTestNGDynamicLoggerListenerAdded) {
            LOG.info(String.format("%s listener already added", TestNGDynamicLogger.class.getSimpleName()));
        } else {
            LOG.info(String.format("Adding %s listener", TestNGDynamicLogger.class.getSimpleName()));
            context.getSuite()
                   .addListener(new TestNGDynamicLogger());
            isTestNGDynamicLoggerListenerAdded = true;
        }
    }
}
