package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.core.pages.WishListPage;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NativeIOSWishListPage extends WishListPage {

    @Step
    @Override
    public WishListPage moveProductToBag() {
        utils.click(getLocator("lnkMoveToBag"), true);
        selectSize();
        utils.click(getLocator("btnDone"));
        return this;
    }

    @Step
    @Override
    public WishListPage removeAllProductsFromWishlist() {
        while (!isWishlistEmpty()) {
            utils.waitForElementToBeVisible(getLocator("icoRemoveProduct"));
            utils.click(getLocator("icoRemoveProduct"), true);
            utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("fraProduct")),5);
        }
        return this;
    }

    @Step
    @Override
    public boolean isWishlistEmpty() {
        utils.isElementPresent(MobileBy.AccessibilityId("WISHLIST"),10);
        return utils.isElementPresent(getLocator("lblEmptyWishlistMsg"), 15);
    }

    @Step
    @Override
    public ShoppingBagPage navigateToBag() {
        utils.waitForElementToBeVisible(getLocator("tlbBag"));
        utils.click(getLocator("tlbBag"), true);
        handleOkPermission();
        return ShoppingBagPage.createInstance();
    }

    @Step
    private void selectSize() {
        utils.findElement(getLocator("btnSelectSize"), 5)
             .click();
    }
}
