package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.OrderConfirmationPage;
import com.myntra.core.pages.PaymentGatewayPage;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

public class NativeIOSPaymentGatewayPage extends PaymentGatewayPage {

    @Step
    @Override
    public boolean isNetbankingPaymentGatewayDisplayed() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    @Override
    public OrderConfirmationPage returnToMerchantSite() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }
}
