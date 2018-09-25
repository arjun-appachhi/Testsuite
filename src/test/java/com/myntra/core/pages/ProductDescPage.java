package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopProductDescPage;
import com.myntra.core.pages.MobileWeb.MobileWebProductDescPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidProductDescPage;
import com.myntra.core.pages.NativeIOS.NativeIOSProductDescPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.core.utils.WaitUtils;
import com.myntra.ui.Direction;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public abstract class ProductDescPage extends Page {

    public static ProductDescPage createInstance() {
        ProductDescPage derivedProductDescPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedProductDescPage = (ProductDescPage) DynamicEnhancer.create(NativeAndroidProductDescPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedProductDescPage = (ProductDescPage) DynamicEnhancer.create(NativeIOSProductDescPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedProductDescPage = (ProductDescPage) DynamicEnhancer.create(DesktopProductDescPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedProductDescPage = (ProductDescPage) DynamicEnhancer.create(MobileWebProductDescPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedProductDescPage;
    }

    @Override
    public String pageName() {
        return ProductDescPage.class.getSimpleName();
    }

    @Step
    public abstract ShoppingBagPage addProductMoveToBag();

    @Step
    public WishListPage navigateToWishlist() {
        utils.click(getLocator("icoWishlist"), true);
        return WishListPage.createInstance();
    }

    @Step
    public ProductDescPage selectSizeAndAddToBag() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isProductSavedToWishList() {
        utils.waitForElementToBeVisible(getLocator("btnSaved"));
        return (utils.isElementPresent(getLocator("btnSaved"), 5));
    }

    @Step
    public boolean isProductAddedToBag() {
        return (utils.isElementPresent(getLocator("btnGoToBag"), 5));
    }

    @Step
    public boolean isDeliveryAvailableInPinCode() {
        utils.scroll(Direction.DOWN, 3);
        utils.waitForElementToBeVisible(getLocator("btnEnterPinCode"));
        utils.click(getLocator("btnEnterPinCode"), true);
        utils.sendKeys(getLocator("txtPinCode"), getAddressTestData().getPincode());
        utils.waitForElementToBeVisible(getLocator("btnCheckDeliveryOption"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnCheckDeliveryOption")));
        utils.click(getLocator("btnCheckDeliveryOption"), true);
        if (utils.isElementPresent(getLocator("txaDeliveryconfirmation"), 2)) {
            utils.scroll(Direction.UP, 3);
            return true;
        } else {
            return false;
        }
    }

    @Step
    public boolean isProductDisplayed() {
        utils.waitForElementToBeVisible(getLocator("lblProductName"));
        return (utils.isElementPresent(getLocator("lblProductName"), 2));
    }

    @Step
    public ProductDescPage saveProductToWishlist() {
        utils.waitForElementToBeVisible(getLocator("btnSave"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnSave")));
        utils.click(getLocator("btnSave"), true);
        return this;
    }

    @Step
    public ProductDescPage tapForBestPrice() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("lnkTapBestPrice")));
        utils.click(getLocator("lnkTapBestPrice"),true);
        return this;
    }

    @Step
    public boolean isTapForBestPriceSuccessful() {
        return (utils.isElementPresent(getLocator("txaBestPriceDetails"), 10) || utils.isElementPresent(getLocator("txtAlreadyInBestPrice"), 10));
    }

    @Step
    public ShoppingBagPage goToBag() {
        utils.click(getLocator("btnGoToBag"), true);
        return ShoppingBagPage.createInstance();
    }

    @Step
    public boolean isProductNameAvailable() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isProductBrandAvailable() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isProductPriceAvailable() {
        return utils.isElementPresent(getLocator("lblProductPrice"), 5);
    }

    @Step
    public boolean isStrikedPriceAvailable() {
        return utils.isElementPresent(getLocator("lblStrikedPrice"), 5);
    }

    @Step
    public boolean isProductDiscountAvailable() {
        return utils.isElementPresent(getLocator("lblProductDiscount"), 5);
    }

    @Step
    public boolean isProductDetailsAvailable() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isSimilarProductAvailable() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductDescPage viewSimilarProducts() {
        // TODO - SimilarProducts feature is not available on stage Env.
        scrollTillElementVisible(getLocator("lnkMoreProducts"), Direction.DOWN);
        utils.click(getLocator("lnkMoreProducts"), true);
        goBack();
        return this;
    }

    @Step
    public boolean isProductImagePresentInPDP() {
        return (utils.isElementPresent(getLocator("imgLargeThumbnail"), 5));
    }

    @Step
    public ProductDescPage viewSizeChart() {
        utils.click(getLocator("lnkSizeChart"), true);
        utils.click(getLocator("btnCloseSizeChart"), true);
        return this;
    }

    @Step
    public boolean isSizeChartLinkAvailable() {
        return (utils.isElementPresent(getLocator("lnkSizeChart"), 5));
    }

    @Step
    public boolean areAllThumbnailImagesAvailableInPDP() {
        boolean imageAvailable = true;
        List<WebElement> smallThumbnails = getDriver().findElements(getLocator("imgSmallThumbnails"));
        for (WebElement smallThumbnail : smallThumbnails) {
            utils.click(smallThumbnail, true);
        }
        return imageAvailable;
    }

    @Step
    public HashMap<String, String> getProductDetails() {
        HashMap<String, String> productDetails = new HashMap<>();
        productDetails.put("Name", utils.findElement(getLocator("lblProductName"))
                                        .getText());
        productDetails.put("Selling Price", utils.findElement(getLocator("lblSellingPrice"))
                                                 .getText());
        List<WebElement> prod_desc=utils.findElements(getLocator("lblProductDesc"));
        productDetails.put("ProductCodeActual", utils.findElement(getLocator("lblProductCode",prod_desc.size()))
                                                     .getText());
        productDetails.put("ProductCodeExpected", (String) getTestData().getAdditionalProperties()
                                                                        .get("SearchItem"));
        if (utils.isElementPresent(getLocator("lblStrikedPrice"), 5)) {
            productDetails.put("Stricked price", utils.findElement(getLocator("lblStrikedPrice"))
                                                      .getText());
            productDetails.put("Product Discount", utils.findElement(getLocator("lblProductDiscount"))
                                                        .getText());
        }
        testExecutionContext.addTestState("productDetails", productDetails);
        soft.assertTrue(isProductPriceAvailable(), "Product price is not present");
        soft.assertTrue(isStrikedPriceAvailable(), "Striked price is not present");
        soft.assertTrue(isProductDiscountAvailable(), "Product discount is not present");
        return productDetails;
    }
}