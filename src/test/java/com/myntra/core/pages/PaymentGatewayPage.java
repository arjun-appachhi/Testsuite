package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopPaymentGatewayPage;
import com.myntra.core.pages.MobileWeb.MobileWebPaymentGatewayPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidPaymentGatewayPage;
import com.myntra.core.pages.NativeIOS.NativeIOSPaymentGatewayPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class PaymentGatewayPage extends Page {
    public static PaymentGatewayPage createInstance() {
        PaymentGatewayPage derivedPaymentGatewayPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedPaymentGatewayPage = (PaymentGatewayPage) DynamicEnhancer.create(NativeAndroidPaymentGatewayPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedPaymentGatewayPage = (PaymentGatewayPage) DynamicEnhancer.create(NativeIOSPaymentGatewayPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedPaymentGatewayPage = (PaymentGatewayPage) DynamicEnhancer.create(DesktopPaymentGatewayPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedPaymentGatewayPage = (PaymentGatewayPage) DynamicEnhancer.create(MobileWebPaymentGatewayPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedPaymentGatewayPage;
    }

    @Override
    public String pageName() {
        return PaymentGatewayPage.class.getSimpleName();
    }

    @Step
    public boolean isNetbankingPaymentGatewayDisplayed() {
        return (utils.isElementPresent(getLocator("txtReturnToMerchantSite"), 10));
    }

    @Step
    public OrderConfirmationPage returnToMerchantSite() {
        if (isNetbankingPaymentGatewayDisplayed()) {
            utils.click(getLocator("txtReturnToMerchantSite"), true);
            utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("txtReturnToMerchantSite")));
        }
        return OrderConfirmationPage.createInstance();
    }
}

