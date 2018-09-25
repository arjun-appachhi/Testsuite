package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.OrderConfirmationPage;
import com.myntra.core.pages.PaymentPage;
import com.myntra.ui.Direction;
import com.myntra.ui.SelectBy;
import io.qameta.allure.Step;

import java.util.Calendar;
import java.util.HashMap;

public class NativeAndroidPaymentPage extends PaymentPage {

    @Step
    @Override
    public OrderConfirmationPage payUsingCashOnDelivery() {
        scrollTillElementVisible(getLocator("tabCOD"));
        utils.click(getLocator("tabCOD"), true);
        utils.click(getLocator("btnPayOnDelivery"), true);
        return OrderConfirmationPage.createInstance();
    }

    @Step
    @Override
    public PaymentPage payUsingCreditAndDebitCard() {
        fillCreditCardDetails();
        utils.click(getLocator("btnPayNowForCreditCardPayment"), true);
        return this;
    }

    @Step
    private void fillCreditCardDetails() {
        scrollTillElementVisible(getLocator("tabCreditCard"));
        utils.click(getLocator("tabCreditCard"), true);
        utils.waitForElementToBeVisible(getLocator("txtCreditCardNumber"));
        utils.findElement(getLocator("txtCreditCardNumber"))
             .sendKeys((String) getTestData().getAdditionalProperties()
                                             .get("CreditCardNumber"));
        utils.findElement(getLocator("txtNameOnCreditCard"))
             .sendKeys((String) getTestData().getAdditionalProperties()
                                             .get("NameOnCreditCard"));
        utils.select(getLocator("ddlExpiryMonthField"), SelectBy.VALUE, String.format("%02d", ((Calendar.getInstance()
                                                                                                        .get(Calendar.MONTH) + 1) % 12)));
        utils.select(getLocator("ddlExpiryYearField"), SelectBy.VISIBLE_TEXT, String.format("%s", (Calendar.getInstance()
                                                                                                           .get(Calendar.YEAR) + 2)));
        utils.findElement(getLocator("txtCVV"))
             .sendKeys((CharSequence) getTestData().getAdditionalProperties()
                                                   .get("CVV"));
    }

    @Step
    @Override
    public OrderConfirmationPage payUsingPhonePe() {
        scrollTillElementVisible(getLocator("tabPhonePe"));
        utils.click(getLocator("tabPhonePe"), true);
        return OrderConfirmationPage.createInstance();
    }

    @Step
    @Override
    public boolean isPayNowButtonEnabledForNetbanking() {
        if (isNetbankingPaymentOptionEnabled()) {
            utils.click(getLocator("tabNetBanking"));
            utils.click(getLocator("icoAvenuesTest"), true);
            return (utils.findElement(getLocator("btnNetbankingPayNow"))
                         .isEnabled());
        } else {
            LOG.info("Netbanking payment option is not displayed");
            return false;
        }
    }

    @Step
    @Override
    public boolean isPayNowButtonEnabledForCCDC() {
        changePaymentType();
        if (isCCDCPaymentOptionEnabled()) {
            fillCreditCardDetails();
            return (utils.findElement(getLocator("btnPayNowForCreditCardPayment"))
                         .isEnabled());
        } else {
            LOG.info("Credit/Debit card payment option is not displayed");
            return false;
        }
    }

    @Step
    @Override
    public boolean isPayNowButtonEnabledForCOD() {
        changePaymentType();
        return super.isPayNowButtonEnabledForCOD();
    }

    @Step
    @Override
    public PaymentPage viewDetailsFromPayment() {
        scrollTillElementVisible(getLocator("lnkViewDetails"));
        utils.waitForElementToBeVisible(getLocator("lnkViewDetails"));
        utils.click(getLocator("lnkViewDetails"), true);
        return this;
    }

    @Step
    @Override
    public PaymentPage payUsingNetBankingAvenuesTestAccount() {
        scrollTillElementVisible(getLocator("tabNetBanking"));
        return super.payUsingNetBankingAvenuesTestAccount();
    }
    @Step
    @Override
    public PaymentPage getPaymentPagePriceDetails() {
        scrollTillElementVisible(getLocator("lnkViewDetails"),Direction.DOWN);
        utils.waitForElementToBeVisible(getLocator("lnkViewDetails"));
        return super.getPaymentPagePriceDetails();
    }
}
