package com.myntra.core.pages;

import com.myntra.core.enums.ChannelUtils;
import com.myntra.core.pages.Desktop.DesktopHomePage;
import com.myntra.core.pages.MobileWeb.MobileWebHomePage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidHomePage;
import com.myntra.core.pages.NativeIOS.NativeIOSHomePage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public abstract class HomePage extends Page {

    public static HomePage createInstance() {
        HomePage derivedHomePage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedHomePage = (HomePage) DynamicEnhancer.create(NativeAndroidHomePage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedHomePage = (HomePage) DynamicEnhancer.create(NativeIOSHomePage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedHomePage = (HomePage) DynamicEnhancer.create(DesktopHomePage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedHomePage = (HomePage) DynamicEnhancer.create(MobileWebHomePage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedHomePage;
    }

    @Override
    public String pageName() {
        return HomePage.class.getSimpleName();
    }

    @Step
    public abstract ProductDescPage searchProductUsingStyleID();

    @Step
    public abstract ProductDescPage searchProductUsingStyleID(String searchItem);

    @Step
    public ProductListPage searchProductUsingName() {
        String searchItem = (String) getTestData().getAdditionalProperties()
                                                  .get("SearchItem");
        utils.click(getLocator("btnSearch"), true);
        WebElement txtSearchField = utils.findElement(getLocator("txtSearchField"));
        txtSearchField.sendKeys(searchItem);
        txtSearchField.sendKeys(Keys.ENTER);
        return ProductListPage.createInstance();
    }

    @Step
    public ProductListPage searchProductUsingBanner() {
        utils.click(getLocator("imgBanner"), true);
        return ProductListPage.createInstance();
    }

    @Step
    public HomePage verifyAutoSuggestion() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public abstract boolean isUserLoggedIn();

    @Step
    public HomePage reset() {
        return resetBag().resetAddress()
                         .resetWishlist();
    }

    @Step
    protected HomePage resetAddress() {
        openMenu().goToAddress()
                  .emptyAddress();
        return this;
    }

    @Step
    protected HomePage resetWishlist() {
        navigateToWishlist().emptyWishlist();
        return this;
    }

    @Step
    protected WishListPage navigateToWishlist() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("imgMyntraLogo")));
        utils.click(getLocator("btnWishlist"), true);
        return WishListPage.createInstance();
    }

    @Step
    public HamburgerPage openMenu() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("mnuHamburger")));
        utils.click(getLocator("mnuHamburger"), true);
        utils.click(getLocator("mnuAccount"));
        return HamburgerPage.createInstance();
    }

    @Step
    public ShoppingBagPage navigateToBag() {
        utils.click(getLocator("btnCart"), true);
        return ShoppingBagPage.createInstance();
    }

    @Step
    private HomePage resetBag() {
        navigateToBag().emptyBag();
        return this;
    }

    @Step
    public ContactUsPage openContactUsPage() {
        utils.click(getLocator("lnkContactUs"));
        return ContactUsPage.createInstance();
    }

    @Step
    public ProductListPage searchProductUsingHovering() {
        boolean isCategorySelectedAvailable = false;
        utils.moveToElement(getLocator("lnkMen"));
        List<WebElement> categoryLinks = getDriver().findElements(getLocator("lstCategoryLink"));
        for (WebElement categoryLink : categoryLinks) {
            if (categoryLink.getText()
                            .equalsIgnoreCase((String) getTestData().getAdditionalProperties()
                                                                    .get("category"))) {
                isCategorySelectedAvailable = true;
                categoryLink.click();
                break;
            }
        }
        soft.assertTrue(isCategorySelectedAvailable, "category selected is not available");
        return ProductListPage.createInstance();
    }

    @Step
    public SavedCardsPage navigateToSavedCards() {
        utils.click(getLocator("icoUser"));
        utils.click(getLocator("lnkSavedCards"));
        return SavedCardsPage.createInstance();
    }

    @Step
    public UserProfilePage navigateToEditProfile() {
        openMenu();
        return HamburgerPage.createInstance()
                            .goToEditProfile();
    }

    @Step
    public UserProfilePage goToProfilePage() {
        utils.click(HamburgerPage.createInstance()
                                 .getLocator("lblEditProfile"), true);
        return UserProfilePage.createInstance();
    }

    @Step
    public HamburgerPage clickOnUserNameSection() {
        openMenu();
        utils.click(getLocator("icoProfile"), true);
        return HamburgerPage.createInstance();
    }

    @Step
    public HomePage navigateToHomePage() {
        if (getChannelUtils() == ChannelUtils.NATIVE_ANDROID) {
            utils.click(getLocator("lnkShopNow"), true);
        }
        return this;
    }

    @Step
    public boolean isUserLogOutSuccessful() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public ProductDescPage searchAnotherProductUsingStyleID() {
        goBack();
        String searchAnotherItem = (String) getTestData().getAdditionalProperties()
                                                         .get("AnotherSearchItem");
        return searchProductUsingStyleID(searchAnotherItem);
    }

    @Step
    public HomePage logout() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }
}
