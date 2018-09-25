package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.*;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

public class NativeIOSHomePage extends HomePage {

    @Step
    @Override
    public boolean isUserLoggedIn() {
        if (utils.isElementPresent(getLocator("mnuOrders"), 3)) {
            utils.click(getLocator("icoHome"));
            return true;
        } else {
            utils.click(getLocator("icoHome"));
            return false;
        }
    }

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
        utils.click(getLocator("btnSearch"));
        utils.sendKeys(getLocator("txtSearchField"), searchItem);
        return ProductDescPage.createInstance();
    }

    @Step
    @Override
    public ProductListPage searchProductUsingName() {
        //TODO, FOR NOW IMPLENTED AS PER PDP FLOW INSTEAD OF PLP,
        //TODO, ONCE LOCATORS FOR PLP IS AVAILABLE MYNTRA TEAM NEED TO MODIFY THIS METHOD
        // TODO, IMPLEMENT IT AS PER THE PLP FLOW (SEARCH USING GOLDEN SET DATA,INSTEAD OF STYLEID);
        String searchItem = (String) getTestData().getAdditionalProperties()
                                                  .get("SearchItem");
        utils.click(getLocator("btnSearch"));
        utils.sendKeys(getLocator("txtSearchField"), searchItem);
        return ProductListPage.createInstance();
    }

    @Step
    @Override
    protected HomePage resetAddress() {
        utils.click(getLocator("icoProfile"), true);
        HamburgerPage.createInstance()
                     .goToAddress()
                     .emptyAddress();
        utils.click(getLocator("icoHome"));
        return HomePage.createInstance();
    }

    @Step
    @Override
    protected WishListPage navigateToWishlist() {
        utils.click(getLocator("btnWishlist"), true);
        return WishListPage.createInstance();
    }

    @Step
    @Override
    public ContactUsPage openContactUsPage() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    @Override
    public HomePage logout() {
        utils.click(getLocator("icoProfile"));
        utils.click(getLocator("btnLogOut"));
        return HomePage.createInstance();
    }
}
