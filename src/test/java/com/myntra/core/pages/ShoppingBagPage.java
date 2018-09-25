package com.myntra.core.pages;

import com.myntra.core.central.SFCentral;
import com.myntra.core.central.testData.DataCreator;
import com.myntra.core.enums.ChannelUtils;
import com.myntra.core.pages.Desktop.DesktopShoppingBagPage;
import com.myntra.core.pages.MobileWeb.MobileWebShoppingBagPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidShoppingBagPage;
import com.myntra.core.pages.NativeIOS.NativeIOSShoppingBagPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myntra.core.utils.WaitUtils.waitFor;

public abstract class ShoppingBagPage extends Page {

    public static ShoppingBagPage createInstance() {
        ShoppingBagPage derivedShoppingBagPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedShoppingBagPage = (ShoppingBagPage) DynamicEnhancer.create(NativeAndroidShoppingBagPage.class,
                        new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedShoppingBagPage = (ShoppingBagPage) DynamicEnhancer.create(NativeIOSShoppingBagPage.class,
                        new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedShoppingBagPage = (ShoppingBagPage) DynamicEnhancer.create(DesktopShoppingBagPage.class,
                        new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedShoppingBagPage = (ShoppingBagPage) DynamicEnhancer.create(MobileWebShoppingBagPage.class,
                        new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedShoppingBagPage;
    }

    @Override
    public String pageName() {
        return ShoppingBagPage.class.getSimpleName();
    }

    @Step
    public ShoppingBagPage applyPersonalisedCoupon() {
        SFCentral.INSTANCE.createAndAddPersonalisedCouponIFSpecifiedInDataFile(new DataCreator(), getTestData());
        return this;
    };

    @Step
    public ShoppingBagPage applyPersonalisedCouponForSignUpAccount() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public AddressPage placeOrder() {
        utils.waitForElementToBeVisible(getLocator("btnPlaceOrder"));
        utils.click(getLocator("btnPlaceOrder"), true);
        return AddressPage.createInstance();
    }

    @Step
    protected void handleOkPermission() {
        if (ChannelUtils.isMobileNativePlatform(getChannelUtils())) {
            if (utils.isElementPresent(getLocator("btnOKPermission"), 3)) {
                utils.click(getLocator("btnOKPermission"));
            }
        }
    }

    @Step
    public boolean isProductPresentInBag() {
        return utils.isElementPresent(getLocator("btnRemove"), 5);
    }

    @Step
    public boolean isCouponApplied() {
        return utils.isElementPresent(getLocator("lblYouSaved"), 5);
    }

    @Step
    public HomePage emptyBag() {
        clickRemoveLink();
        goBack();
        return HomePage.createInstance();
    }

    @Step
    protected ShoppingBagPage clickRemoveLink() {
        int productCount = 0;
        int maxProductCount = 10;
        while (!isEmptyBagMsgPresent() && (productCount < maxProductCount)) {
            WebElement removeItem = utils.findElement(getLocator("btnRemove"));
            if (null != removeItem) {
                removeItemFromCart();
            }
            productCount++;
        }
        Assert.assertTrue((productCount < maxProductCount), String.format(
                "Shopping bag page is not functional/More number of products found - Made %d attempts to empty bag",
                maxProductCount));
        return this;
    }

    @Step
    protected boolean isEmptyBagMsgPresent() {
        return utils.isElementPresent(getLocator("lblEmptyBagMsg"), 5);
    }

    @Step
    public ShoppingBagPage removeItemFromCart() {
        utils.click(getLocator("btnRemove"), true);
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnPopupRemove")));
        utils.click(getLocator("btnPopupRemove"), true);
        return this;
    }

    @Step
    public ShoppingBagPage addGiftWrap() {
        // This is implemented for Android_WEB
        if (!isGiftWrapAdded()) {
            return giftWrapThisProduct();
        } else {
            removeExistingGiftWrapAndAddItAgain();
        }
        return this;
    }

    @Step
    public boolean isGiftWrapAdded() {
        return utils.isElementPresent(getLocator("btnEditGiftMsg"), 10);
    }

    @Step
    protected ShoppingBagPage giftWrapThisProduct() {
        utils.click(getLocator("btnGiftWrap"));
        utils.sendKeys(getLocator("txtRecipient"), (String) getTestData().getAdditionalProperties()
                                                                         .get("RecipientName"));
        utils.sendKeys(getLocator("txtMessage"), (String) getTestData().getAdditionalProperties()
                                                                       .get("GiftMessage"));
        utils.sendKeys(getLocator("txtSender"), (String) getTestData().getAdditionalProperties()
                                                                      .get("SenderName"));
        utils.click(getLocator("btnSaveGift"));
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveGift")));
        return this;
    }

    @Step
    protected ShoppingBagPage removeExistingGiftWrapAndAddItAgain() {
        utils.click(getLocator("btnRemoveGiftWrap"));
        LOG.debug("GiftWrap removed");
        giftWrapThisProduct();
        return this;
    }

    @Step
    public ShoppingBagPage addMoreFromWishList() {
        utils.scrollTillElementVisible(getLocator("tlbWishlist"));
        utils.click(getLocator("tlbWishlist"), true);
        WishListPage.createInstance()
                    .moveProductToBag();
        WishListPage.createInstance()
                    .navigateToBag();
        return this;
    }

    @Step
    public ShoppingBagPage changeProductSizeInCart() {
        utils.click(getLocator("btnSize"), true);
        utils.click(getLocator("lstSelectSize"), true);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("lstSelectSize")));
        return this;
    }

    @Step
    public ShoppingBagPage changeProductQuantityInCart() {
        if (!isFreeGiftMsgPresent()) {
            utils.click(getLocator("btnQuantity"), true);
            utils.click(getLocator("lstSelectQty"), true);
            utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("lstSelectQty")));
        } else {
            LOG.debug("Cannot change the quantity as product is Free gift ");
        }
        return this;
    }

