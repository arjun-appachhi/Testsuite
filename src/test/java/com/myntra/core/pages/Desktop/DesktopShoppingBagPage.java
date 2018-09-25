package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.ShoppingBagPage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class DesktopShoppingBagPage extends ShoppingBagPage {

    @Step
    @Override
    public AddressPage placeOrder() {
        HashMap<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetails");
        utils.click(getLocator("btnPlaceOrder"));
        return AddressPage.createInstance();
    }

    @Step
    @Override
    public ShoppingBagPage applyPersonalisedCoupon() {
        super.applyPersonalisedCoupon();
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"));
        String personalisedCoupons = getCouponTestData().getCouponCode();
        utils.sendKeys(getLocator("txtCouponCode"), personalisedCoupons);
        utils.click(getLocator("btnApply"));
        utils.wait((ExpectedConditions.invisibilityOfElementLocated(getLocator("btnApply"))));
        return this;
    }

    @Step
    @Override
    public ShoppingBagPage applyPersonalisedCouponForSignUpAccount() {
        int refreshcount = 0;
        int maxRefreshcount = 10;
        super.applyPersonalisedCoupon();
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"));
        String personalisedCoupons = getCouponTestData().getCouponCode();
        utils.sendKeys(getLocator("txtCouponCode"), personalisedCoupons);
        utils.click(getLocator("btnApply"));
        while (isCouponNotLiveMessageDisplayed() && refreshcount <= maxRefreshcount) {
            utils.click(getLocator("btnApply"));
            refreshcount++;
        }
        utils.wait((ExpectedConditions.invisibilityOfElementLocated(getLocator("btnApply"))));
        return this;
    }

    @Step
    private boolean isCouponNotLiveMessageDisplayed() {
        return (utils.isElementPresent(getLocator("txaCouponNotLiveMessage"), 2));
    }

    @Step
    @Override
    public boolean isProductPresentInBag() {
        //TODO Myntra Team please remove this if condition once the bug is fixed; Jira ID:VEGASF_579
        if (!super.isProductPresentInBag()) {
            utils.refreshPage();
        }
        return super.isProductPresentInBag();
    }
}
