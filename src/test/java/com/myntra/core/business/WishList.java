package com.myntra.core.business;

import com.myntra.core.pages.WishListPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class WishList extends BusinessFlow {
    public static WishList getInstance() {
        return (WishList) getInstance(Of.WISHLIST);
    }

    @Step
    public WishList verifyProductDetailsinWishlistWithPDP() {
        WishListPage wishListPage = WishListPage.createInstance();
        Assert.assertTrue(wishListPage.isProductAddedToWishList(), "Product is not added to wishlist");
        Assert.assertTrue(wishListPage.isProductDetailsOfWishlistWithPDPSame(),
                "Product Details in Wishlist is not same as PDP");
        return this;
    }

    @Step
    public WishList verifyProductDetailsinWishlistWithPLP() {
        WishListPage wishListPage = WishListPage.createInstance();
        Assert.assertTrue(wishListPage.isProductAddedToWishList(), "Product is not added to wishlist");
        Assert.assertTrue(wishListPage.isProductDetailsOfWishlistWithPLPSame(),
                "Product Details in Wishlist is not same as PLP");
        return this;
    }

}