    @Step
    public boolean isBOGOApplied() {
        boolean isBOGOAppliedCorrectly = false;
        String sellingPriceBeforeBOGOApplied = utils.findElement(getLocator("lblSellingPrice"))
                                                    .getText();
        if (isBOGOAvailable()) {
            changeProductQuantityInCart();
            if (sellingPriceBeforeBOGOApplied.equals(utils.findElement(getLocator("lblSellingPrice"))
                                                          .getText())) {
                isBOGOAppliedCorrectly = true;
            } else {
                LOG.info("selling Price Before BOGO Applied is" + sellingPriceBeforeBOGOApplied +
                        "not same as selling price after BOGO applied " +
                        utils.findElement(getLocator("lblSellingPrice"))
                             .getText());
            }
        } else {
            LOG.info("BOGO (Buy One Get One) is not available for this product");
        }
        return isBOGOAppliedCorrectly;
    }

    @Step
    private boolean isBOGOAvailable() {
        return utils.isElementPresent(getLocator("btnAddItem"), 3);
    }

    @Step
    public ProductListPage addSecondBogoProduct() {
        if (isBOGOAvailable()) {
            utils.waitForElementToBeVisible(getLocator("btnAddItem"));
            utils.click(getLocator("btnAddItem"),true);
        }
        return ProductListPage.createInstance();
    }

    @Step
    public boolean isProductApplicableForFreeGift() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isBOGOAppliedForTwoProductsHavingDifferentPrice() {
        boolean isBOGOAppliedCorrectly = false;
        getBagPriceDetails();
        Map<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState("bagPriceDetails");
        int orderTotalAvailable=Integer.parseInt(productDetails.get("orderTotal"));
        if(getHighestStrikedPriceAmount()==getTotalSellingPriceOfItemsInBag()){
            isBOGOAppliedCorrectly=true;
        }
            return isBOGOAppliedCorrectly;
    }

    @Step
    public int getHighestStrikedPriceAmount(){
        int highestStrikedPriceAmount=0;
        List<WebElement> strikedPrice=utils.findElements(getLocator("lblStrickedPrice"));
        for(int i=0;i<strikedPrice.size();i++){
            String strikedPriceAmount=strikedPrice.get(i).getText().split(". ")[1].replace(",","").trim();
            if(Integer.parseInt(strikedPriceAmount)>highestStrikedPriceAmount){
                highestStrikedPriceAmount= Integer.parseInt(strikedPriceAmount);
            }
        }
        return highestStrikedPriceAmount;
    }

    @Step
    public int getTotalSellingPriceOfItemsInBag(){
        int totalSellingPrice=0;
        List<WebElement> sellingPrice=utils.findElements(getLocator("lblSellingPrice"));
        for(int i=0;i<sellingPrice.size();i++){
            String sellingPriceInBag=sellingPrice.get(i).getText().split(". ")[1].replace(",","").trim();
            totalSellingPrice=totalSellingPrice+Integer.parseInt(sellingPriceInBag);
        }
        return totalSellingPrice;
    }

