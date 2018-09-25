package com.myntra.core.business;

import com.myntra.core.pages.PaymentPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class Payment extends BusinessFlow {

    public static Payment getInstance() {
        return (Payment) BusinessFlow.getInstance(Of.PAYMENT);
    }

    @Step
    public PaymentGateway payUsingNetBanking() {
        PaymentPage paymentpage = PaymentPage.createInstance();
        Assert.assertTrue(paymentpage.isPaymentPageDisplayed(), "Payment page is not displayed");
        Assert.assertTrue(paymentpage.isPaymentOptionEnabled("NetBanking"), "NetBanking Payment option is not displayed");
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            paymentpage.payUsingNetBankingAvenuesTestAccount();
        }
        return PaymentGateway.getInstance();
    }

    @Step
    public Order payUsingCOD() {
        PaymentPage payment = PaymentPage.createInstance();
        Assert.assertTrue(payment.isPaymentPageDisplayed(), "Payment page is not displayed");
        Assert.assertTrue(payment.isPaymentOptionEnabled("COD"), "COD Payment option is not displayed");
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            payment.payUsingCashOnDelivery();
        }
        return Order.getInstance();
    }

    @Step
    public Order payUsingPhonepe() {
        PaymentPage payment = PaymentPage.createInstance();
        Assert.assertTrue(payment.isPaymentPageDisplayed(), "Payment page is not displayed");
        Assert.assertTrue(payment.isPaymentOptionEnabled("Phonepe"), "Phonepe Payment option is not displayed");
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            payment.payUsingPhonePe();
            Assert.assertTrue(payment.isPhonePePageDisplayed(), "User couldn't navigate to PhonePe page to complete the transaction");
        }
        return Order.getInstance();
    }

    @Step
    public Order payUsingCreditDebit() {
        PaymentPage payment = PaymentPage.createInstance();
        Assert.assertTrue(payment.isPaymentPageDisplayed(), "Payment page is not displayed");
        Assert.assertTrue(payment.isPaymentOptionEnabled("creditcard"), "CreditDebit Payment option is not displayed");
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            payment.payUsingCreditAndDebitCard();
        }
        return Order.getInstance();
    }

    @Step
    public Order payUsingGiftCard() {
        PaymentPage payment = PaymentPage.createInstance();
        Assert.assertTrue(payment.isPaymentPageDisplayed(), "Payment page is not displayed");
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            payment.payUsingGiftCard();
        }
        return Order.getInstance();
    }

    @Step
    public Order payUsingSavedCard() {
        PaymentPage payment = PaymentPage.createInstance();
        Assert.assertTrue(payment.isPaymentPageDisplayed(), "Payment page is not displayed");
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            payment.payUsingSavedCard();
        }
        return Order.getInstance();
    }

    @Step
    public Order payUsingWallet() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            PaymentPage.createInstance()
                       .payUsingWallets();
        }
        return Order.getInstance();
    }

    @Step
    public Order payUsingEMI() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            PaymentPage.createInstance()
                       .payUsingEMI();
        }
        return Order.getInstance();
    }

    @Step
    public Payment payUsingMyntraPoint() {
        PaymentPage payment = PaymentPage.createInstance();
        Assert.assertTrue(payment.isPaymentPageDisplayed(), "Payment page is not displayed");
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            payment.payUsingMyntraPoint();
        }
        return this;
    }


    @Step
    public Payment verifyAllNecessaryPaymentOptionsAreEnabled() {
        PaymentPage paymentPage = PaymentPage.createInstance();
        Assert.assertTrue(paymentPage.isPaymentPageDisplayed(), "Payment page is not displayed");
        soft.assertTrue(paymentPage.isPayNowButtonEnabledForNetbanking(), "Pay Now Button is not enabled for Netbanking Payment option");
        soft.assertTrue(paymentPage.isPayNowButtonEnabledForCCDC(), "Pay Now Button is not enabled for CCDC Payment option");
        soft.assertTrue(paymentPage.isPayNowButtonEnabledForCOD(), "Pay Now Button is not enabled for COD Payment option");
        return this;
    }

    @Step
    public Payment continueToPaymentGetPriceDetailsCompaireWithBagPage() {
        PaymentPage paymentPage = PaymentPage.createInstance().getPaymentPagePriceDetails();
        boolean isPaymentPageDetailsSameAsBag = paymentPage.isPaymentPageDetailsSameAsBag();
        Assert.assertTrue(isPaymentPageDetailsSameAsBag, "PaymentPage Price details are not same with Address price details");
        return this;
    }
}
