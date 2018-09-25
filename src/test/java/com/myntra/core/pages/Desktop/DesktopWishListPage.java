package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.WishListPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DesktopWishListPage extends WishListPage {

    @Step
    @Override
    public WishListPage moveProductToBag() {
        utils.click(getLocator("lnkMoveToBag"));
        selectASize();
        utils.click(getLocator("btnDone"));
        return this;
    }

    @Step
    private WishListPage selectASize() {
        List<WebElement> listOfSizeElementinWishlist = utils.findElements(getLocator("btnSelectSize"));
        for (WebElement eachSizeElementinlistOfSizeElement : listOfSizeElementinWishlist) {
            if (eachSizeElementinlistOfSizeElement.isDisplayed()) {
                utils.wait(ExpectedConditions.elementToBeClickable(eachSizeElementinlistOfSizeElement), 5);
                eachSizeElementinlistOfSizeElement.click();
                break;
            }
        }
        return this;
    }
}

