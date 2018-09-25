package com.myntra.core.business;

import com.myntra.core.pages.ProductDescPage;
import com.myntra.core.pages.ProductListPage;
import com.myntra.core.pages.WishListPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

import java.util.AbstractMap;

public class Product extends BusinessFlow {

    public static Product getInstance() {
        return (Product) getInstance(Of.PRODUCT);
    }

    @Step
    public Product saveProductToWishlist() {
        boolean isProductAvailableInWishList = ProductDescPage.createInstance()
                                                              .saveProductToWishlist()
                                                              .isProductSavedToWishList();
        Assert.assertTrue(isProductAvailableInWishList, "Product NOT found in WishList");
        return this;
    }

    @Step
    public Product checkDeliveryAvailability() {
        boolean isDeliveryAvailableInPinCode = ProductDescPage.createInstance()
                                                              .isDeliveryAvailableInPinCode();
        Assert.assertTrue(isDeliveryAvailableInPinCode,
                "Delivery is NOT avilable for the selected product in the provided PinCode");
        return this;
    }

    @Step
    public ShoppingBag addProductFromWishlistToShoppingBag() {
        WishListPage wishListPage = ProductDescPage.createInstance()
                                                   .navigateToWishlist();
        Assert.assertTrue(wishListPage.isProductAddedToWishList(), "Product added is NOT found in Wishlist");
        wishListPage.moveProductToBag()
                    .navigateToBag();
        return ShoppingBag.getInstance();
    }

    @Step
    public ShoppingBag addProductFromPDPToShoppingBag() {
        boolean isProductPresentInBag = ProductDescPage.createInstance()
                                                       .addProductMoveToBag()
                                                       .isProductPresentInBag();
        Assert.assertTrue(isProductPresentInBag, "Product NOT added in Bag");
        return ShoppingBag.getInstance();
    }

