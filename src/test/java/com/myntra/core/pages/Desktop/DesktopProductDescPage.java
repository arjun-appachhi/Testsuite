package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.ProductDescPage;
import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.core.pages.WishListPage;
import com.myntra.ui.Direction;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public class DesktopProductDescPage extends ProductDescPage {

    HashMap<String, String> productDetails = new HashMap<>();

    @Step
    @Override
    public WishListPage navigateToWishlist() {
        utils.click(getLocator("icoUser"));
        utils.click(getLocator("lnkWishlist"));
        return WishListPage.createInstance();
    }

    @Step
    @Override
    public ProductDescPage selectSizeAndAddToBag() {
        utils.waitForElementToBeVisible(getLocator("btnSelectSize"));
        List<WebElement> listOfSizeElement = utils.findElements(getLocator("btnSelectSize"));
        for (WebElement eachSizeElementinlistOfSizeElement : listOfSizeElement) {
            if (eachSizeElementinlistOfSizeElement.isDisplayed()) {
                eachSizeElementinlistOfSizeElement.click();
                break;
            }
        }
        utils.waitForElementToBeVisible(getLocator("btnAddToBag"));
        utils.click(getLocator("btnAddToBag"), true);
        return this;
    }

    @Step
    @Override
    public ShoppingBagPage addProductMoveToBag() {
        selectSizeAndAddToBag().goToBag();
        return ShoppingBagPage.createInstance();
    }

    @Step
    @Override
    public boolean isProductPriceAvailable() {
        boolean productPriceAvailable = false;
        if (utils.isElementPresent(getLocator("lblProductPrice"), 3)) {
            productDetails.put("Product Price", utils.findElement(getLocator("lblProductPrice"))
                                                     .getText());
            productPriceAvailable = true;
        }
        return productPriceAvailable;
    }

    @Step
    @Override
    public boolean isStrikedPriceAvailable() {
        boolean strikedPriceAvailable = false;
        if (utils.isElementPresent(getLocator("lblStrikedPrice"), 3)) {
            productDetails.put("Striked Price", utils.findElement(getLocator("lblStrikedPrice"))
                                                     .getText());
            strikedPriceAvailable = true;
        }
        return strikedPriceAvailable;
    }

    @Step
    @Override
    public boolean isProductDiscountAvailable() {
        boolean productDiscountAvailable = false;
        if (utils.isElementPresent(getLocator("lblProductDiscount"), 3)) {
            productDetails.put("Product Discount", utils.findElement(getLocator("lblProductDiscount"))
                                                        .getText());
            productDiscountAvailable = true;
        }
        return productDiscountAvailable;
    }

    @Step
    @Override
    public ProductDescPage tapForBestPrice() {
        return this;
    }


    @Step
    @Override
    public boolean isProductSavedToWishList() {
        utils.waitForElementToBeVisible(getLocator("btnSaved"));
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("wishlistInfoPopup")));
        return (utils.isElementPresent(getLocator("btnSaved"), 5));
    }

    @Step
    @Override
    public HashMap<String, String> getProductDetails() {
        productDetails = super.getProductDetails();
        String actualProductCode = productDetails.get("ProductCodeActual");
        productDetails.put("ProductCodeActual", actualProductCode);
        productDetails.put("Selling Price", productDetails.get("Selling Price")
                                                          .split(" ")[1]);
        testExecutionContext.addTestState("productDetails", productDetails);
        productDetails.get("productDetails");
        soft.assertTrue(isProductPriceAvailable(), "Product price is not present");
        soft.assertTrue(isStrikedPriceAvailable(), "Striked price is not present");
        soft.assertTrue(isProductDiscountAvailable(), "Product discount is not present");
        return productDetails;
    }

    @Step
    @Override
    public ProductDescPage viewSimilarProducts() {
        utils.scroll(Direction.DOWN, 3);
        soft.assertTrue(utils.isElementPresent(getLocator("lnkMoreProducts"), 2), "View similar products is not available ");
        utils.scroll(Direction.UP, 3);
        return this;
    }
}
