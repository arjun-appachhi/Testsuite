package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.HamburgerPage;
import com.myntra.core.pages.HomePage;
import com.myntra.core.pages.OrderConfirmationPage;
import com.myntra.utils.test_utils.Assert;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NativeAndroidOrderConfirmationPage extends OrderConfirmationPage {

    @Step
    @Override
    public boolean isOrderConfirmedWithOrderNo() {
        nativeView();
        utils.wait(ExpectedConditions.visibilityOfElementLocated(By.id("com.myntra.android:id/btn_rate_now")), 5);
        goBack();
        webView();
        utils.waitForElementToBeVisible(getLocator("txaOrderConfirmed"));
        return (utils.isElementPresent(getLocator("txaOrderConfirmed"), 2));
    }

    @Step
    @Override
    public boolean isOrderNumberAvailableInMyOrders() {
        utils.click(getLocator("lnkViewOrder"), true);
        //TODO Myntra team please remove this condition once order details page load is fixed;
        if (utils.isElementPresent(By.className("error-showButton"),4)){
            utils.click(By.className("error-showButton"),true);
        }
        return super.isOrderNumberAvailableInMyOrders();
    }

    @Step
    @Override
    protected OrderConfirmationPage navigateToOrdersPage() {
        nativeView();
        // goBack is called to reach home page as per flow
        int backCount = 0;
        int maxBackCount = 5;
        while (!isHomePagePresent() && (backCount < maxBackCount)) {
            goBack();
            backCount++;
        }
        Assert.assertTrue((backCount < maxBackCount),
                String.format("Failed to navigate back to Home page - Made %d attempts to navigate back", maxBackCount));
        HomePage.createInstance()
                .openMenu();
        HamburgerPage.createInstance()
                     .goToMyOrdersPage();
        webView();
        return this;
    }

    @Step
    private boolean isHomePagePresent() {
        return utils.isElementPresent(MobileBy.AccessibilityId("rightElement3"), 2);
    }

    @Step
    @Override
    public HomePage navigateToHomePage() {
        nativeView();
        // goBack is called to reach home page as per flow
        int backCount = 0;
        int maxBackCount = 5;
        while (!isHomePagePresent() && (backCount < maxBackCount)) {
            goBack();
            backCount++;
        }
        return HomePage.createInstance();
    }

    private boolean isOrderNumberAvailable() {
        return !(getMyPlacedOrderElement() == null);
    }
}
