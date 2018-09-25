package com.myntra.core.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaUtils {

    public static String datetime(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public static File[] listFiles(String dirPath, String extension) {
        File dir = new File(dirPath);

        return dir.listFiles(pathname -> pathname.getAbsolutePath()
                                                 .endsWith(extension));
    }

    public static String getCaller() {
        StackTraceElement[] stackTraceElements = Thread.currentThread()
                                                       .getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[2];
        return stackTraceElement.getMethodName();
    }

    public static String generateUserNameForTest(String testName) {
        return String.valueOf(Math.abs(Integer.parseInt(String.valueOf((testName + new Date().getTime()).hashCode()))));
    }
}
