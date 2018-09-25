package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.*;
import com.myntra.ui.Direction;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NativeAndroidHomePage extends HomePage {

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
    public boolean isUserLoggedIn() {
        handleDevicePermissions();
        utils.click(getLocator("mnuHamburger"));
        scrollTillElementVisible(getLocator("mnuAccount"), Direction.DOWN);
        boolean isAccountButtonAvailable = utils.isElementPresent(getLocator("mnuAccount"), 5);
        goBack();
        return isAccountButtonAvailable;
    }

    @Step
    @Override
    public ContactUsPage openContactUsPage() {
        utils.click(getLocator("mnuHamburger"));
        scrollTillElementVisible(getLocator("lnkContactUs"), Direction.DOWN);
        utils.click(getLocator("lnkContactUs"));
        return ContactUsPage.createInstance();
    }

    @Step
    @Override
    public ProductListPage searchProductUsingName() {
        String searchItem = (String) getTestData().getAdditionalProperties()
                                                  .get("SearchItem");
        utils.click(getLocator("btnSearch"), true);
        utils.findElement(getLocator("txtSearchField"))
             .sendKeys(searchItem + "\\n");
        return ProductListPage.createInstance();
    }

    @Step
    @Override
    public SavedCardsPage navigateToSavedCards() {
        openMenu();
        NativeAndroidHamburgerPage.createInstance()
                                  .navigateToSavedCards();
        return SavedCardsPage.createInstance();
    }

    @Step
    @Override
    public UserProfilePage goToProfilePage() {
        utils.click(getLocator("mnuHamburger"));
        scrollTillElementVisible(getLocator("mnuAccount"), Direction.DOWN);
        utils.click(getLocator("mnuAccount"));
        return UserProfilePage.createInstance();
    }

    @Step
    @Override
    public boolean isUserLogOutSuccessful() {
        return utils.isElementPresent(getLocator("mnuHamburger"), 5);
    }

    @Step
    @Override
    public HomePage logout() {
        HamburgerPage.createInstance()
                     .navigateToLogout();
        utils.click(getLocator("lnkLogOut"));
        utils.waitForElementToBeVisible(getLocator("mnuHamburger"));
        return this;
    }

    @Step
    @Override
    public ProductListPage searchProductUsingHovering() {
        String subCategory = "//android.widget.TextView[@text='T-Shirts']";
        utils.click(getLocator("mnuHamburger"));
        utils.click(getLocator("lnkMen"));
        utils.click(getLocator("lnkTopwear"));
        utils.click(By.xpath(subCategory));
        utils.waitForElementToBeVisible(getLocator("imgBanner"));
        utils.click(getLocator("imgBanner"));
        return ProductListPage.createInstance();
    }

    @Step
    @Override
    public ProductDescPage searchAnotherProductUsingStyleID() {
        navigateToHome();
        String searchAnotherItem = (String) getTestData().getAdditionalProperties()
                                                         .get("AnotherSearchItem");
        return searchProductUsingStyleID(searchAnotherItem);
    }

    @Step
    private boolean isHomePagePresent() {
        return utils.isElementPresent(getLocator("btnCart"), 2);
    }

    @Step
    public HomePage navigateToHome() {
        nativeView();
        // goBack is called to reach home page as per flow
        int backCount = 0;
        int maxBackCount = 5;
        while (!isHomePagePresent() && (backCount < maxBackCount)) {
            goBack();
            backCount++;
        }
        return this;
    }

    @Step
    @Override
    public HamburgerPage openMenu() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("mnuHamburger")));
        utils.click(getLocator("mnuHamburger"), true);
        utils.click(getLocator("btnAccount"), true);
        return HamburgerPage.createInstance();
    }
}