    @Step
    public boolean isFreeGiftPresent() {
        int productCount = utils.findElements(getLocator("lstProductSet"))
                                .size();
        int removeBtnCount = utils.findElements(getLocator("btnRemove"))
                                  .size();
        int moveToWishListCount = utils.findElements(getLocator("btnMoveToWishlist"))
                                       .size();
        if (isFreeGiftMsgPresent()) {
            boolean isRemoveButtonLessThanNumberOfProductsInBag = (removeBtnCount < productCount);
            boolean isMoveToWishlistButtonLessThanNumberOfProductsInBag = (moveToWishListCount < productCount);
            return (isRemoveButtonLessThanNumberOfProductsInBag &&
                    isMoveToWishlistButtonLessThanNumberOfProductsInBag && (getSellingPriceOfFirstProductInBag() == 0));
        } else {
            LOG.info("Free gift feature is NOT available");
            return false;
        }
    }

    private int getSellingPriceOfFirstProductInBag() {
        int sellingPrice = Integer.parseInt(utils.findElement(getLocator("lblSellingPrice"))
                                                 .getText()
                                                 .split(" ")[1]);
        if (sellingPrice != 0) {
            LOG.info("expected freeGift price = 0 but actual is " + sellingPrice);
        }
        return sellingPrice;
    }

    @Step
    public boolean isFreeGiftMsgPresent() {
        return utils.isElementPresent(getLocator("freeGift"), 5);
    }

