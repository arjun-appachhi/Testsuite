package com.myntra.scenarios;

import com.myntra.core.business.Home;
import org.testng.annotations.Test;

public class CCDCTest extends SupportTest {
    @Test(groups = {"CCDC", "na-ios"},
          description = "TC_ID:2786 - Search, Check for Delivery Option, View similar products, Add Product, Apply Coupon, New Address Office, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void checkDeliveryOptionAddProductToCartPlaceOrderApplyCouponAddNewAddressOfficePayUsingCreditDebitCard() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .checkDeliveryAvailability()
                            .viewSimilarProducts()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"CCDC", "prod"},
          description = "TC_ID:2010 - Search, Add to Bag, Change Size & Quantity, Free Gift, View Details, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void addToBagSizeQuantityInCartVerifyFreeGiftViewDetailsPayUsingCreditCard() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .addProductFromPDPToShoppingBag()
                            .changeSizeQuantityOfProductInCart()
                            .verifyFreeGift()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"CCDC", "na-ios"},
          description = "TC_ID:1241 - Search, View Similar products, Percentage Discount, Size Chart, Add to Bag, Move to Wishlist, Move to Bag, Add from wishlist, Add New Address, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void sizeChartAddToBagMoveToWishlistMoveToBagAddNewAddressPayUsingCCDC() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSimilarProducts()
                            .viewSizeChartAndSelectSizeAddToBagAndGoToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .moveToWishlistAndAddFromWishlist()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }


    @Test(groups = {"CCDC", "na-ios"},
          description = "TC_ID:1737 - Verify contact us, Search, Add to Bag, Move to Bag, Verify BOGO, Add New Address, View details, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void selectSizeAddtoBagBOGOVIewDetailsPayUsingCCDC() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .selectSizeAddToBagAndMoveToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .verifyBOGO()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }


    @Test(groups = {"CCDC", "na-ios"},
          description = "TC_ID:2121 - Search, save, add product from wishlist, Show similar products, Change size and quantity, place order,  Add New Address, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void similarProductsSaveAddToBagFromWishlistClickForBestPriceChangeSizeAndQuantityAddAddressPayUsingCCDC() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSimilarProducts()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .changeSizeQuantityOfProductInCart()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"CCDC", "prod"},
          description = "TC_ID:2158 - Search, save, add product from wishlist,Conditional Discount, place order,  Add New Address-Home, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void saveAddToBagMovetoBagFromWishlistPlaceOrderPercentageDiscountAddNewAddressHomePayUsingCCDC() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromWishlistToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }


    @Test(groups = {"CCDC", "na-ios"},
          description = "TC_ID:3034 - Search,view similar products, Add to bag, gift wrap, Apply coupon, add new address, edit address place order,  Add New Address-Home, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void showSimilarProductsAddToBagGiftWrapApplyCouponEditAddressPayUsingSavedCard() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSimilarProducts()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .giftWrapThisProduct()
                            .applyCouponAndAddNewAddressAndEditAddress()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"PhaseTwo", "na-ios"},
          description = "TC_ID:61 - Place an order as guest user, registre to proceed further, pay using COD",
          alwaysRun = true)
    public void placeOrderAsGuestUserThenRegisterPayUsingCCDC() {
        Home.getInstance()
            .navigateToHomePage()
            .searchProductUsingStyleID()
            .addProductFromPDPToShoppingBag()
            .placeOrderAsGuestUser()
            .signUpSuccessfully()
            .addAddressAfterSignUp()
            .payUsingCreditDebit();
    }

    @Test(groups = {"PhaseTwo", "na-ios"},
          description = "TC_ID:265 - Login as Registered user, Search, Add To Bag, Place Order, Pay Using CCDC, Verify Empty Bag Page",
          alwaysRun = true)
    public void verifyEmptyBagAfterCheckout() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .addProductFromPDPToShoppingBag()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingCreditDebit()
                            .goToHomePageAfterOrderConfirmation()
                            .goToBagPage()
                            .verifyEmptyCart();
    }


    @Test(groups = {"CCDC", "na-ios"},
          description = "TC_ID:1718 - Home, Search, Save, Move to Bag,Conditional Discount, View Details, Pay using Credit/Debit Card",
          alwaysRun = true)
    public void homeSearchSaveMoveToBagConditionalDiscountViewDetailsCCDC() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingCreditDebit()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"PhaseTwo", "na-ios"},
          description = "TC_ID:56 - SignUp, Add Item To Bag, Place Order, Add New Address, Pay Using CCDC, Verify Order Confirmation",
          alwaysRun = true)
    public void signUpPlaceOrderUsingCCDCVerifyOrderConfirmation() {
        Home.getInstance()
            .signUpSuccessfully()
            .searchProductUsingStyleID()
            .verifyProductDetailsInPDP()
            .addProductFromPDPToShoppingBag()
            .verifyProductPriceInShoppingBagAndCompareWithPDP()
            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
            .applyCouponForSignUpAccount()
            .placeOrderAddAddressAndContinueToPayment()
            .payUsingCreditDebit()
            .verifyOrderIsConfirmed();
    }
}
