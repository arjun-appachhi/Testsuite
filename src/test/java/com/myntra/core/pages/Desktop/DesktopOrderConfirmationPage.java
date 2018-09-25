package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.OrderConfirmationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DesktopOrderConfirmationPage extends OrderConfirmationPage {


    @Step
    @Override
    protected OrderConfirmationPage navigateToOrdersPage() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("btnContinueShopping")));
        utils.waitForElementToBeVisible(getLocator("btnContinueShopping"));
        utils.click(getLocator("btnContinueShopping"), true);
        utils.waitForElementToBeVisible(getLocator("icoUser"));
        utils.click(getLocator("icoUser"));
        utils.click(getLocator("lnkOrder"));
        return this;
    }
}
