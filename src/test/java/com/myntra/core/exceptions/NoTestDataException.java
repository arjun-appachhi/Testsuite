package com.myntra.core.exceptions;

import com.myntra.utils.logger.ILogger;

public class NoTestDataException extends RuntimeException implements ILogger {
    public NoTestDataException(String message) {
        super(message);
    }
}
