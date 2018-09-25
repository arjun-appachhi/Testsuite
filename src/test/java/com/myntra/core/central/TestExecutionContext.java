package com.myntra.core.central;

import com.myntra.testdata.models.pojo.response.MyntData;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class TestExecutionContext {
    private String testName;
    private ThreadLocal<WebDriver> webDriver;
    private String deviceId;
    private MyntData testData;
    private SoftAssert soft;
    private Map<String, Object> testExecutionState;

    public TestExecutionContext(ThreadLocal<WebDriver> webDriver, String deviceId, String testName, SoftAssert soft) {
        this.webDriver = webDriver;
        this.deviceId = deviceId;
        this.testName = testName;
        this.soft = soft;
        testExecutionState = new HashMap<>();
    }

    public void setTestData(MyntData testData) {
        this.testData = testData;
    }

    public MyntData getTestData() {
        return testData;
    }

    public String getTestName() {
        return testName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public ThreadLocal<WebDriver> getWebDriver() {
        return webDriver;
    }

    public SoftAssert getSoftAssert() {
        return soft;
    }

    public void addTestState(String key, Map<String, String> productDetails) {
        testExecutionState.put(key, productDetails);
    }

    public Object getTestState(String key) {
        return testExecutionState.get(key);
    }
}
