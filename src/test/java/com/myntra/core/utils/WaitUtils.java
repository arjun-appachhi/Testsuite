package com.myntra.core.utils;

import com.myntra.utils.logger.ILogger;

public class WaitUtils implements ILogger {
    public static void waitFor(int sec, String message) {
        try {
            LOG.debug(String.format("Wait for %d sec to %s", sec, message));
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
