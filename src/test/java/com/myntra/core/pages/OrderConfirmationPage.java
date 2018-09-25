package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopOrderConfirmationPage;
import com.myntra.core.pages.MobileWeb.MobileWebOrderConfirmationPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidOrderConfirmationPage;
import com.myntra.core.pages.NativeIOS.NativeIOSOrderConfirmationPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public abstract class OrderConfirmationPage extends Page {

    public static OrderConfirmationPage createInstance() {
        OrderConfirmationPage derivedOrderConfirmationPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedOrderConfirmationPage = (OrderConfirmationPage) DynamicEnhancer.create(
                        NativeAndroidOrderConfirmationPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedOrderConfirmationPage = (OrderConfirmationPage) DynamicEnhancer.create(
                        NativeIOSOrderConfirmationPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedOrderConfirmationPage = (OrderConfirmationPage) DynamicEnhancer.create(
                        DesktopOrderConfirmationPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedOrderConfirmationPage = (OrderConfirmationPage) DynamicEnhancer.create(
                        MobileWebOrderConfirmationPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedOrderConfirmationPage;
    }

    @Override
    public String pageName() {
        return OrderConfirmationPage.class.getSimpleName();
    }

    @Step
    public boolean isOrderConfirmedWithOrderNo() {
        return (utils.isElementPresent(getLocator("txaOrderConfirmed"), 2) &&
                utils.isElementPresent(getLocator("txaOrderNumber"), 2));
    }

    @Step
    public boolean isOrderNumberAvailableInMyOrders() {
        String orderNumberConfirmation = utils.getText(getLocator("txaOrderNumber"))
                                              .replaceAll("-", "");
        HashMap<String, String> orderDetails = new HashMap<>();
        orderDetails.put("orderConfirmationNumber", orderNumberConfirmation);
        testExecutionContext.addTestState("orderNumber", orderDetails);
        navigateToOrdersPage();
        return isOrderNumberAvailableWithRetry();
    }

    @Step
    protected boolean isOrderNumberAvailableWithRetry() {
        boolean isOrderNumberAvailable = !(getMyPlacedOrderElement() == null);
        // TODO Myntra Team please remove this condition once refresh issue is fixed; Jira ID:VEGASF_582
        if (!isOrderNumberAvailable) {
            //Explicity making it wait for 15 secs with wrong locator as discussed with Myntra Team due to late order response
            utils.isElementPresent(getLocator("lnkViewOrder"), 20);
            utils.refreshPage();
            utils.refreshPage();
            isOrderNumberAvailable = !(getMyPlacedOrderElement() == null);
        }
        return isOrderNumberAvailable;
    }

    @Step
    public HomePage navigateToHomePage() {
        utils.waitForElementToBeVisible(getLocator("imgMyntraLogo"));
        utils.click(getLocator("imgMyntraLogo"));
        return HomePage.createInstance();
    }



    @Step
    protected WebElement getMyPlacedOrderElement() {
        WebElement myOrder = null;
        if (!utils.isElementPresent(By.className("error-card"), 5)) {
            List<WebElement> allOrders = utils.findElements(getLocator("lstOrders"));
            boolean isOrderFound = false;
            HashMap<String, String> placedOrderDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                    "orderNumber");
            String placedOrderConfirmationNumber = placedOrderDetails.get("orderConfirmationNumber");
            for (WebElement eachOrder : allOrders) {
                String currentOrderNumber = eachOrder.findElement(getLocator("txaOrderNumberInMyOrdersPage"))
                                                     .getText()
                                                     .replaceAll("-", "");
                if (currentOrderNumber.equals(placedOrderConfirmationNumber)) {
                    myOrder = eachOrder;
                    isOrderFound = true;
                    LOG.info(String.format("Found placed order - %s in %s", placedOrderConfirmationNumber,
                            getClass().getSimpleName()));
                    break;
                }
            }
            if (!isOrderFound) {
                LOG.info(String.format("Did not find placed order - %s in %s", placedOrderConfirmationNumber,
                        getClass().getSimpleName()));
            }
        }
        return myOrder;
    }

    @Step
    protected abstract OrderConfirmationPage navigateToOrdersPage();
}