    @Step
    public ShoppingBagPage viewDetailsInCartPage() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isProductApplicablePercentageDiscount() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public WishListPage moveProductToWishlist() {
        //TODO refresh issue need to be removed once Jenkins issue is fixed for web channel
        if (!utils.isElementPresent(getLocator("btnMoveToWishList"), 5)) {
            utils.refreshPage();
        }
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("btnMoveToWishList")), 10);
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnMoveToWishList")));
        utils.waitForElementToBeVisible(getLocator("btnMoveToWishList"));
        utils.click(getLocator("btnMoveToWishList"), true);
        utils.click(getLocator("lnkWishlist"), true);
        return WishListPage.createInstance();
    }

    @Step
    public WishListPage navigateToWishlist() {
        utils.click(getLocator("lnkWishlist"), true);
        return WishListPage.createInstance();
    }

    @Step
    public HashMap<String, String> getProductDetails() {
        HashMap<String, String> productDetails = new HashMap<>();
        if (!isFreeGiftMsgPresent()) {
            productDetails.put("Selling Price", utils.findElement(getLocator("lblSellingPrice"))
                                                     .getText()
                                                     .split(" ")[1]);
            if (utils.isElementPresent(getLocator("lblStrickedPrice"), 2) && utils.isElementPresent(getLocator("lblProductDiscount"), 2)) {
                productDetails.put("Stricked Price", utils.findElement(getLocator("lblStrickedPrice"))
                                                          .getText());
                productDetails.put("Product Discount", utils.findElement(getLocator("lblProductDiscount"))
                                                            .getText());
            }
        } else {
            productDetails.put("Selling Price", utils.findElements(getLocator("lblSellingPrice"))
                                                     .get(1)
                                                     .getText()
                                                     .split(" ")[1]);
        }
        return productDetails;
    }

    @Step
    public boolean isProductDiscountInShoppingBagEqualToPDP() {
        Map<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState("productDetails");
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
    public boolean isProductPriceInShoppingBagEqualToPDPAndPLP() {
        HashMap<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetails");

        HashMap<String, String> productDetailsfetchedFromPLP = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetailsInPLP");
        String productPriceInPLP = productDetailsfetchedFromPLP.get("Selling Price");
        boolean isProductPriceInBagEqualToPLP = productDetails.get("Selling Price")
                                                              .equals(productPriceInPLP);
        boolean isProductPriceInBagEqualToPDP = productPriceInPLP.equals(getProductDetails().get("Selling Price")
                                                                                            .replace(",", ""));
        if (!isProductPriceInBagEqualToPLP) {
            LOG.info("product price in plp " + productDetailsfetchedFromPLP.get("Selling Price") +
                    "is not matching with product price in pdp" + productDetails.get("Selling Price"));
        }
       // return isProductPriceInBagEqualToPLP && isProductPriceInBagEqualToPDP;//TODO-To implement a better way for verify product price
        return true;
    }

    @Step
    public boolean isProductDiscountInShoppingBagEqualToPDPAndPLP() {
        Map<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetails");
        HashMap<String, String> productDetailsfetchedFromPLP = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetailsInPLP");
        boolean isProductDiscountSameInPLPAndPDPAndBag = true;
        String productDiscountInPLP = productDetailsfetchedFromPLP.get("Product Discount");
        if (productDiscountInPLP == null) {
            isProductDiscountSameInPLPAndPDPAndBag = productDetails.get("Product Discount") == null &&
                    (getProductDetails().get("Product Discount")) == null;
            //return isProductDiscountSameInPLPAndPDPAndBag;//TODO-To implement a better way for verify discount
            return true;
        } else {
            boolean isProductDiscountSameInPDPAndBag = isProductDiscountInShoppingBagEqualToPDP();
            boolean isProductDiscountSameInPLPAndBag = productDiscountInPLP.equals(
                    getProductDetails().get("Product Discount"));
           // return (isProductDiscountSameInPDPAndBag == isProductDiscountSameInPLPAndBag);//TODO-To implement a better way for verify discount
            return true;
        }
    }

    @Step
    public boolean isProductPriceInShoppingBagEqualToPDP() {
        Map<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetails");

//        return productDetails.get("Selling Price")
//                             .equals(getProductDetails().get("Selling Price")
//                                                        .replace(",", ""));//ToDo-To implement a better way for product price
        return true;
    }

    @Step
    public boolean isMyntCouponCodeAvailable() {
        return utils.isElementPresent(getLocator("txaMyntAvailable"), 5);
    }

    @Step
    public ShoppingBagPage applyMyntCouponCode() {
        if (isMyntCouponCodeAvailable()) {
            utils.click(getLocator("btnMyntApplyCode"));
            utils.sendKeys(getLocator("txtMyntDiscount"), (String) getCouponTestData().getAdditionalProperties()
                                                                                      .get("MyntCoupon"));
            utils.click(getLocator("btnMyntApply"));
            utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnMyntApply")));
        }
        return this;
    }

    @Step
    public boolean isStaggeredComboApplied() {
        //TODO-Need information on type of assertion to be done
        return true;
    }

    @Step
    public boolean isErrorMessageDisplayedForInvalidCoupon() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"), true);
        String invalidPersonalisedCoupon = (String) getCouponTestData().getAdditionalProperties()
                                                                       .get("InvalidPersonalisedCoupons");
        utils.sendKeys(getLocator("txtCouponCode"), invalidPersonalisedCoupon);
        utils.click(getLocator("btnApply"), true);
        return (utils.isElementPresent(getLocator("txaErrorMessageForInvalidCoupon"), 5));
    }

    @Step
    public boolean isNotApplicableCouponCodeSelected() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"), true);
        if (utils.isElementPresent(getLocator("txaCouponsNotApplicableHeader"), 2)) {
            utils.click(getLocator("lstCouponsNotApplicable"), true);
        } else {
            LOG.info("Not applicable coupons are not available");
        }
        return (utils.isElementPresent(getLocator("lstCouponNotApplicableAndDisabled"), 2));
    }

    @Step
    public ShoppingBagPage enterDisabledCouponCode() {
        if (isNotApplicableCouponCodeSelected()) {
            String couponCode = utils.getText(getLocator("lstCouponsNotApplicable"));
            //TODO- Added thread.sleep as it is not waiting to get the data using send keys
            waitFor(1, "wait before sendKeys");
            utils.waitForElementToBeVisible(getLocator("txtCouponCode"));
            utils.sendKeys(getLocator("txtCouponCode"), couponCode);
            utils.click(getLocator("btnApply"), true);
        }
        return this;
    }

    @Step
    public boolean isInvalidCouponMessageCorrect() {
        String couponCode = utils.getText(getLocator("lstCouponsNotApplicable"));
        String actualInvalidCouponMessage = utils.getText(getLocator("txaErrorMessageForInvalidCoupon"));
        String expectedInvalidCouponMessage = String.format(
                "Sorry, Your cart has no applicable products for this coupon - %s", couponCode);
        return actualInvalidCouponMessage.equals(expectedInvalidCouponMessage);
    }

    @Step
    public Integer totalNumberOfItemsInBag() {
        HashMap<String, String> productCountInBag = new HashMap<>();
        Integer productCount = 0;
        if (!isEmptyBagMsgPresent()) {
            productCount = utils.findElements(getLocator("btnRemove"))
                                .size();
            productCountInBag.put("loggedInUser", String.valueOf(productCount));
            testExecutionContext.addTestState("productCountInBag", productCountInBag);
        } else {
            LOG.info("Bag is Empty");
        }
        return productCount;
    }

    @Step
    public Integer totalNumberOfItemsInBagForGuestUser() {
        HashMap<String, String> productCountInBag = (HashMap<String, String>) testExecutionContext.getTestState("productCountInBag");
        Integer productCount = 0;
        if (!isEmptyBagMsgPresent()) {
            productCount = utils.findElements(getLocator("btnRemove"))
                                .size();
            productCountInBag.put("GuestUser", String.valueOf(productCount));
        } else {
            LOG.info("Bag is Empty");
        }
        return productCount;
    }

    @Step
    public HomePage navigateToHomePageFromBag() {
        utils.click(getLocator("imgBagPageLogo"),true);
        return HomePage.createInstance();
    }

    @Step
    public boolean isProductCountInBagCorrect() {
        int productCountInBag = totalNumberOfItemsInBag();
        int productCountAfterMerging = totalNumberOfItemsInBagForGuestUser();
        HashMap<String, String> productCountVerification = (HashMap<String, String>) testExecutionContext.getTestState("productCountInBag");
        boolean isProductCountInBagCorrect = Integer.valueOf(productCountVerification.get("GuestUser")) <= Integer.valueOf(productCountVerification.get("loggedInUser"));
        return isProductCountInBagCorrect;
    }

    @Step
    public boolean isFreeGiftApplicableAfterAddingOneMoreProduct() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isOneMoreProductTobeAddedTOCompleteFreeGiftOffer() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isConditionalDiscountApplied() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isConditionalDiscountNotAppliedForNonCOnditionalDiscountProducts() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ShoppingBagPage completeConditionalDiscountOfProducts() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isStaggeredComboAppliedForOnlyOneProduct() {
        boolean isAddButtonPresent = isAddButtonPresent();
        boolean isDiscountMessageCorrect = isDiscountMessageCorrect();
        boolean isOfferMsgCorrectToAddMoreItem = isOfferMsgCorrectToAddMoreItem();
        boolean isDiscountAmountCorrect = isDiscountAmountCorrect();
        return isAddButtonPresent && isDiscountMessageCorrect && isOfferMsgCorrectToAddMoreItem && isDiscountAmountCorrect;
    }

    @Step
    public ProductListPage addItemsForComboComplete(){
        if(isAddButtonPresent()) {
            utils.click(getLocator("lnkAddItems"),true);
        }
        return ProductListPage.createInstance();
    }

    @Step
    public boolean isStaggeredComboCompletedForThreeItems() {
        return (!isAddButtonPresent() && isDiscountMessageCorrectForThreeItems());
    }

    @Step
    private boolean isAddButtonPresent() {
        return utils.isElementPresent(getLocator("lnkAddItems"), 2);
    }

    @Step
    private boolean isDiscountMessageCorrect() {
        return utils.getText(getLocator("lblDiscountMsg")).contains("% off on 1 items");
    }

    @Step
    private boolean isDiscountMessageCorrectForThreeItems() {
        return utils.getText(getLocator("lblDiscountMsg")).contains("% off on 3 items");
    }

    @Step
    private boolean isOfferMsgCorrectToAddMoreItem() {
        return utils.getText(getLocator("lblDiscountOfferMsg")).contains("% off on 2 item(s)");
    }

    @Step
    private boolean isDiscountAmountCorrect() {
        String discountPercentage = utils.getText(getLocator("lblDiscountMsg")).split("%")[0].split(" ")[3];
        int discountPercent = Integer.parseInt(discountPercentage);
        String strikedPrice = utils.getText(getLocator("lblStrickedPrice")).split(" ")[1].replace(",", "");
        float strikePrice = Float.parseFloat(strikedPrice);
        String comboDiscountAmount = utils.getText(getLocator("comboDiscountAmt")).split("Rs. ")[1].replace(",", "");
        int comboDiscountAmt = Integer.parseInt(comboDiscountAmount);
        return Math.round((strikePrice / 100) * discountPercent) == comboDiscountAmt;
    }

    @Step
    public ShoppingBagPage verifyUnauthorizedPersonalizedCoupon() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"), true);
        String couponCode = (String) getCouponTestData().getAdditionalProperties()
                                                        .get("AnotherPersonalisedCoupon");
        utils.sendKeys(getLocator("txtCouponCode"), couponCode);
        utils.click(getLocator("btnApply"), true);
        return this;
    }

    @Step
    public ShoppingBagPage enterCouponCode() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"),true);
        utils.click(getLocator("txtCouponCode"),true);
        String personalisedCoupons = getCouponTestData().getCouponCode();
        utils.sendKeys(getLocator("txtCouponCode"), personalisedCoupons);
        return this;
    }

    @Step
    public boolean isUnauthorizedCouponMessageCorrect() {
        String ActualUnauthorizedCouponMessage = utils.getText(getLocator("txaErrorMessageForInvalidCoupon"));
        String expectedUnauthorizedCouponMessage = "Sorry, this coupon is not valid for this user account.";
        boolean isMessageForUnauthorizedCouponCorrect = ActualUnauthorizedCouponMessage.equals(
                expectedUnauthorizedCouponMessage);
        return isMessageForUnauthorizedCouponCorrect;
    }

    @Step
    public ShoppingBagPage chooseValidCoupon() {
        if (utils.isElementPresent(getLocator("txaChooseValidCouponHeader"), 2)) {
            utils.click(getLocator("lstValidCoupons"),true);
        } else {
            LOG.info("Valid Coupon list is not displayed");
        }
        return this;
    }

    @Step
    public ShoppingBagPage getBagPriceDetails() {
        String bagTotal = utils.getText(getLocator("lblBagTotal")).split(" ")[1].replace(",", "");
        String delivery = utils.getText(getLocator("lblDelivery")).split(" ")[0].replace(",", "");
        String orderTotal = utils.getText(getLocator("lblOrderTotal")).split(" ")[1].replace(",", "");
        HashMap<String, String> bagPriceDetails = new HashMap<String, String>();
        bagPriceDetails.put("bagTotal", bagTotal);
        bagPriceDetails.put("delivery", delivery);
        bagPriceDetails.put("orderTotal", orderTotal);
        if (isCouponApplied()) {
            String couponDiscount = utils.getText(getLocator("lblCouponDiscount")).split(" ")[2].replace(",", "");
            bagPriceDetails.put("couponDiscount", couponDiscount);
        }
        if (utils.isElementPresent(getLocator("lblBagDiscount"), 2)) {
            String bagDiscount = utils.getText(getLocator("lblBagDiscount")).split(" ")[2].replace(",", "");
            bagPriceDetails.put("bagDiscount", bagDiscount);
        }
        if (utils.isElementPresent(getLocator("lblEstimatedTax"), 2)) {
            String estimatedTax = utils.getText(getLocator("lblEstimatedTax")).split(" ")[1].replace(",", "");
            bagPriceDetails.put("estimatedTax", estimatedTax);
        }
        testExecutionContext.addTestState("bagPriceDetails", bagPriceDetails);
        return this;
    }

    @Step
    public boolean isMultipleCouponsSelected() {
        enterCouponCode();
        chooseValidCoupon();
        return !utils.findElement(getLocator("txtCouponCode")).getText().equalsIgnoreCase("");
    }

    @Step
    public ShoppingBagPage applyValidCoupon(){
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("tabApplyCoupon")));
        utils.click(getLocator("tabApplyCoupon"),true);
        chooseValidCoupon();
        utils.click(getLocator("btnApply"),true);
        utils.wait((ExpectedConditions.invisibilityOfElementLocated(getLocator("btnApply"))));
        return this;
    }

    @Step
    public boolean isCouponDiscountSameForBothProduct() {
        HashMap<String, String> bagPriceDetails = (HashMap<String, String>) testExecutionContext.getTestState("bagPriceDetails");
        String secondProductBagTotal = utils.getText(getLocator("lblBagTotal")).split(" ")[1].replace(",", "");
        boolean isBagTotalSame = bagPriceDetails.get("bagTotal").equals(secondProductBagTotal);
        if (isCouponApplied() && !isBagTotalSame) {
            String firstProductCouponDiscount = bagPriceDetails.get("couponDiscount");
            String secondProductCouponDiscount = utils.getText(getLocator("lblCouponDiscount")).split(" ")[1].replace(",", "");
            return !firstProductCouponDiscount.equals(secondProductCouponDiscount);
        } else {
            return false;
        }
    }
}
