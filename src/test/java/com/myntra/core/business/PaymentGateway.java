package com.myntra.core.business;

import com.myntra.core.pages.PaymentGatewayPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class PaymentGateway extends BusinessFlow {

    public static PaymentGateway getInstance() {
        return (PaymentGateway) getInstance(Of.PAYMENT_GATEWAY);
    }

    @Step
    public Order returnToMerchantSiteAfterNetbankingPayment() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            PaymentGatewayPage gateway = PaymentGatewayPage.createInstance();
            Assert.assertTrue(gateway.isNetbankingPaymentGatewayDisplayed(),
                    "User couldn't navigate to Netbanking payment gateway to complete the transaction");
            gateway.returnToMerchantSite();
        }
        return Order.getInstance();
    }
}
