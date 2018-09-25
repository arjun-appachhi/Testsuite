package com.myntra.core.pages;

import com.myntra.core.enums.ChannelUtils;
import com.myntra.core.pages.Desktop.DesktopWishListPage;
import com.myntra.core.pages.MobileWeb.MobileWebWishListPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidWishListPage;
import com.myntra.core.pages.NativeIOS.NativeIOSWishListPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public abstract class WishListPage extends Page {

    HashMap<String, String> productDetailsInWishlist = new HashMap<>();

    public static WishListPage createInstance() {
        WishListPage derivedWishListPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedWishListPage = (WishListPage) DynamicEnhancer.create(NativeAndroidWishListPage.class,
                        new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedWishListPage = (WishListPage) DynamicEnhancer.create(NativeIOSWishListPage.class,
                        new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedWishListPage = (WishListPage) DynamicEnhancer.create(DesktopWishListPage.class,
                        new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedWishListPage = (WishListPage) DynamicEnhancer.create(MobileWebWishListPage.class,
                        new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedWishListPage;
    }

    @Override
    public String pageName() {
        return WishListPage.class.getSimpleName();
    }

    @Step
    public WishListPage moveProductToBag() {
        utils.click(getLocator("lnkMoveToBag"), true);
        utils.click(getLocator("btnSelectSize"), true);
        utils.click(getLocator("btnDone"), true);
        return this;
    }

    @Step
    public ShoppingBagPage navigateToBag() {
        utils.click(getLocator("tlbBag"), true);
        return ShoppingBagPage.createInstance();
    }

    @Step
    public HomePage emptyWishlist() {
        removeAllProductsFromWishlist();
        goBack();
        return HomePage.createInstance();
    }

    @Step
    public boolean isProductAddedToWishList() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("lblWishlistPageTitle")));
        return (utils.isElementPresent(getLocator("fraProduct"), 5));
    }

    @Step
    protected WishListPage removeAllProductsFromWishlist() {
        int productCount = 0;
        int maxProductCount = 10;
        while (!isWishlistEmpty() && (productCount < maxProductCount)) {
            utils.click(getLocator("icoRemoveProduct"), true);
            productCount++;
        }
        Assert.assertTrue((productCount < maxProductCount), String.format(
                "Wishlist page is not functional/More number of products found - Made %d attempts to empty wishlist",
                maxProductCount));
        return this;
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
    protected boolean isWishlistEmpty() {
        return utils.isElementPresent(getLocator("lblEmptyWishlistMsg"), 2);
    }

    @Step
    public WishListPage getProductDetailsInWishlist() {
        soft.assertTrue(isProductPriceAvailable(), "Product price is not present");
        soft.assertTrue(isStrikedPriceAvailable(), "Striked price is not present");
        soft.assertTrue(isProductDiscountAvailable(), "Product discount is not present");
        return this;
    }

    @Step
    public boolean isProductPriceAvailable() {
        boolean productPriceAvailable = false;
        if (utils.isElementPresent(getLocator("lblSellingPrice"), 3)) {
            productDetailsInWishlist.put("Selling Price", utils.findElement(getLocator("lblSellingPrice"))
                                                               .getText()
                                                               .split("Rs.")[1].replace(",", ""));
            productPriceAvailable = true;
        }
        return productPriceAvailable;
    }

    @Step
    public boolean isStrikedPriceAvailable() {
        boolean strikedPriceAvailable = false;
        if (utils.isElementPresent(getLocator("lblStrikedPrice"), 3)) {
            productDetailsInWishlist.put("Striked Price", utils.findElement(getLocator("lblStrikedPrice"))
                                                               .getText());
            strikedPriceAvailable = true;
        }
        return strikedPriceAvailable;
    }

    @Step
    public boolean isProductDiscountAvailable() {
        boolean productDiscountAvailable = false;
        if (utils.isElementPresent(getLocator("lblProductDiscount"), 3)) {
            productDetailsInWishlist.put("Product Discount", utils.findElement(getLocator("lblProductDiscount"))
                                                                  .getText());
            productDiscountAvailable = true;
        }
        return productDiscountAvailable;
    }

    @Step
    public boolean isProductDetailsOfWishlistWithPDPSame() {
        getProductDetailsInWishlist();
        HashMap<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetails");
        soft.assertEquals(productDetails.get("Selling Price"), productDetailsInWishlist.get("Selling Price"));
        if (isStrikedPriceAvailable()) {
            soft.assertEquals(productDetails.get("Striked Price")
                                            .replace(" ", ""), productDetailsInWishlist.get("Striked Price"));
        }
        soft.assertEquals(productDetails.get("Product Discount"), productDetailsInWishlist.get("Product Discount"));


        return true;
    }

    @Step
    public boolean isProductDetailsOfWishlistWithPLPSame() {
        getProductDetailsInWishlist();
        HashMap<String, String> productDetails = (HashMap<String, String>) testExecutionContext.getTestState(
                "productDetailsInPLP");
        soft.assertEquals(productDetails.get("Selling Price"), productDetailsInWishlist.get("Selling Price"));
        if (isStrikedPriceAvailable()) {
            soft.assertEquals(productDetails.get("Striked Price")
                                            .replace(" ", ""), productDetailsInWishlist.get("Striked Price"));
        }
        soft.assertEquals(productDetails.get("Product Discount"), productDetailsInWishlist.get("Product Discount"));
        return true;
    }
}
