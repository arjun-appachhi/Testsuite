package com.myntra.core.business;

import com.myntra.core.central.SessionContext;
import com.myntra.core.central.TestExecutionContext;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.scenarios.SupportTest;
import com.myntra.ui.Channel;
import com.myntra.utils.config.ConfigProperties;
import com.myntra.utils.logger.ILogger;
import org.openqa.selenium.InvalidArgumentException;
import org.testng.asserts.SoftAssert;

import static com.myntra.ui.BaseUIHelper.CONFIG_MANAGER;

public abstract class BusinessFlow implements ILogger {
    private static Channel CHANNEL = Channel.value(CONFIG_MANAGER.getString(ConfigProperties.CHANNEL.getKey()));
    private static String ENVIRONMENT = CONFIG_MANAGER.getString(ConfigProperties.ENVIRONMENT.getKey());

    public enum Of {
        HOME,
        USER,
        PAYMENT,
        ORDER,
        PAYMENT_GATEWAY,
        ADDRESS,
        PRODUCT,
        SEARCH,
        SHOPPING_BAG,
        WISHLIST,
        MYMYNTRA
    }

    private String testName;
    SoftAssert soft;

    BusinessFlow() {
        TestExecutionContext testExecutionContext = SessionContext.getTestExecutionContext(Thread.currentThread()
                                                                                                 .getId());
        soft = SupportTest.getSoftAssert();
        testName = testExecutionContext.getTestName();
    }

    public Home home() {
        return Home.getInstance();
    }

    protected boolean shouldExecuteIfNotInProd(String simpleClassName, String methodName) {
        boolean isProdEnvironment = "prod".equalsIgnoreCase(ENVIRONMENT);
        if (isProdEnvironment) {
            String message = String.format("%s.%s - Subsequent actions NOT APPLICABLE for Channel - %s in Environment - %s", simpleClassName,
                    methodName, CHANNEL, ENVIRONMENT);
            LOG.warn(message);
            SupportTest.logStep(message);
        } else {
            String message = String.format("%s.%s - APPLICABLE for Channel - %s in Environment - %s. Executing it", simpleClassName, methodName,
                    CHANNEL, ENVIRONMENT);
            LOG.info(message);
        }
        return !isProdEnvironment;
    }

    protected static BusinessFlow getInstance(Of classType) {
        BusinessFlow businessFlow;
        switch (classType) {
            case HOME:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(Home.class, new DynamicLogger());
                break;
            case USER:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(User.class, new DynamicLogger());
                break;
            case PAYMENT:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(Payment.class, new DynamicLogger());
                break;
            case ORDER:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(Order.class, new DynamicLogger());
                break;
            case PAYMENT_GATEWAY:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(PaymentGateway.class, new DynamicLogger());
                break;
            case ADDRESS:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(Address.class, new DynamicLogger());
                break;
            case PRODUCT:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(Product.class, new DynamicLogger());
                break;
            case SEARCH:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(Search.class, new DynamicLogger());
                break;
            case SHOPPING_BAG:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(ShoppingBag.class, new DynamicLogger());
                break;
            case WISHLIST:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(WishList.class, new DynamicLogger());
                break;
            case MYMYNTRA:
                businessFlow = (BusinessFlow) DynamicEnhancer.create(MyMyntra.class, new DynamicLogger());
                break;
            default:
                throw new InvalidArgumentException(String.format("Invalid Business Flow type specified - %s", classType.getDeclaringClass()
                                                                                                                       .getName()));
        }
        return businessFlow;
    }
}
