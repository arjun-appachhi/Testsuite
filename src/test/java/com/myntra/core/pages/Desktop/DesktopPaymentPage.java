package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.PaymentPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class DesktopPaymentPage extends PaymentPage {

    @Step("checking whether page is loaded fine")
    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
        utils.waitForElementToBeVisible(getLocator("txtPaymentHeader"));
        Assert.assertTrue(utils.isElementPresent(getLocator("txtPaymentHeader"), 5), "Desktop Payment page is not loaded");
    }
}
