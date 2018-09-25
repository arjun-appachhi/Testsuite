package com.myntra.core.pages.MobileWeb;

import com.myntra.core.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MobileWebHomePage extends HomePage {

    @Step
    @Override
    public boolean isUserLoggedIn() {
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnLogin")));
        utils.click(getLocator("mnuHamburger"), true);
        if (utils.isElementPresent(getLocator("mnuAccount"), 3)) {
            utils.click(getLocator("mnuAccount"),true);
            utils.waitForElementToBeVisible(By.className("logo"));
            utils.wait(ExpectedConditions.textToBe( By.className("edit-profile"),"Edit Profile"));
            utils.click(By.className("logo"),true);
            return true;
        } else {
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
        utils.click(getLocator("btnSearch"), true);
        WebElement txtSearchField = utils.findElement(getLocator("txtSearchField"));
        txtSearchField.sendKeys(searchItem);
        txtSearchField.sendKeys(Keys.ENTER);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("txtSearchField")));
        return ProductDescPage.createInstance();
    }

    @Step
    @Override
    public ContactUsPage openContactUsPage() {
        utils.click(getLocator("mnuHamburger"), true);
        scrollTillElementVisible(getLocator("lnkContactUs"));
        utils.waitForElementToBeVisible(getLocator("lnkContactUs"));
        utils.click(getLocator("lnkContactUs"), true);
        return ContactUsPage.createInstance();
    }

    @Override
    @Step
    public SavedCardsPage navigateToSavedCards() {
        utils.click(getLocator("mnuAccount"), true);
        utils.click(getLocator("lnkSavedCards"), true);
        return SavedCardsPage.createInstance();
    }

    @Override
    @Step
    public UserProfilePage goToProfilePage() {
        openMenu();
        utils.click(getLocator("mnuAccount"), true);
        return super.goToProfilePage();
    }

    @Step
    @Override
    public HomePage logout() {
        utils.click(getLocator("mnuHamburger"), true);
        utils.click(getLocator("mnuAccount"), true);
        utils.click(getLocator("lnkLogOut"), true);
        return HomePage.createInstance();
    }
    @Override
    @Step
    public ProductListPage searchProductUsingHovering() {
        boolean isCategorySelectedAvailable = false;
        utils.click(getLocator("mnuHamburger"), true);
        utils.click(getLocator("lnkMen"), true);
        utils.click(getLocator("lnkTopwear"), true);
        utils.waitForElementToBeVisible(getLocator("lstCategoryLink"));
        utils.click(getLocator("lstCategoryLink"), true);
        return ProductListPage.createInstance();
    }

    @Step
    @Override
    public boolean isUserLogOutSuccessful() {
        utils.click(getLocator("mnuHamburger"), true);
        return utils.isElementPresent(getLocator("lnkLogIn"), 2);
    }

    @Step
    @Override
    public ProductDescPage searchAnotherProductUsingStyleID() {
        goBack();
        goBack();
        goBack();
        String searchAnotherItem = (String) getTestData().getAdditionalProperties()
                                                         .get("AnotherSearchItem");
        return searchProductUsingStyleID(searchAnotherItem);
    }

    @Override
    @Step
    public HamburgerPage openMenu() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("mnuHamburger")));
        utils.click(getLocator("mnuHamburger"), true);
        return HamburgerPage.createInstance();
    }
}
