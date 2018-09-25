package com.myntra.core.pages.MobileWeb;

import com.myntra.core.pages.WishListPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class MobileWebWishListPage extends WishListPage {

    @Step
    @Override
    public boolean isProductDetailsOfWishlistWithPDPSame() {
        getProductDetailsInWishlist();
        HashMap<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetails");
        HashMap<String, String> productDetailsInWishlist = new HashMap<>();
        soft.assertEquals(productDetails.get("Selling Price"), productDetailsInWishlist.get("Selling Price"));
        if (isStrikedPriceAvailable()) {
            soft.assertEquals(productDetails.get("Striked Price"), productDetailsInWishlist.get("Striked Price"));
        }
        soft.assertEquals(productDetails.get("Product Discount"), productDetailsInWishlist.get("Product Discount"));
        return true;
    }

    @Step
    @Override
    public boolean isProductAddedToWishList() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("lblWishlistPageTitle")));
        return (utils.isElementPresent(getLocator("lnkMoveToBag"), 5));
    }

    @Step
    @Override
    protected boolean isWishlistEmpty() {
        return utils.isElementPresent(getLocator("lblEmptyWishlistMsg"), 2);
    }

    @Step
    @Override
    protected WishListPage removeAllProductsFromWishlist() {
        int productCount = 0;
        int maxProductCount = 10;
        while (!isWishlistEmpty() && (productCount < maxProductCount)) {
            utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("lnkMoveToBag")));
            utils.click(getLocator("icoRemoveProduct"), true);
            productCount++;
        }
        Assert.assertTrue((productCount < maxProductCount), String.format(
                "Wishlist page is not functional/More number of products found - Made %d attempts to empty wishlist",
                maxProductCount));
        return this;
    }
}

