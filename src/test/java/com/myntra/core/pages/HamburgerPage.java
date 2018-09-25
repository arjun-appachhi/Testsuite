package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopHamburgerPage;
import com.myntra.core.pages.Desktop.DesktopHomePage;
import com.myntra.core.pages.MobileWeb.MobileWebHamburgerPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidHamburgerPage;
import com.myntra.core.pages.NativeIOS.NativeIOSHamburgerPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

public abstract class HamburgerPage extends Page {

    public static HamburgerPage createInstance() {
        HamburgerPage derivedHamburgerPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedHamburgerPage = (HamburgerPage) DynamicEnhancer.create(NativeAndroidHamburgerPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedHamburgerPage = (HamburgerPage) DynamicEnhancer.create(NativeIOSHamburgerPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedHamburgerPage = (HamburgerPage) DynamicEnhancer.create(DesktopHamburgerPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedHamburgerPage = (HamburgerPage) DynamicEnhancer.create(MobileWebHamburgerPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedHamburgerPage;
    }

    @Override
    public String pageName() {
        return HamburgerPage.class.getSimpleName();
    }

    @Step
    public AddressPage goToAddress() {
        utils.click(DesktopHomePage.createInstance()
                                   .getLocator("lnkSavedAddress"), true);
        return AddressPage.createInstance();
    }

    @Step
    public OrderConfirmationPage goToMyOrdersPage() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public SavedCardsPage navigateToSavedCards() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public UserProfilePage goToEditProfile() {
        utils.click(getLocator("lblEditProfile"), true);
        return UserProfilePage.createInstance();
    }

    @Step
    public HamburgerPage navigateToLogout() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }
}
