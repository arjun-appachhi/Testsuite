package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Date;

public class DesktopHomePage extends HomePage {

    @Step
    @Override
    public ProductDescPage searchProductUsingStyleID() {
        String searchItem = (String) getTestData().getAdditionalProperties()
                                                  .get("SearchItem");
        return searchProductUsingStyleID(searchItem);
    }

    @Step
    @Override
    public ProductDescPage searchProductUsingStyleID(String searchItem) {
        utils.sendKeys(getLocator("txtSearchField"), searchItem);
        utils.click(getLocator("btnSearch"));
        return ProductDescPage.createInstance();
    }

    @Step
    @Override
    public boolean isUserLoggedIn() {
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("icoUser")), 5);
        utils.click(getLocator("icoUser"));
        // TODO Myntra team please remove this condition once bug is fixed; JIRA ID:VEGASF-600
        if (!utils.getText(getLocator("icoProfile"))
                  .equalsIgnoreCase(getUserTestData().getEmail())) {
            utils.refreshPage();
            utils.isElementPresent(getLocator("icoUser"), 5);
            utils.click(getLocator("icoUser"));
        }
        return utils.getText(getLocator("icoProfile"))
                    .equalsIgnoreCase(getUserTestData().getEmail());
    }

    @Step
    @Override
    public HomePage reset() {
        utils.wait((ExpectedConditions.invisibilityOfElementLocated(getLocator("btnLogin"))));
        return super.reset();
    }

    @Step
    @Override
    protected HomePage resetAddress() {
        utils.click(getLocator("icoUser"));
        utils.click(getLocator("lnkSavedAddress"), true);
        AddressPage.createInstance()
                   .emptyAddress();
        return this;
    }

    @Step
    @Override
    protected WishListPage navigateToWishlist() {
        DesktopProductDescPage.createInstance()
                              .navigateToWishlist();
        return WishListPage.createInstance();
    }

    @Override
    public HamburgerPage openMenu() {
        utils.click(getLocator("icoUser"));
        return HamburgerPage.createInstance();
    }

    @Step
    @Override
    public boolean isUserLogOutSuccessful() {
        utils.click(getLocator("icoUser"));
        return utils.isElementPresent(getLocator("lnkLogIn"), 2);
    }

    @Step
    @Override
    public HomePage logout() {
        utils.click(getLocator("icoUser"));
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("lnkLogOut")));
        utils.moveToElement(getLocator("lnkLogOut"));
        utils.click(getLocator("lnkLogOut"));
        return HomePage.createInstance();
    }

    @Step
    @Override
    public void isLoaded() {
        super.isLoaded();
        boolean isInternalServerError = utils.isElementPresent(By.xpath("//*[contains(text(),'Internal Server Error')]"), 1);
        if (isInternalServerError) {
            String internalServerErrorFileName = "InternalServerError-" + this.getClass().getSimpleName() + "-" + new Date().getTime();
            utils.takeScreenshot(internalServerErrorFileName);
            LOG.info(String.format("Internal Server Error found on page - %s - Refresh Page and try", this.getClass().getSimpleName()));
            LOG.info(String.format("Screenshot saved as - %s", internalServerErrorFileName));
            utils.refreshPage();
            LOG.info(String.format("Check if page - %s - is loaded", this.getClass().getSimpleName()));
            super.isLoaded();
        }
    }
}
