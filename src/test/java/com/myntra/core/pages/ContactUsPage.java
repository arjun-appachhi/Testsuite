package com.myntra.core.pages;


import com.myntra.core.pages.Desktop.DesktopContactUsPage;
import com.myntra.core.pages.MobileWeb.MobileWebContactUsPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidContactUsPage;
import com.myntra.core.pages.NativeIOS.NativeIOSContactUsPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

public class ContactUsPage extends Page {
    public static ContactUsPage createInstance() {
        ContactUsPage derivedContactUsPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedContactUsPage = (ContactUsPage) DynamicEnhancer.create(NativeAndroidContactUsPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedContactUsPage = (ContactUsPage) DynamicEnhancer.create(NativeIOSContactUsPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedContactUsPage = (ContactUsPage) DynamicEnhancer.create(DesktopContactUsPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedContactUsPage = (ContactUsPage) DynamicEnhancer.create(MobileWebContactUsPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedContactUsPage;
    }

    @Override
    public String pageName() {
        return ContactUsPage.class.getSimpleName();
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(utils.isElementPresent(getLocator("txaHelpCenter"), 10), "Contact Us page is not loaded");
    }

    @Step
    public HomePage closeContactUsPage() {
        goBack();
        return HomePage.createInstance();
    }
}
