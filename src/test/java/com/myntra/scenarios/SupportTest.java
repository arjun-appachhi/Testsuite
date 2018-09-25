package com.myntra.scenarios;

import com.myntra.core.business.Home;
import com.myntra.core.central.SFCentral;
import com.myntra.core.central.SessionContext;
import com.myntra.core.central.TestExecutionContext;
import com.myntra.testdata.models.pojo.response.MyntData;
import com.myntra.utils.config.ConfigManager;
import com.myntra.utils.config.ConfigProperties;
import com.myntra.utils.test_utils.BaseTest;
import io.qameta.allure.Step;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

public class SupportTest extends BaseTest implements IHookable {
    private String testName = null;

    private static final String SOFT_ASSERT = "softAssert";

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        SoftAssert softAssert = new SoftAssert();
        testResult.setAttribute(SOFT_ASSERT, softAssert);
        callBack.runTestMethod(testResult);
        softAssert = getSoftAssert(testResult);
        if (!testResult.isSuccess()) {
            Throwable throwable = testResult.getThrowable();
            if (null != throwable) {
                if (null != throwable.getCause()) {
                    throwable = throwable.getCause();
                }
                softAssert.assertNull(throwable, ExceptionUtils.getStackTrace(throwable));
            }
        }
        softAssert.assertAll();
    }

    public static SoftAssert getSoftAssert() {
        return getSoftAssert(Reporter.getCurrentTestResult());
    }

    private static SoftAssert getSoftAssert(ITestResult result) {
        Object object = result.getAttribute(SOFT_ASSERT);
        if (object instanceof SoftAssert) {
            return (SoftAssert) object;
        }
        throw new IllegalStateException("Could not find a soft assertion object");
    }

    @Step("{0}")
    public static void logStep(final String message) {
        //intentionally empty - this will create a step in the Allure report based on the message
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) throws Exception {
        LOG.info(String.format("%s - beforeSuite", getClass().getSimpleName()));
        SFCentral.INSTANCE.init();
        SessionContext.addDynamicLoggerListener((TestRunner) context);
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext context) {
        LOG.info(String.format("%s - beforeClass", getClass().getSimpleName()));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, Method method) {
        LOG.info(String.format("%s - beforeMethod", getClass().getSimpleName()));
        testName = method.getName();
        LOG.info(String.format("Started Test (%s) execution", testName));
        TestExecutionContext testExecutionContext = new TestExecutionContext(getDriver(), getDeviceId(), testName, new SoftAssert());

        SessionContext.addContext(Thread.currentThread()
                                        .getId(), testExecutionContext);

        LOG.info(String.format("Starting test execution - %s", testName));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, Method method, ITestResult result) {
        LOG.info(String.format("%s - afterMethod", getClass().getSimpleName()));
        testName = method.getName();
        LOG.info(String.format("Finished Test (%s) execution :: Is execution successful? : %s", testName, result.isSuccess()));
        SessionContext.remove(Thread.currentThread()
                                    .getId());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext suite) {
        LOG.info(String.format("%s - afterClass", getClass().getSimpleName()));
    }

    @Step
    Home setupLoginAndReset() {
        LOG.info(String.format("setupLoginAndReset - Set up for test method [%s] started.", testName));
        TestExecutionContext testExecutionContext = SessionContext.getTestExecutionContext(Thread.currentThread()
                                                                                                 .getId());
        MyntData testData = SFCentral.INSTANCE.getTestData(testName);
        testExecutionContext.setTestData(testData);

        Home home = Home.getInstance()
                        .loginSuccessfully()
                        .home();
        home = cleanStateIfEnvironmentIsNotProd(home);

        LOG.info(String.format("setupLoginAndReset - Set up for test method [%s] ended.", testName));
        return home;
    }

    @Step
    private Home cleanStateIfEnvironmentIsNotProd(Home home) {
        ConfigManager CONFIG_MANAGER = ConfigManager.getInstance();
        String environment = CONFIG_MANAGER.getString(ConfigProperties.ENVIRONMENT.getKey());
        if ("prod".equalsIgnoreCase(environment)) {
            LOG.info(String.format("Running tests in 'prod' environment. Clean user state from UI before proceeding", environment));
            //TODO ONCE THE LOCATORS ARE IN SYNC NEED TO UNCOMMENT RESET METHOD
            home.cleanState();
        } else {
            LOG.info(String.format("Running tests in %s environment. No need for user state cleanup", environment));
        }
        return home;
    }
}
