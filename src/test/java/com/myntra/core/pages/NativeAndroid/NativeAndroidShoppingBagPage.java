package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.HomePage;
import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.core.pages.WishListPage;
import com.myntra.core.utils.WaitUtils;
import com.myntra.utils.test_utils.Assert;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeAndroidShoppingBagPage extends ShoppingBagPage {

    @Step
    @Override
    protected void isLoaded() {
        boolean isElementDisplayed = false;
        webView();
        if (utils.isElementPresent(By.className("retry-service-failure"), 3)) {
            isElementDisplayed = utils.findElement(By.className("retry-service-failure"))
                                      .isDisplayed();
        }
        Assert.assertTrue(!isElementDisplayed, "Failed to load shopping bag page");
    }

    @Step
    @Override
    protected void load() {
        utils.click(By.className("retry-service-failure"), true);
    }

    @Step
    @Override
    public HomePage emptyBag() {
        webView();
        clickRemoveLink();
        nativeView();
        goBack();
        return HomePage.createInstance();
    }

    @Step
    @Override
    public ShoppingBagPage applyPersonalisedCoupon() {
        int refreshcount = 0;
        int maxRefreshcount = 10;
        super.applyPersonalisedCoupon();
        webView();
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("lblGiftWrapSuccess")), 10);
        scrollTillElementVisible(getLocator("tabApplyCoupon"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")), 5);
        utils.click(getLocator("tabApplyCoupon"), true);
        String personalisedCoupons = getCouponTestData().getCouponCode();
        utils.sendKeys(getLocator("txtCouponCode"), personalisedCoupons);
        while (isCouponNotLiveMessageDisplayed() && refreshcount <= maxRefreshcount) {
            utils.click(getLocator("btnApply"));
            //Added sleep condition as discussed with Myntra Team
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refreshcount++;
        }
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("txtCouponAppliedSuccessfully")), 5);
        return this;
    }

    @Step
    @Override
    public AddressPage placeOrder() {
        webView();
        WaitUtils.waitFor(2,"Waiting to click on place order");
        return super.placeOrder();
    }

    @Step
    @Override
    public boolean isProductPresentInBag() {
        webView();
        boolean isProductPresent = super.isProductPresentInBag();
        nativeView();
        return isProductPresent;
    }

    @Step
    @Override
    public ShoppingBagPage addMoreFromWishList() {
        nativeView();
        utils.click(getLocator("tlbWishlist"), true);
        WishListPage.createInstance()
                    .moveProductToBag();
        return this;
    }

    @Step
    @Override
    public ShoppingBagPage changeProductSizeInCart() {
        webView();
        utils.click(getLocator("btnSize"));
        List<WebElement> sizeOptions = utils.findElements(getLocator("lstSelectSize"));
        boolean isSizeAvailable = false;
        for (WebElement eachSizeOption : sizeOptions) {
            String checkSize = eachSizeOption.getAttribute("class");
            if ("btn size-btn-group size-btn  ".equals(checkSize)) {
                eachSizeOption.click();
                isSizeAvailable = true;
                break;
            }
        }
        Assert.assertTrue(isSizeAvailable, "Size NOT available, or Size is NOT clickable");
        return this;
    }

    @Step
    @Override
    public ShoppingBagPage changeProductQuantityInCart() {
        utils.isElementPresent(getLocator("txtCouponCode"), 4);
        if (!isFreeGiftMsgPresent()) {
            scrollTillElementVisible(getLocator("btnQuantity"));
            utils.click(getLocator("btnQuantity"), true);
            utils.waitForElementToBeVisible(getLocator("lstSelectQty"));
            utils.click(getLocator("lstSelectQty"), true);
            utils.isElementPresent(getLocator("txtCouponCode"), 20);
        } else {
            LOG.debug("Cannot change the quantity as product is Free gift ");
        }
        return this;
    }

    @Step
    @Override
    public WishListPage moveProductToWishlist() {
        webView();
        scrollTillElementVisible(getLocator("btnMoveToWishList"));
        utils.click(getLocator("btnMoveToWishList"), true);
        nativeView();
        utils.click(getLocator("lnkWishlist"), true);
        return WishListPage.createInstance();
    }

    @Step
    @Override
    public boolean isFreeGiftPresent() {
        webView();
        return super.isFreeGiftPresent();
    }

    @Step
    @Override
    public ShoppingBagPage removeItemFromCart() {
        webView();
        scrollTillElementVisible(getLocator("btnRemove"));
        super.removeItemFromCart();
        return this;
    }

    @Step
    @Override
    public HashMap<String, String> getProductDetails() {
        webView();
        HashMap<String, String> productDetails = super.getProductDetails();
        if (productDetails.keySet().contains("Product Discount")) {
            String productDiscount = productDetails.get("Product Discount")
                                                   .substring(1)
                                                   .replace(")", "");
            productDetails.put("Product Discount", productDiscount);
        }
        return productDetails;
    }

    @Step
    @Override
    public boolean isProductDiscountInShoppingBagEqualToPDP() {
        Map<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetails");
        boolean isDiscountAvailable = true;
        if (!(getProductDetails().get("Product Discount") == null)) {
            isDiscountAvailable = productDetails.get("Product Discount")
                                                .equals(getProductDetails().get("Product Discount"));
        } else {
            LOG.info("Discount is not available");
        }
        return isDiscountAvailable;
    }

    @Step
    @Override
    protected ShoppingBagPage giftWrapThisProduct() {
        scrollTillElementVisible(getLocator("btnGiftWrap"));
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
    public ShoppingBagPage addGiftWrap() {
        webView();
        return super.addGiftWrap();
    }

    @Step
    @Override
    public WishListPage navigateToWishlist() {
        nativeView();
        return super.navigateToWishlist();
    }

    @Step
    @Override
    public ShoppingBagPage applyMyntCouponCode() {
        webView();
        scrollTillElementVisible(getLocator("txaMyntAvailable"));
        return super.applyMyntCouponCode();
    }

    @Step
    @Override
    public boolean isErrorMessageDisplayedForInvalidCoupon() {
        webView();
        scrollTillElementVisible(getLocator("tabApplyCoupon"));
        return super.isErrorMessageDisplayedForInvalidCoupon();
    }

    @Step
    @Override
    public boolean isNotApplicableCouponCodeSelected() {
        webView();
        scrollTillElementVisible(getLocator("tabApplyCoupon"));
        return super.isNotApplicableCouponCodeSelected();
    }

    @Step
    @Override
    public HomePage navigateToHomePageFromBag() {
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

    @Step
    private boolean isHomePagePresent() {
        return utils.isElementPresent(MobileBy.AccessibilityId("rightElement3"), 2);
    }
    
    @Step
    @Override
    public ShoppingBagPage applyPersonalisedCouponForSignUpAccount() {
        int refreshcount = 0;
        int maxRefreshcount = 10;
        super.applyPersonalisedCoupon();
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        scrollTillElementVisible(getLocator("tabApplyCoupon"));
        utils.click(getLocator("tabApplyCoupon"),true);
        String personalisedCoupons = getCouponTestData().getCouponCode();
        utils.sendKeys(getLocator("txtCouponCode"), personalisedCoupons);
        utils.click(getLocator("btnApply"),true);
        while (isCouponNotLiveMessageDisplayed() && refreshcount <= maxRefreshcount) {
            utils.click(getLocator("btnApply"),true);
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
    public ShoppingBagPage verifyUnauthorizedPersonalizedCoupon() {
        scrollTillElementVisible(getLocator("tabApplyCoupon"));
        return super.verifyUnauthorizedPersonalizedCoupon();
    }

    @Step
    @Override
    public ShoppingBagPage enterCouponCode() {
        scrollTillElementVisible(getLocator("tabApplyCoupon"));
        return super.enterCouponCode();
    }

    @Step
    public boolean isCouponDiscountSameForBothProduct() {
        HashMap<String, String> bagPriceDetails = (HashMap<String, String>) testExecutionContext.getTestState("bagPriceDetails");
        String secondProductBagTotal = utils.getText(getLocator("lblBagTotal")).split(" ")[1].replace(",", "");
        boolean isBagTotalSame = bagPriceDetails.get("bagTotal").equals(secondProductBagTotal);
        if (isCouponApplied() && !isBagTotalSame) {
            String firstProductCouponDiscount = bagPriceDetails.get("couponDiscount");
            String secondProductCouponDiscount = utils.getText(getLocator("lblCouponDiscount")).substring(5).trim();
            return firstProductCouponDiscount.equals(secondProductCouponDiscount);
        } else {
            return false;
        }
    }

    @Step
    @Override
    public boolean isOneMoreProductTobeAddedTOCompleteFreeGiftOffer() {
       return true;
    }

    @Step
    @Override
    public ShoppingBagPage applyValidCoupon(){
        scrollTillElementVisible(getLocator("tabApplyCoupon"));
        return super.applyValidCoupon();
    }
}