    @Step
    public ShoppingBag selectBestPriceAddProductToBagAndGoToBag() {
        ProductDescPage productDescriptionPage = ProductDescPage.createInstance();
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            productDescriptionPage.tapForBestPrice();
            Assert.assertTrue(productDescriptionPage.isTapForBestPriceSuccessful(),
                    "Best price description is not displayed");
        }
        boolean isProductPresentInBag = productDescriptionPage.addProductMoveToBag()
                                                              .isProductPresentInBag();
        Assert.assertTrue(isProductPresentInBag, "Product NOT added in Bag");
        return ShoppingBag.getInstance();
    }

    @Step
    public ShoppingBag selectSizeAddToBagAndMoveToShoppingBag() {
        ProductDescPage productDescPage = ProductDescPage.createInstance()
                                                         .selectSizeAndAddToBag();
        boolean isProductAvailableInBag = productDescPage.isProductAddedToBag();
        Assert.assertTrue(isProductAvailableInBag, "Product NOT added to Bag");
        productDescPage.goToBag();
        return ShoppingBag.getInstance();
    }

    @Step
    public Product filterSearchResult() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isProductListDisplayed(), "Failed to display the product list");
        productListPage.applyFilter("filterOne");
        return this;
    }

    @Step
    public Product navigateThroughImagesOfProduct() {
        Assert.assertTrue(ProductDescPage.createInstance()
                                         .areAllThumbnailImagesAvailableInPDP(),
                "Thumbnail Images are not available in PDP");
        return this;
    }

    @Step
    public Product viewSimilarProducts() {
        ProductDescPage.createInstance()
                       .viewSimilarProducts();
        return this;
    }

    @Step
    public Product verifyProductDetailsInPDP() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {

            AbstractMap productDetails = ProductDescPage.createInstance()
                                                        .getProductDetails();
            boolean isProductNameAvailable = productDetails.get("Name") != null;
            boolean isSellingPriceAvailable = productDetails.get("Selling Price") != null;
            Assert.assertTrue((isProductNameAvailable && isSellingPriceAvailable),
                    String.format("Product Details are not available in PDP \n%s", productDetails.toString()));
        }
        return this;
    }

    @Step
    public Product fetchProductDetailsFromPLP() {
        ProductListPage.createInstance()
                       .getProductDetails();
        return this;
    }

    @Step
    public ShoppingBag viewSizeChartAndSelectSizeAddToBagAndGoToShoppingBag() {
        ProductDescPage productDescriptionPage = ProductDescPage.createInstance()
                                                                .viewSizeChart();
        boolean isProductAvailableInBag = productDescriptionPage.selectSizeAndAddToBag()
                                                                .isProductAddedToBag();
        Assert.assertTrue(isProductAvailableInBag, "Product NOT added to Bag");
        productDescriptionPage.goToBag();
        return ShoppingBag.getInstance();
    }

    @Step
    public Product viewSizeChartAndTapForBestPrice() {
        ProductDescPage productDescriptionPage = ProductDescPage.createInstance()
                                                                .viewSizeChart();
        productDescriptionPage.tapForBestPrice();
        Assert.assertTrue(productDescriptionPage.isTapForBestPriceSuccessful(),
                "Best price description is not displayed");
        return this;
    }

    @Step
    public Product saveProductToWishlistFromListPage() {
        boolean isProductSavedFromListPage = ProductListPage.createInstance()
                                                            .saveToWishlistFromListPage()
                                                            .isProductSaved();
        Assert.assertTrue(isProductSavedFromListPage, "Product is NOT Saved to Wishlist");
        return this;
    }

    @Step
    public Product addProductToBagFromListPage() {
        boolean isProductAddedToBagFromListPage = ProductListPage.createInstance()
                                                                 .addToBagFromListPage()
                                                                 .isProductAddedToBag();
        Assert.assertTrue(isProductAddedToBagFromListPage, "Product is NOT added to Bag");
        return this;
    }

    @Step
    public Product selectFirstProductAndGoToPDP() {
        ProductListPage productListPage = ProductListPage.createInstance();
        productListPage.getProductDetails();
        productListPage.selectFirstProductFromListPage();
        return this;
    }

    @Step
    public Product sortSearchResult() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isProductListDisplayed(), "Failed to display the product list");
        productListPage.sortSearchResult();
        return this;
    }

    @Step
    public Product selectFreeGiftProductThroughPromotions() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isPromotionAvailableInPLP(), "Promotions are not available in PLP");
        boolean isPromotionCheckBoxSelected = productListPage.navigateToPromotion()
                                                             .selectFreeGiftUnderPromotions()
                                                             .isPromotionCheckBoxSelected();
        Assert.assertTrue(isPromotionCheckBoxSelected, "Promotion check box did not get selected");
        productListPage.selectFirstProductFromListPage();
        return this;
    }

    @Step
    public WishList goToWishlistFromPLP() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isProductSaved(), "Product is not saved to wishlist");
        productListPage.navigateToWishlist();
        return WishList.getInstance();
    }

    @Step
    public WishList goToWishlistFromPDP() {
        ProductDescPage productDescPage = ProductDescPage.createInstance();
        Assert.assertTrue(productDescPage.isProductSavedToWishList(), "Product is not saved to wishlist");
        productDescPage.navigateToWishlist();
        return WishList.getInstance();
    }

    @Step
    public Product selectFreeGiftProductNotApplicableAloneThroughPromotions() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isPromotionAvailableInPLP(), "Promotions are available in PLP");
        productListPage.navigateToPromotion()
                       .selectFreeGiftUnderPromotions();
        Assert.assertTrue(productListPage.isProductAloneNotApplicableForFreeGiftAvailable(),
                "No items are available which alone is not applicable for free gift");
        productListPage.selectProductAloneNotApplicableForFreeGift();
        return this;
    }

    @Step
    public Product selectAndCompleteConditionalDiscountProductsThroughPromotions() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isPromotionAvailableInPLP(), "Promotions are available in PLP");
        productListPage.navigateToPromotion();
        Assert.assertTrue(productListPage.isConditionalDiscountAvailableUnderPromotions(),
                "Conditional discount check box is not available Under promotions");
        productListPage.selectConditionalDiscountUnderPromotions()
                       .selectProductsToCompleteConditionalDiscount();
        return this;
    }

    @Step
    public Product selectOneProductInConditionalDiscountProductsThroughPromotions() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isPromotionAvailableInPLP(), "Promotions are available in PLP");
        productListPage.navigateToPromotion();
        Assert.assertTrue(productListPage.isConditionalDiscountAvailableUnderPromotions(),
                "Conditional discount check box is not available Under promotions");
        productListPage.selectConditionalDiscountUnderPromotions()
                       .selectOneProductInConditionalDiscountProducts();
        return this;
    }

    @Step
    public Product selectStaggeredComboProductThroughPromotions() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isPromotionAvailableInPLP(), "Promotions are available in PLP");
        productListPage.navigateToPromotion()
                       .selectStaggeredComboUnderPromotions()
                       .selectFirstProductFromListPage();
        return this;
    }

    @Step
    public ShoppingBag selectTwoBOGOProductsOfDifferentPriceThroughPromotions() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isPromotionAvailableInPLP(), "Promotions are available in PLP");
        productListPage.navigateToPromotion()
                       .selectBOGOUnderPromotions()
                       .addToBagFromListPage()
                       .navigateToBag()
                       .addSecondBogoProduct()
                       .addSecondItemToBag()
                       .navigateToBag();
        return ShoppingBag.getInstance();
    }

    @Step
    public ShoppingBag selectProductNotApplicableForConditionalDiscountThroughPromotions() {
        ProductListPage productListPage = ProductListPage.createInstance();
        Assert.assertTrue(productListPage.isPromotionAvailableInPLP(), "Promotions are available in PLP");
        productListPage.navigateToPromotion();
        Assert.assertTrue(productListPage.isConditionalDiscountAvailableUnderPromotions(),
                "Conditional discount check box is not available Under promotions");
        productListPage.selectConditionalDiscountUnderPromotions()
                       .selectProductNotApplicableForConditionalDiscount();
        return ShoppingBag.getInstance();
    }

    @Step
    public Product verifyProductDetailsInPLP() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            ProductListPage.createInstance()
                           .verifyProductDetailsInPLP();
        }
        return this;
    }
}
