package com.myntra.core.business;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.LoginPage;
import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class ShoppingBag extends BusinessFlow {

    public static ShoppingBag getInstance() {
        return (ShoppingBag) getInstance(Of.SHOPPING_BAG);
    }

    @Step
    public Order provideAddressAndPlaceOrderUsingCOD() {
        return Order.getInstance();
    }

    @Step
    public Payment applyCouponAndPlaceOrderWithNewAddress() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            Assert.assertTrue(shoppingBagPage.isProductPresentInBag(), "Product added is NOT found in shopping bag");
            shoppingBagPage.applyPersonalisedCoupon();
            Assert.assertTrue(shoppingBagPage.isCouponApplied(), "Coupon NOT applied successfully");
        }
        boolean productDeliverable = shoppingBagPage.placeOrder()
                                                    .addAddress()
                                                    .isProductDeliverable();
        Assert.assertTrue(productDeliverable, "Selected pincode is not available for delivery");
        AddressPage.createInstance()
                   .addressContinue();

        return Payment.getInstance();

    }

    @Step
    public ShoppingBag moveToWishlistAndAddFromWishlist() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBagPage.isProductPresentInBag(), "Product added is NOT found in shopping bag");
        shoppingBagPage.moveProductToWishlist()
                       .moveProductToBag()
                       .navigateToBag();
        return this;
    }

    @Step
    public Payment applyCouponAndPlaceOrderRemoveAddressAddNewAddress() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance()
                                                         .applyPersonalisedCoupon();
        Assert.assertTrue(shoppingBagPage.isCouponApplied(), "Coupon NOT applied successfully");
        boolean productDeliverable = shoppingBagPage.placeOrder()
                                                    .addAddress()
                                                    .removeAddress()
                                                    .addAddress()
                                                    .isProductDeliverable();
        Assert.assertTrue(productDeliverable, "Selected pincode is not available for delivery");
        AddressPage.createInstance()
                   .addressContinue();
        return Payment.getInstance();
    }

    @Step
    public Payment placeOrderAndEditAddress() {
        return placeOrderAndAddAddress().editAddressAndProceedToPayment();
    }

    @Step
    public Address placeOrderAndAddAddress() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBagPage.isProductPresentInBag(), "Product added is NOT found in shopping bag");
        boolean productDeliverable = shoppingBagPage.placeOrder()
                                                    .addAddress()
                                                    .isProductDeliverable();
        Assert.assertTrue(productDeliverable, "Selected pincode is not available for delivery");
        return Address.getInstance();
    }

    @Step
    public Payment placeOrderAddAddressAndContinueToPayment() {
        placeOrderAndAddAddress();
        AddressPage.createInstance()
                   .addressContinue();
        return Payment.getInstance();
    }

    @Step
    public ShoppingBag addMoreFromWishlist() {
        ShoppingBagPage.createInstance()
                       .addMoreFromWishList();
        return this;
    }

    @Step
    public ShoppingBag removeProductFromCart() {
        ShoppingBagPage.createInstance()
                       .removeItemFromCart();
        return this;
    }

    @Step
    public ShoppingBag emptyTheCartAndAddProductsFromWishlist() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBagPage.isProductPresentInBag(), "Product added is NOT found in shopping bag");
        shoppingBagPage.removeItemFromCart()
                       .navigateToWishlist()
                       .moveProductToBag()
                       .navigateToBag();
        return this;
    }

    @Step
    public ShoppingBag verifyFreeGift() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
            Assert.assertTrue(shoppingBagPage.isFreeGiftPresent(), "Free Gift details are not available or incorrect");
        }
        return this;
    }

    @Step
    public ShoppingBag changeSizeQuantityOfProductInCart() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBagPage.isProductPresentInBag(), "Product added is NOT found in shopping bag");
        shoppingBagPage.changeProductSizeInCart()
                       .changeProductQuantityInCart();
        return this;
    }

    @Step
    public Address applyCouponAndAddNewAddress() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            shoppingBagPage.applyPersonalisedCoupon();
            Assert.assertTrue(shoppingBagPage.isCouponApplied(), "Coupon NOT applied successfully");
        }
        Assert.assertTrue(shoppingBagPage.isProductPresentInBag(), "Product added is NOT found in shopping bag");
        boolean productDeliverable = shoppingBagPage.placeOrder()
                                                    .addAddress()
                                                    .isProductDeliverable();
        Assert.assertTrue(productDeliverable, "Selected pincode is not available for delivery");
        return Address.getInstance();
    }

    @Step
    public Payment applyCouponAndAddNewAddressAndEditAddress() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            shoppingBagPage = ShoppingBagPage.createInstance()
                                             .applyPersonalisedCoupon();
            Assert.assertTrue(shoppingBagPage.isCouponApplied(), "Coupon NOT applied successfully");
        }
        AddressPage addressPage = shoppingBagPage.placeOrder()
                                                 .addAddress();
        Assert.assertTrue(addressPage.isProductDeliverable(), "Selected pincode is not available for delivery");
        addressPage.editAddress()
                   .addressContinue();
        return Payment.getInstance();
    }

    @Step
    public ShoppingBag giftWrapThisProduct() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ShoppingBagPage shoppingBag = ShoppingBagPage.createInstance()
                                                         .addGiftWrap();
            Assert.assertTrue(shoppingBag.isGiftWrapAdded(), "Failed to Giftwrap the item/items");
        }
        return this;
    }

    @Step
    public ShoppingBag verifyBOGO() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
            Assert.assertTrue(shoppingBagPage.isBOGOApplied(), "BOGO is not available or incorrect");
        }
        return this;
    }

    @Step
    public ShoppingBag verifyBOGOForTwoProductsHavingDifferentPriceInShoppingBag() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBagPage.isBOGOAppliedForTwoProductsHavingDifferentPrice(),
                "BOGO for two products having different price is not available/incorrect");
        return this;
    }

    @Step
    public ShoppingBag verifyConditionalDiscountForNotApplicableConditionlDiscountProductsInShoppingBag() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBagPage.isConditionalDiscountNotAppliedForNonCOnditionalDiscountProducts(),
                "Conditional discount is applicable for this product");
        return this;
    }

    @Step
    public ShoppingBag applyMyntCoupon() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
            Assert.assertTrue(shoppingBagPage.isMyntCouponCodeAvailable(), "Mynt Coupon Code is not available");
            shoppingBagPage.applyMyntCouponCode();
        }
        return this;
    }

    @Step
    public ShoppingBag verifyProductPriceInShoppingBagAndCompareWithPDPAndPLP() {
        ShoppingBagPage shoppingBag = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBag.isProductPriceInShoppingBagEqualToPDPAndPLP(),
                "Product price available in Shopping Bag Page is not equal to Product Price available in PDP and PLP");
        return this;
    }

    @Step
    public ShoppingBag verifyProductPriceInShoppingBagAndCompareWithPDP() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ShoppingBagPage shoppingBag = ShoppingBagPage.createInstance();
            Assert.assertTrue(shoppingBag.isProductPriceInShoppingBagEqualToPDP(),
                    "Product price available in Shopping Bag Page is not equal to Product Price available in PDP");
        }
        return this;
    }

    @Step
    public ShoppingBag verifyProductDiscountInShoppingBagAndCompareWithPDP() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ShoppingBagPage shoppingBag = ShoppingBagPage.createInstance();
            soft.assertTrue(shoppingBag.isProductDiscountInShoppingBagEqualToPDP(),
                    "Discount available in Shopping Bag Page is not equal to Discount available in PDP");
        }
        return this;
    }

    @Step
    public ShoppingBag verifyProductDiscountInShoppingBagAndCompareWithPDPAndPLP() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ShoppingBagPage shoppingBag = ShoppingBagPage.createInstance();
            soft.assertTrue(shoppingBag.isProductDiscountInShoppingBagEqualToPDPAndPLP(),
                    "Discount available in Shopping Bag Page is not equal to Discount available in PDP And PLP");
        }
        return this;
    }

    @Step
    public ShoppingBag verifyStaggeredCombo() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            Assert.assertTrue(shoppingBagPage.isStaggeredComboApplied(), "Staggered Combo is not available or incorrect");
        }
        return this;
    }

    @Step
    public User placeOrderAsGuestUser() {
        ShoppingBagPage.createInstance()
                       .placeOrder();
        Assert.assertTrue(LoginPage.createInstance()
                                   .isLoginOptionDisplayed(), "Login page did not displayed");
        return new User();
    }

    @Step
    public ShoppingBag verifyEmptyCart() {
        Assert.assertFalse(ShoppingBagPage.createInstance()
                                          .isProductPresentInBag(), "Items are available in Bag");
        return this;
    }

    @Step
    public ShoppingBag verifyErrorMessageForInvalidCouponCode() {
        Assert.assertTrue(ShoppingBagPage.createInstance()
                                         .isErrorMessageDisplayedForInvalidCoupon(), "Appropriate error message for invalid coupon is not displayed");
        return this;
    }

    @Step
    public ShoppingBag verifyNotApplicableCouponIsNotAbleToSelect() {
        Assert.assertTrue(ShoppingBagPage.createInstance()
                                         .isNotApplicableCouponCodeSelected(), "Not applicable coupons are enabled");
        return this;
    }

    @Step
    public ShoppingBag verifyNotApplicableCouponIsNotAbleToEnter() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        boolean isNotApplicableCouponAvailable = shoppingBagPage.isNotApplicableCouponCodeSelected();
        soft.assertTrue(isNotApplicableCouponAvailable, "Not applicable coupons are enabled");
        boolean isInvalidCouponMessageCorrect = shoppingBagPage.enterDisabledCouponCode()
                                                               .isInvalidCouponMessageCorrect();
        soft.assertTrue(isInvalidCouponMessageCorrect, "Error message for disabled coupon is not correct");
        return this;
    }

    @Step
    public ShoppingBag productCountInBag() {
        ShoppingBagPage.createInstance()
                       .totalNumberOfItemsInBag();
        return this;
    }

    @Step
    public ShoppingBag verifyItemsMerged() {
        Assert.assertTrue(ShoppingBagPage.createInstance()
                                         .isProductCountInBagCorrect(), "Items are not merged in Cart Page");
        return this;
    }

    @Step
    public Home naviagteToHome() {
        ShoppingBagPage.createInstance()
                       .navigateToHomePageFromBag();
        return Home.getInstance();
    }

    @Step
    public ShoppingBag verifyFreeGiftApplicableAfterAddingOneMoreProduct() {
        Assert.assertTrue(ShoppingBagPage.createInstance()
                                         .isFreeGiftApplicableAfterAddingOneMoreProduct(),
                "Free Gift is not Applicable after adding One More Product,Combo Fail");
        return this;
    }

    @Step
    public ShoppingBag verifyOneMoreProductTobeAddedTOCompleteFreeGiftOffer() {
        Assert.assertTrue(ShoppingBagPage.createInstance()
                                         .isOneMoreProductTobeAddedTOCompleteFreeGiftOffer(),
                "Free Gift is Applicable without adding One More Product,Combo Fail");
        return this;
    }

    @Step
    public ShoppingBag completeAndVerifyConditionalDiscount() {
        Assert.assertTrue(ShoppingBagPage.createInstance()
                                         .isOneMoreProductTobeAddedTOCompleteFreeGiftOffer(),
                "Free Gift is Applicable without adding One More Product,Combo Fail");
        return this;
    }

    @Step
    public ShoppingBag verifyPersonalizedCouponForOtherUser() {
        boolean isUnauthorizedCouponMessageCorrect = ShoppingBagPage.createInstance()
                                                                    .verifyUnauthorizedPersonalizedCoupon()
                                                                    .isUnauthorizedCouponMessageCorrect();
        soft.assertTrue(isUnauthorizedCouponMessageCorrect, "Unauthorized coupon message is not valid");
        return this;
    }

    @Step
    public ShoppingBag enterCouponCodeAndChooseCouponFromValidCoupons() {
        Assert.assertFalse(ShoppingBagPage.createInstance()
                                          .isMultipleCouponsSelected(), "User is able to select multiple coupons");
        return this;
    }

    @Step
    public Search applyCouponAndGetCouponDetails() {
        ShoppingBagPage.createInstance()
                       .applyPersonalisedCoupon()
                       .getBagPriceDetails();
        return Search.getInstance();
    }

    @Step
    public ShoppingBag applyCouponAndCompareCapAmountWithPreviousProduct() {
        boolean isCouponDiscountSameForBothProduct = ShoppingBagPage.createInstance()
                                                                    .applyPersonalisedCoupon()
                                                                    .isCouponDiscountSameForBothProduct();
        soft.assertTrue(isCouponDiscountSameForBothProduct, "The cap amount should be different");
        return this;
    }

    @Step
    public ShoppingBag applyCouponForSignUpAccount() {
        ShoppingBagPage.createInstance()
                       .applyPersonalisedCouponForSignUpAccount();
        return this;
    }

    @Step
    public Address applyCouponAndGetCouponDetailsPlaceOrder() {
        ShoppingBagPage.createInstance()
                       .applyValidCoupon()
                       .getBagPriceDetails()
                       .placeOrder();
        return Address.getInstance();
    }

    @Step
    public ShoppingBag verifyStaggeredComboWhenOnlyOneProductIsAdded() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        Assert.assertTrue(shoppingBagPage.isStaggeredComboAppliedForOnlyOneProduct(),
                "Staggered Combo for one product is not available or incorrect");
        return this;
    }

    @Step
    public ShoppingBag verifyStaggeredComboWhenComboComplete() {
        ShoppingBagPage shoppingBagPage = ShoppingBagPage.createInstance();
        shoppingBagPage.addItemsForComboComplete()
                       .addSecondItemToBag()
                       .navigateToBag()
                       .addItemsForComboComplete()
                       .addThirdItemToBag()
                       .navigateToBag();
        Assert.assertTrue(shoppingBagPage.isStaggeredComboCompletedForThreeItems(), "Staggered Combo is not completed or incorrect");
        return this;
    }
}
