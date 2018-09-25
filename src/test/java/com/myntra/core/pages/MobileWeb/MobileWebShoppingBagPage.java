package com.myntra.core.pages.MobileWeb;

import com.myntra.core.pages.ShoppingBagPage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class MobileWebShoppingBagPage extends ShoppingBagPage {

    @Step
    @Override
    public ShoppingBagPage applyPersonalisedCoupon() {
        super.applyPersonalisedCoupon();
        utils.scrollDown_m(3);
        utils.click(getLocator("tabApplyCoupon"), true);
        String personalisedCoupons = getCouponTestData().getCouponCode();
        utils.waitForElementToBeVisible((getLocator("txtCouponCode")));
        utils.sendKeys(getLocator("txtCouponCode"), personalisedCoupons);
        utils.click(getLocator("btnApply"), true);
        return this;
    }

    @Step
    @Override
    protected ShoppingBagPage giftWrapThisProduct() {
        utils.scrollDown_m(3);
        utils.click(getLocator("btnGiftWrap"), true);
        utils.sendKeys(getLocator("txtRecipient"), (String) getTestData().getAdditionalProperties()
                                                                         .get("RecipientName"));
        utils.sendKeys(getLocator("txtMessage"), (String) getTestData().getAdditionalProperties()
                                                                       .get("GiftMessage"));
        utils.sendKeys(getLocator("txtSender"), (String) getTestData().getAdditionalProperties()
                                                                      .get("SenderName"));
        utils.click(getLocator("btnSaveGift"), true);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveGift")));
        return this;
    }

    @Step
    @Override
    protected ShoppingBagPage removeExistingGiftWrapAndAddItAgain() {
        utils.click(getLocator("btnRemoveGiftWrap"));
        LOG.debug("GiftWrap removed");
        giftWrapThisProduct();
        return this;
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

    @Step
    @Override
    public ShoppingBagPage applyMyntCouponCode() {
        utils.click(getLocator("btnMyntApplyCode"), true);
        utils.sendKeys(getLocator("txtMyntDiscount"), (String) getCouponTestData().getAdditionalProperties()
                                                                                  .get("MyntCoupon"));
        utils.click(getLocator("btnMyntApply"), true);
        return this;
    }

    @Step
    @Override
    public boolean isNotApplicableCouponCodeSelected() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"), true);
        if (utils.isElementPresent(getLocator("txaCouponsNotApplicableHeader"), 2)) {
            utils.waitForElementToBeVisible(getLocator("lstCouponsNotApplicable"));
            utils.click(getLocator("lstCouponsNotApplicable"), true);
        } else {
            LOG.info("Not applicable coupons are not available");
        }
        return (utils.isElementPresent(getLocator("lstCouponNotApplicableAndDisabled"), 2));
    }

    @Step
    @Override
    public ShoppingBagPage applyPersonalisedCouponForSignUpAccount() {
        int refreshcount = 0;
        int maxRefreshcount = 10;
        super.applyPersonalisedCoupon();
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"), true);
        String personalisedCoupons = getCouponTestData().getCouponCode();
        utils.sendKeys(getLocator("txtCouponCode"), personalisedCoupons);
        utils.click(getLocator("btnApply"), true);
        while (isCouponNotLiveMessageDisplayed() && refreshcount <= maxRefreshcount) {
            utils.click(getLocator("btnApply"), true);
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
    public boolean isConditionalDiscountNotAppliedForNonCOnditionalDiscountProducts() {
        return true;
    }
}

