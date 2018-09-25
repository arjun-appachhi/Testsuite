package com.myntra.scenarios;

import org.testng.annotations.Test;

public class CashOnDeliveryTest extends SupportTest {

    @Test(groups = {"CashOnDelivery", "na-ios"},
          description = "TC_ID:2768 - Image Verification, Save To Wishlist, Tap For Best Price, Add to Bag, Empty Bag, Add Items From Wishlist, Place Order, Add New Address, View Price Details, Pay Using COD+Mynt",
          alwaysRun = true)
    public void imageVerificationSaveTapforBestPriceEmptyCartAddItemsFromWishlistViewPriceDetailsPayUsingCOD() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .navigateThroughImagesOfProduct()
                            .saveProductToWishlist()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .emptyTheCartAndAddProductsFromWishlist()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .applyMyntCoupon()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingCOD()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"CashOnDelivery", "na-ios"},
          description = "TC_ID:1792 -Search, Save Product to Wishlist, Check for Best Offer, Pay using Mynt+COD",
          alwaysRun = true)
    public void searchSaveToWishlistCheckBestPriceRemoveandAddAddressAnPayUsingCOD() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .viewSimilarProducts()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .applyMyntCoupon()
                            .placeOrderAndAddAddress()
                            .reEnterAddressAndProceedToPayment()
                            .payUsingCOD()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"CashOnDelivery", "na-ios"},
          description = "TC_ID:2453 - BUG - Search, Check for Delivery Option, Add product, Add More from Wishlist, Pay using Mynt+COD",
          alwaysRun = true)
    public void homeSearchMoveToBagAddMoreFromWishlistAddNewAddressOfficeMyntraCreditCOD() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .checkDeliveryAvailability()
                            .addProductFromPDPToShoppingBag()
                            .addMoreFromWishlist()
                            .verifyStaggeredCombo()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingCOD()
                            .verifyOrderIsConfirmed();
    }


    @Test(groups = {"CashOnDelivery", "na-ios"},
          description = "TC_ID:412 - Search, Save, Add To Bag, Add more from Wishlist, Place Order, Pay using COD",
          alwaysRun = true)
    public void imageVerificationSaveAddToBagAddMoreFromWishlistViewDetailsCOD() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .navigateThroughImagesOfProduct()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .addMoreFromWishlist()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingCOD()
                            .verifyOrderIsConfirmed();
    }


    @Test(groups = {"CashOnDelivery", "na-ios"},
          description = "TC_ID:944 - Search, Save, Empty Wishlist, Move to Bag, Place Order, Remove Address, Add New Address, Pay using COD",
          alwaysRun = true)
    public void saveEmptyWishlistMoveToBagPlaceOrderRemoveAddressAddAddressAndCOD() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .checkDeliveryAvailability()
                            .addProductFromWishlistToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .placeOrderAndAddAddress()
                            .reEnterAddressAndProceedToPayment()
                            .payUsingCOD()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"CashOnDelivery", "prod"},
          description = "TC_ID:1843 - Search, Size Chart, Add To Bag, Move To Wishlist, Move To Bag From Wishlist, Place Order, Add New Address, Pay using COD+Mynt",
          alwaysRun = true)
    public void sizeChartAddToBagMoveToWishlistMoveToBagFromWishlistBOGOViewDetailsPayUsingCOD() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .addProductFromPDPToShoppingBag()
                            .applyMyntCoupon()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .verifyBOGO()
                            .moveToWishlistAndAddFromWishlist()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingCOD()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"CashOnDelivery", "na-ios"},
          description = "TC_ID:2705 - Search, Save, Click For Best Price, View Similar Products, Go To Bag, Add More From Wishlist, Remove Address, Add Address, Pay Using Netbanking",
          alwaysRun = true)
    //TODO - Myntra Credit Testdata is not available
    public void saveClickForBestPriceShowSimilarProductsAddToBagAddMoreFromWishListRemoveAddressAddAddressPayUsingCODAndMyntraCredit() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .viewSimilarProducts()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .addMoreFromWishlist()
                            .placeOrderAndAddAddress()
                            .reEnterAddressAndProceedToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }
}
