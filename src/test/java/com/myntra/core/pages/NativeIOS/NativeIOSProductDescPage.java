package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.ProductDescPage;
import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class NativeIOSProductDescPage extends ProductDescPage {

    @Step
    @Override
    public ShoppingBagPage addProductMoveToBag() {
        utils.findElement(getLocator("btnAddToBag"))
             .click();
        selectSize();
        utils.findElement(getLocator("btnDone"))
             .click();
        utils.waitForElementToBeVisible(getLocator("btnGoToBag"));
        utils.findElement(getLocator("btnGoToBag"))
             .click();
        return ShoppingBagPage.createInstance();
    }


    @Step
    @Override
    public ProductDescPage saveProductToWishlist() {
        utils.waitForElementToBeVisible(getLocator("btnSave"));
        utils.findElement(getLocator("btnSave"))
             .click();
        return this;
    }

    @Step
    @Override
    public ProductDescPage tapForBestPrice() {
        // TODO Once styleid for production is available for best price,need to implement this;
        Assert.assertTrue(utils.isElementPresent(getLocator("btnAddToBag"), 30), String.format("Page - %s - NOT Loaded", getClass().getSimpleName()));
        utils.swipeDown_m(1);
        if (utils.isElementPresent(getLocator("lnkTapBestPrice"), 5)) {
            utils.findElement(getLocator("lnkTapBestPrice"))
                 .click();
        }
        return this;
    }

    @Step
    @Override
    public boolean isTapForBestPriceSuccessful() {
        // TODO Once styleid for production is available for best price,need to implement this;
        return true;
    }

    @Step
    @Override
    public ProductDescPage selectSizeAndAddToBag() {
        if (utils.isElementPresent(getLocator("btnAddToBag"), 5)) {
            utils.findElement(getLocator("btnAddToBag"), 1000)
                 .click();
            selectSize();
            utils.findElement(getLocator("btnDone"), 1000);
            utils.click(getLocator("btnDone"), true);
        }
        return this;
    }

    @Step
    private void selectSize() {
        utils.findElement(getLocator("btnSelectSize"), 5)
             .click();
    }

    @Step
    @Override
    public ProductDescPage viewSizeChart() {
        utils.findElement(getLocator("btnAddToBag"), 1000)
             .click();
        utils.waitForElementToBeVisible(getLocator("lnkSizeChart"));
        utils.click(getLocator("lnkSizeChart"), true);
        goBack();
        return this;
    }

    @Override
    public boolean isProductAddedToBag() {
        return (utils.isElementPresent(getLocator("btnGoToBag"), 5));
    }

    @Step
    @Override
    public HashMap<String, String> getProductDetails() {
        // TODO, MYNTRA TEAM NEED TO IMPLEMENT THIS METHOD;
        // TODO,Once locators are available for product details as the same is in a single frame on PDP;
        HashMap<String, String> productDetails = new HashMap<>();
        return productDetails;
    }

    @Step
    @Override
    public boolean isProductDisplayed() {
        return (utils.isElementPresent(getLocator("lblProductName"), 2));
    }
}

