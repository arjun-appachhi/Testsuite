package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopProductListPage;
import com.myntra.core.pages.MobileWeb.MobileWebProductListPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidProductListPage;
import com.myntra.core.pages.NativeIOS.NativeIOSProductListPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myntra.core.utils.WaitUtils.waitFor;

public abstract class ProductListPage extends Page {
    public static ProductListPage createInstance() {
        ProductListPage derivedProductListPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedProductListPage = (ProductListPage) DynamicEnhancer.create(NativeAndroidProductListPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedProductListPage = (ProductListPage) DynamicEnhancer.create(NativeIOSProductListPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedProductListPage = (ProductListPage) DynamicEnhancer.create(DesktopProductListPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedProductListPage = (ProductListPage) DynamicEnhancer.create(MobileWebProductListPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedProductListPage;
    }

    @Step
    public abstract ProductDescPage selectFirstProductFromListPage();

    @Step
    protected ProductListPage filterUsingDiscount() {
        utils.click(getLocator("btnFilter"), true);
        utils.click(getLocator("btnDiscountInFilter"), true);
        utils.click(getLocator("chkFilterOption"), true);
        utils.click(getLocator("btnApply"), true);
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("allProductList")));
        return this;
    }

    @Step
    protected ProductListPage filterUsingGender() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    protected ProductListPage filterUsingCategories() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Override
    public String pageName() {
        return ProductListPage.class.getSimpleName();
    }

    @Step
    public WishListPage navigateToWishlist() {
        utils.click(getLocator("icoUser"));
        utils.click(getLocator("lnkWishlist"));
        return WishListPage.createInstance();
    }

    @Step
    public ProductListPage sortSearchResult() {
        utils.click(getLocator("btnSort"), true);
        List<WebElement> sortOptions = getDriver().findElements(getLocator("lstSort"));
        boolean sort = false;
        for (WebElement sortOption : sortOptions) {
            String sortRequired = (String) getTestData().getAdditionalProperties()
                                                        .get("sortRequired");
            if ((sortOption.getText()).equals(sortRequired)) {
                sortOption.click();
                sort = true;
                break;
            }
        }
        Assert.assertTrue(sort, "Sort Required is not available");
        return this;
    }

    @Step
    public boolean isProductListDisplayed() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("allProductList")));
        List<WebElement> saveButtons = getDriver().findElements(getLocator("allProductList"));
        return (!saveButtons.isEmpty());
    }

    @Step
    public ProductListPage applyFilter(String filterType) {
        switch (((String) getTestData().getAdditionalProperties()
                                       .get(filterType)).toLowerCase()) {
            case "discount":
                filterUsingDiscount();
                break;
            case "categories":
                filterUsingCategories();
                break;
            case "gender":
                filterUsingGender();
                break;
            default:
                throw new NotImplementedException("Invalid filter option");
        }
        return this;
    }

    @Step
    public ProductListPage saveToWishlistFromListPage() {
        utils.moveToElement(getLocator("lstAllProducts"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnSave")));
        utils.click(getLocator("btnSave"));
        return this;
    }

    @Step
    public boolean isProductSaved() {
        utils.moveToElement(getLocator("lstAllProducts"));
        return (utils.isElementPresent(getLocator("btnSaved"), 10));
    }

    @Step
    public ProductListPage addToBagFromListPage() {
        utils.moveToElement(getLocator("lstAllProducts"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnAddToBag")));
        utils.click(getLocator("btnAddToBag"), true);
        soft.assertTrue(isProductSizeIsAvailableInPLP(), "Product Size is not present in PLP");
        utils.click(getLocator("btnSizes"), true);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("loaderSpinner")));
        return this;
    }

    @Step
    public boolean isProductAddedToBag() {
        return (utils.isElementPresent(getLocator("txaAddToBagConfirmationMessage"), 1));
    }

    @Step
    public Map<String, String> getProductDetails() {
        waitFor(2, "wait before filter is done");
        HashMap<String, String> productDetails = new HashMap<>();
        WebElement firstProduct = utils.findElement(getLocator("lstAllProducts"));
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("lstAllProducts")));
        if (firstProduct.findElement(getLocator("txaDiscount"))
                        .getText()
                        .contains("OFF)")) {
            productDetails.put("Selling Price", firstProduct.findElement(getLocator("txaSellingPriceIfStrikedPriceAvailable"))
                                                            .getText()
                                                            .split(" ")[1]);
            productDetails.put("Striked Price", utils.findElement(getLocator("txaStrikedPrice"))
                                                     .getText());

        } else {
            productDetails.put("Selling Price", firstProduct.findElement(getLocator("txaSellingPrice"))
                                                            .getText()
                                                            .split(" ")[1]);
        }
        if (firstProduct.findElement(getLocator("txaDiscount"))
                        .getText()
                        .contains("OFF")) {
            productDetails.put("Product Discount", utils.findElement(getLocator("txaDiscount"))
                                                        .getText());
        }
        testExecutionContext.addTestState("productDetailsInPLP", productDetails);
        return productDetails;
    }

    @Step
    public ProductListPage navigateToPromotion() {
        if (isPromotionAvailableInPLP()) {
            utils.click(getLocator("lblPromotions"), true);
        }
        return this;
    }

    @Step
    public boolean isPromotionAvailableInPLP() {
        return utils.isElementPresent(getLocator("lblPromotions"), 4);
    }

    @Step
    public boolean isConditionalDiscountAvailableUnderPromotions() {
        return utils.isElementPresent(getLocator("chkConditionalDiscount"), 4);
    }

    @Step
    public ProductListPage selectFreeGiftUnderPromotions() {
        if (utils.isElementPresent(getLocator("chkBoxPromotionsFreeGift"), 2)) {
            utils.wait(ExpectedConditions.elementToBeClickable(getLocator("chkBoxPromotionsFreeGift")));
            utils.click(getLocator("chkBoxPromotionsFreeGift"), true);
            utils.wait(ExpectedConditions.elementToBeClickable(getLocator("lnkClearAll")));
        }
        return this;
    }

    @Step
    public boolean isPromotionCheckBoxSelected() {
        if (utils.isElementPresent(getLocator("chkBoxPromotionsFreeGift"), 2)) {
            waitForFilterToBeApplied();
            return utils.isElementPresent(getLocator("lnkClearAll"), 2);
        } else {
            LOG.info("Free gift offer is not available under promotion");
            return true;
        }
    }

    @Step
    public void waitForFilterToBeApplied() {
        String itemCouuntBeforeFilter = utils.getText(getLocator("plpItemCount"))
                                             .split("")[2];
        String itemCouuntAfterFilter = utils.getText(getLocator("plpItemCount"))
                                            .split("")[2];
        int count = 0;
        while (itemCouuntBeforeFilter.equals(itemCouuntAfterFilter) && count < 10) {
            count++;
        }
    }

    @Step
    public ProductListPage selectConditionalDiscountUnderPromotions() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductListPage selectStaggeredComboUnderPromotions() {
        if (utils.isElementPresent(getLocator("chkBoxStaggeredCombo"), 2)) {
            utils.wait(ExpectedConditions.elementToBeClickable(getLocator("chkBoxStaggeredCombo")));
            utils.click(getLocator("chkBoxStaggeredCombo"), true);
            utils.wait(ExpectedConditions.elementToBeClickable(getLocator("lnkClearAll")));
            waitForFilterToBeApplied();
        } else {
            Assert.assertTrue(false, "StaggeredComboUnderPromotions option is not available");
        }
        return this;
    }

    @Step
    public ProductListPage selectBOGOUnderPromotions() {
        if (utils.isElementPresent(getLocator("chkBoxPromotionsBOGO"), 2)) {
            utils.wait(ExpectedConditions.elementToBeClickable(getLocator("chkBoxPromotionsBOGO")));
            utils.click(getLocator("chkBoxPromotionsBOGO"), true);
            utils.wait(ExpectedConditions.textToBePresentInElement(getLocator("lnkClearAll"), "CLEAR ALL"));
            utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("loaderSpinner")));
        }
        return this;
    }

    @Step
    public boolean isProductAloneNotApplicableForFreeGiftAvailable() {
        boolean isProductAloneNotApplicableForFreeGiftAvailable = false;
        if (utils.isElementPresent(getLocator("productAloneNotApplicableForFreeGift"), 2)) {
            isProductAloneNotApplicableForFreeGiftAvailable = true;
        }
        return isProductAloneNotApplicableForFreeGiftAvailable;
    }

    @Step
    public ProductDescPage selectProductAloneNotApplicableForFreeGift() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductDescPage selectProductNotApplicableForBOGO() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductDescPage selectProductNotApplicableForConditionalDiscount() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductDescPage selectProductsToCompleteConditionalDiscount() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductDescPage selectProductsNotApplicableForConditionalDiscount() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductDescPage selectOneProductInConditionalDiscountProducts() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ShoppingBagPage navigateToBag() {
        utils.click(getLocator("icoBag"));
        return ShoppingBagPage.createInstance();
    }

    @Step
    public ProductListPage addSecondItemToBag() {
        utils.moveToElement(getLocator("lnkSecondItem"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnAddToBagSecondItem")));
        utils.click(getLocator("btnAddToBagSecondItem"), true);
        utils.click(getLocator("btnSizesForSecondItem"), true);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("loaderSpinner")));
        return this;
    }

    @Step
    public ProductListPage addThirdItemToBag() {
        utils.moveToElement(getLocator("lnkThirdItem"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnAddToBagThirdItem")));
        utils.click(getLocator("btnAddToBagThirdItem"), true);
        utils.click(getLocator("btnSizesForThirdItem"), true);
        return this;
    }

    @Step
    public boolean isProductPriceAvailableInPLP() {
        return (utils.isElementPresent(getLocator("txaSellingPrice"), 5)) ||
                (utils.isElementPresent(getLocator("txaSellingPriceIfStrikedPriceAvailable"), 5));
    }

    @Step
    public boolean isProductNameAvailableInPLP() {
        return (utils.isElementPresent(getLocator("lblProductName"), 5));
    }

    @Step
    public boolean isProductSizeIsAvailableInPLP() {
        return (utils.isElementPresent(getLocator("btnSizes"), 5));
    }

    @Step
    public ProductListPage verifyProductDetailsInPLP() {
        soft.assertTrue(isProductPriceAvailableInPLP(), "Product price is not present in PLP");
        soft.assertTrue(isProductNameAvailableInPLP(), "Product name is not present in PLP");
        return this;
    }
}