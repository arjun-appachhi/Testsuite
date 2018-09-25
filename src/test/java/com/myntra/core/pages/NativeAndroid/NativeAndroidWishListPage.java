package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.HomePage;
import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.core.pages.WishListPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public class NativeAndroidWishListPage extends WishListPage {

    @Step
    @Override
    public WishListPage moveProductToBag() {
        utils.click(getLocator("lnkMoveToBag"), true);
        selectASize();
        return this;
    }

    @Step
    private WishListPage selectASize() {
        if (utils.isElementPresent(getLocator("btnSelectSize"), 4)) {
            selectSizeFromWishlistPage();
        } else if (utils.isElementPresent(By.id("com.myntra.android:id/tv_size_label"), 4)) {
            selectSizeFromWishlistOverlay();
            goBack();
        }
        return this;
    }

    @Step
    private void selectSizeFromWishlistOverlay() {
        List<WebElement> listOfSizeElement = utils.findElements(By.id("com.myntra.android:id/tv_size_label"));
        for (WebElement eachSizeElementInListOfSizeElement : listOfSizeElement) {
            eachSizeElementInListOfSizeElement.click();
            if (!utils.isElementPresent(By.id("com.myntra.android:id/tv_select_size_text"), 3)) {
                break;
            }
        }
    }

    @Step
    private void selectSizeFromWishlistPage() {
        List<WebElement> listOfSizeElement = utils.findElements(getLocator("btnSelectSize"));
        for (WebElement eachSizeElementInListOfSizeElement : listOfSizeElement) {
            if (eachSizeElementInListOfSizeElement.isDisplayed()) {
                eachSizeElementInListOfSizeElement.click();
                utils.click(getLocator("btnDone"), true);
                if (!utils.isElementPresent(getLocator("btnDone"), 3)) {
                    break;
                }
            }
        }
    }

    @Step
    @Override
    public HomePage emptyWishlist() {
        removeAllProductsFromWishlist();
        getDriver().navigate()
                   .back();
        return HomePage.createInstance();
    }

    @Step
    @Override
    public WishListPage removeAllProductsFromWishlist() {
        int productCount = 0;
        int maxProductCount = 10;
        while (!isWishlistEmpty() && (productCount < maxProductCount)) {
            if (utils.isElementPresent(getLocator("icoRemoveProduct"), 2)) {
                utils.click(getLocator("icoRemoveProduct"), true);
            }
            productCount++;
        }
        Assert.assertTrue((productCount < maxProductCount),
                String.format("Wishlist page is not functional/More number of products found - Made %d attempts to empty wishlist", maxProductCount));
        return this;
    }

    @Step
    @Override
    public boolean isProductAddedToWishList() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("lblWishlistPageTitle")));
        return (utils.isElementPresent(getLocator("lnkMoveToBag"), 5));
    }

    @Step
    @Override
    public ShoppingBagPage navigateToBag() {
        if (utils.isElementPresent(getLocator("tlbBag"), 5)) {
            utils.click(getLocator("tlbBag"), true);
        } else if (utils.isElementPresent(By.id("com.myntra.android:id/rv_shortlist_products_collection_list"), 5)) {
            goBack();
        }

        return ShoppingBagPage.createInstance();
    }

    @Step
    @Override
    public boolean isProductPriceAvailable() {
        boolean productPriceAvailable = false;
        HashMap<String, String> productDetailsInWishlist = new HashMap<>();
        
        if (utils.isElementPresent(getLocator("lblSellingPrice"), 3)) {
            productDetailsInWishlist.put("Selling Price", utils.findElement(getLocator("lblSellingPrice"))
                                                               .getText().substring(1));
            productPriceAvailable = true;
        }
        return productPriceAvailable;
    }

}
