package com.myntra.scenarios;

import org.testng.annotations.Test;

public class OnlineTest extends SupportTest {
    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1622 - Select Size, Verify product price, Apply Coupon, BOGO, Remove Address, Pay using Manual GC+Online",
          alwaysRun = true)
    public void addProductMoveToBagApplyCouponPlaceOrderRemoveAddressPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .verifyBOGO()
                            .applyCouponAndPlaceOrderRemoveAddressAddNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:3155 - Save To Wishlist, Empty Wishlist, Gift Wrap, EditAddress, Pay using LP+Online",
          alwaysRun = true)
    public void saveToWishlistEmptyWishlistGiftWrapEditAddressPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromWishlistToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .giftWrapThisProduct()
                            .placeOrderAndEditAddress()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2792 - Search, Show More Products, Similar Product, Select Size, Place Order Conditional Discount, Add Address_Home, Pay Using Net Banking",
          alwaysRun = true)
    public void searchaddProductToCartPlaceOrderConditionalDiscountAddNewAddressHomePayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSimilarProducts()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2967 - Save, Move to Bag, Apply Coupon, Free Gift, Add New Address_Office, Pay using Net Banking",
          alwaysRun = true)
    public void saveToWishlistAfterSearchAddFreeGiftApplycouponPlaceOrderUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromWishlistToShoppingBag()
                            .verifyFreeGift()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:68 - Save Product, Empty Wishlist, Gift Wrap, Apply Coupon, Pay using Net banking",
          alwaysRun = true)
    public void saveEmptyWishlistAddProductToBagGiftWrapApplyCouponPayUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .saveProductToWishlist()
                            .addProductFromWishlistToShoppingBag()
                            .giftWrapThisProduct()
                            .applyCouponAndAddNewAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1065 - Contact us, Save Product, Empty Wishlist, Gift Wrap, Apply Coupon, Pay using net banking",
          alwaysRun = true)
    public void searchSaveContactUsBuyOneGetOneAddNewAddressPayUsingNetbanking() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .viewSimilarProducts()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .verifyBOGO()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2635 - Add to bag, Move to Bag, Apply Coupon, Remove Address, Pay using Net banking ",
          alwaysRun = true)
    public void selectBestPriceAddProductToCartPlaceOrderApplyCouponRemoveAddressAddAddressPayUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .viewSimilarProducts()
                            .verifyProductDetailsInPDP()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .applyCouponAndPlaceOrderRemoveAddressAddNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2559 - Verify Product Details, View Size Chart, Apply Coupon, Click for Offer, View Details, Pay using Wallet",
          alwaysRun = true)
    public void verifyProductDetailsInPdpSizeChartApplyCouponViewPriceDetailsPayUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .verifyProductDetailsInPDP()
                            .viewSizeChartAndSelectSizeAddToBagAndGoToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .applyCouponAndAddNewAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2001 - Search, Save Product to Wishlist, Apply Coupon, Add New Address, Pay using Net Banking",
          alwaysRun = true)
    public void searchSaveToWishlistApplyCouponAddNewAddressAnPayUsingNetBanking() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:815 - Search, Save Product to Wishlist, Apply Coupon, Add New address, Pay using Net Banking",
          alwaysRun = true)
    public void searchSaveToWishlistCheckBestPriceApplyCouponAddNewAddressEditAddressVerifyFreeGiftAndPayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .applyCouponAndAddNewAddressAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1156 - Search, Save Product to Wishlist, Select Size Chart, Apply Coupon, Add New Address, Edit Address, Pay using Net Banking",
          alwaysRun = true)
    public void searchSaveProductSelectSizeChartApplyCouponVerifyFreeGiftAddAndEditAddressPayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSimilarProducts()
                            .saveProductToWishlist()
                            .viewSizeChartAndSelectSizeAddToBagAndGoToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .applyCouponAndAddNewAddressAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();

    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:536 - Search, Save Product to Wishlist, Select Size Chart, Apply Coupon, Add New Address, Pay using Net Banking",
          alwaysRun = true)
    public void saveProductToWishlistCheckBestPriceApplyCouponViewDetailsPlaceOrderAddNewAddressAndPayUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .applyCouponAndAddNewAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:1046 - Search, Save Product to Wishlist, Move To Bag from Wishlist, Apply Personalized Coupon, Edit Address, Pay Using Netbanking+LP",
          alwaysRun = true)
    public void saveMoveToBagApplyPersonalizedCouponEditAddressPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromWishlistToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .applyCouponAndAddNewAddressAndEditAddress()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:754 - Search, Add Product to Bag, Move to Bag, Add New Address, Pay using MyntraCredit",
          alwaysRun = true)
    public void addToBagPlaceOrderBOGOViewDetailsAddNewAddressPayUsingMyntraCredit() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .verifyBOGO()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingMyntraPoint();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:734 - Search, BOGO, Size Chart, Add to Bag, Change Size and Quantity, Add Address, Pay Using Online+Manual GC",
          alwaysRun = true)
    public void sizeChartAddToBagChangeSizeAndQuantityBOGOAddNewAddressPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSizeChartAndSelectSizeAddToBagAndGoToShoppingBag()
                            .verifyBOGO()
                            .changeSizeQuantityOfProductInCart()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1089 - BUG - Search, Staggered Combo, Check Delivery, Add to Bag, Edit Address, Pay Using Online+Mynt",
          alwaysRun = true)
    public void checkDeliveryAddToBagStaggeredComboEditAddressPayUsingOnline() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .checkDeliveryAvailability()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .placeOrderAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:1417 - Search, Add to Bag, Move to Bag, Edit Address, Pay Using Online",
          alwaysRun = true)
    public void moveToBagPlaceOrderStaggeredComboEditAddressPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .selectSizeAddToBagAndMoveToShoppingBag()
                            .verifyStaggeredCombo()
                            .placeOrderAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1429 - Search, Save, Tap For Best Price, Size Chart, Add To Bag, Place Order, Add New Address, Pay Using Online+Manual GC",
          alwaysRun = true)
    public void saveClickForBestPriceSizeChartAddToBagAddAddressPayUsingOnline() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .viewSizeChartAndTapForBestPrice()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:1438 - Search, Save To Wishlist, Add To Bag From Wishlist, Place Order, Add New Address, View Price Details, Pay Using Online+LP",
          alwaysRun = true)
    public void saveEmptyWishlistMoveToBagPlaceOrderPercentageDiscountViewDetailsAddAddressPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromWishlistToShoppingBag()
                            .verifyProductDiscountInShoppingBagAndCompareWithPDP()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1675 - Search, Image Verification, Save To Wishlist, Add To Bag, Add More From Wishlist, Place Order, Add New Address, Pay Using PhonePe",
          alwaysRun = true)
    public void verifyImageSaveAddToBagAddMoreFromWishlistFreegiftAddAddressPayUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .navigateThroughImagesOfProduct()
                            .saveProductToWishlist()
                            .selectSizeAddToBagAndMoveToShoppingBag()
                            .addMoreFromWishlist()
                            .verifyStaggeredCombo()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:2097 - Search, Save To Wishlist, Add to Bag From Wishlist, Apply personalised Coupon, Add New Address, View details, Pay using online+manual GC",
          alwaysRun = true)
    //TODO - Rupee discount was not available for the style id. Add discount verification on PDP (Test data not available)
    //TODO - Manual GiftCard Testdata is not available
    public void verifyDiscountPriceSaveAddToBagFromWishListPlaceOrderApplyCouponViewDetailsPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .saveProductToWishlist()
                            .addProductFromWishlistToShoppingBag()
                            .applyCouponAndAddNewAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1746 - Search, Show similar products, Tap For Best Price, Add To Bag, gift wrap, Place Order, Add New Address, View details, Pay Using EMI",
          alwaysRun = true)
    public void similarProductsClickForBestPriceGiftWrapViewDetailsPayusingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .viewSimilarProducts()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .giftWrapThisProduct()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:269 - Search, Add to Bag, Change Size And Quantity, Verify Free gift, Add New Address Office, Pay Using Myntra Credit",
          alwaysRun = true)
    public void addToBagPlaceOrderChangeSizeAndQuantityFromBagFreeGiftAddNewAddressOfficePayUsingMyntraCredit() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .addProductFromPDPToShoppingBag()
                            .changeSizeQuantityOfProductInCart()
                            .verifyFreeGift()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1795 - Search, Show similar products, Tap For Best Price, Add To Bag, gift wrap, Place Order, Add New Address, View details, Pay Using EMI",
          alwaysRun = true)
    public void similarProductsAddToBagPlaceOrderFreeGiftAddNewAddressOfficePayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .viewSimilarProducts()
                            .addProductFromPDPToShoppingBag()
                            .verifyFreeGift()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:1508 - Search, Save, Add To Bag, Go To Bag, Move To Wishlist, Move To Bag From Wishlist, Apply coupon, Edit Address, Pay Using GiftCard",
          alwaysRun = true)
    //TODO - Giftcard Testdata is not available
    public void saveGoToBagMoveToWishlistMoveToBagFromWishlistApplyCouponEditAddressPayUsingGiftCard() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .addProductFromPDPToShoppingBag()
                            .moveToWishlistAndAddFromWishlist()
                            .applyCouponAndAddNewAddressAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2147 - Search, Add To Bag, View Size Chart, Contact Us, Go To Bag, BOGO, Add New Address, View Details, Pay Using Online+LP",
          alwaysRun = true)
    public void addToBagViewSizeChartContactUsBOGOViewDetailsPayUsingOnline() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSizeChartAndTapForBestPrice()
                            .addProductFromPDPToShoppingBag()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2186 - Search, Show similar products, Tap For Best Price, Add To Bag, Place Order, Apply coupon, Add New Address, Pay Using NetBanking",
          alwaysRun = true)
    public void saveViewSimilarProductsAddToBagFromWishlistApplyCouponClickForBestPriceAddNewAddressHomePayUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .viewSimilarProducts()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2200 - Search, View Similar Products, Contact Us, Add To Bag, View Size Chart, Contact Us, Go To Bag, BOGO, Add New Address, View Details, Pay Using Online+LP",
          alwaysRun = true)
    //TODO - EMI Testdata is not available
    public void saveViewSimilarProductsContactUsAddToBagFromWishlistBOGOViewDetailsPayUsingEMI() {
        setupLoginAndReset().verifyContactUsHasDetails()
                            .searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .viewSizeChartAndTapForBestPrice()
                            .addProductFromWishlistToShoppingBag()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2368 - Search, Show similar products, Tap For Best Price, Add To Bag, Place Order, Gift Wrap, Add New Address, Pay Using NetBanking",
          alwaysRun = true)
    public void clickForBestPriceviewSimilarProductsAddToBagGiftwrapAddNewAddressOfficePayUsingNetbanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSimilarProducts()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .giftWrapThisProduct()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2439 - Search, Save, Check Delivery Option, Add To Bag, Remove Item From Bag, Add ITem From Wishlist, BOGO, Add New Address Office, Pay Using Online+LP",
          alwaysRun = true)
    public void saveCheckDeliveryOptionAddToBagRemoveItemFromBagAddItemFromWishlistBOGOAddAddressOfficePayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .checkDeliveryAvailability()
                            .addProductFromPDPToShoppingBag()
                            .emptyTheCartAndAddProductsFromWishlist()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2740 - Search, save, Add To Bag, Remove product from cart, Add more from wishlist, Place Order, Add New Address, Pay Using NetBanking",
          alwaysRun = true)
    public void saveAddToBagEmptyWishlistPercentsgeDiscountRemoveAddressAddAddressPayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .removeProductFromCart()
                            .addMoreFromWishlist()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:2516 - Search, Save, Click For Best Price, Add To Bag, Empty Wishlist, Go To Bag, Percentage Discount, Edit Address, Pay Using Online+LP",
          alwaysRun = true)
    public void saveClickForBestPriceAddToBagFromWishlistPlaceOrderPercentageDiscountEditAddressPayUsingOnline() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .addMoreFromWishlist()
                            .placeOrderAndEditAddress()
                            .payUsingMyntraPoint()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:2518 - Search, Save, Click For Best Price, View Size Chart, Remove Product From Bag, Add To Bag From Wishlist, Apply Coupon, Percentage Discount, Add Address Home, Pay Using EMI",
          alwaysRun = true)
    //TODO - EMI Testdata is not available
    public void saveClickedForBestPriceViewSizeChartRemoveProductAddFromWishlistApplyCouponPercentageDiscountAddAddressHomePayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .viewSizeChartAndTapForBestPrice()
                            .addProductFromPDPToShoppingBag()
                            .emptyTheCartAndAddProductsFromWishlist()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:3148 - Search, Save, Add To Bag, Apply Coupon, BOGO, Edit Address, Pay Using Gift Card",
          alwaysRun = true)
    //TODO - GiftCard Testdata is not available
    public void saveMoveToBagBOGOApplyCouponEditAddressPayUsingGiftCard() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .verifyBOGO()
                            .applyCouponAndAddNewAddressAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "prod"},
          description = "TC_ID:3088 - Search, save, view size chart, Add To Bag, Remove product from cart, Add more from wishlist, Place Order, Add New Address, edit address,  Pay Using NetBanking",
          alwaysRun = true)
    public void saveViewSizeChartAddToBagRemoveProductPercentageDiscountEditAddressPayUsingOnlineAndGiftCard() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .viewSizeChartAndSelectSizeAddToBagAndGoToShoppingBag()
                            .emptyTheCartAndAddProductsFromWishlist()
                            .placeOrderAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:928 - Save product, Add to bag from Wishlist, Change Size and Add Quantity in Shopping bag,Place Order, Percentage Discount, EditAddress",
          alwaysRun = true)
    public void selectSizeFromWishlistChangeSizeAndAddQuantityApplyCouponPlaceOrderAndAddNewAddressEditAddressPayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .viewSimilarProducts()
                            .verifyProductDetailsInPDP()
                            .saveProductToWishlist()
                            .addProductFromPDPToShoppingBag()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .changeSizeQuantityOfProductInCart()
                            .applyCouponAndAddNewAddressAndEditAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:3218 - Add To Bag, Size Chart, Gift Wrap, Staggered Combo, Add New Address_Home, Pay using COD",
          alwaysRun = true)
    public void addProductToBagGiftWrapAddNewAddressHomePayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .selectSizeAddToBagAndMoveToShoppingBag()
                            .giftWrapThisProduct()
                            .placeOrderAddAddressAndContinueToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:2762 - Search, Check for Best Price, Show Similar, Move to Wishlist, Free Gift, Add New Address_Home, Pay using Mynt+COD",
          alwaysRun = true)
    public void clickForBestPriceAddToBagMoveToWishlistFreeGiftAddAddressAndNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .viewSimilarProducts()
                            .saveProductToWishlist()
                            .selectBestPriceAddProductToBagAndGoToBag()
                            .moveToWishlistAndAddFromWishlist()
                            .verifyFreeGift()
                            .applyCouponAndPlaceOrderWithNewAddress()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }

    @Test(groups = {"Online", "na-ios"},
          description = "TC_ID:1851 - Search, Add To Bag, Giftwrap, BOGO, Place Order, Add New Address, View Price Details, Pay using COD+Mynt",
          alwaysRun = true)
    public void addToBagGiftWrapBOGOPlaceOrderAddNewAddressViewDetailsPayUsingNetBanking() {
        setupLoginAndReset().searchProductUsingStyleID()
                            .verifyProductDetailsInPDP()
                            .addProductFromPDPToShoppingBag()
                            .applyMyntCoupon()
                            .verifyBOGO()
                            .verifyProductPriceInShoppingBagAndCompareWithPDP()
                            .giftWrapThisProduct()
                            .placeOrderAndAddAddress()
                            .viewPriceDetailsAndProceedToPayment()
                            .payUsingNetBanking()
                            .returnToMerchantSiteAfterNetbankingPayment()
                            .verifyOrderIsConfirmed();
    }
}
