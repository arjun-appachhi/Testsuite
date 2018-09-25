package com.myntra.core.pages.MobileWeb;

import com.myntra.core.pages.PaymentPage;
import com.myntra.ui.Direction;
import com.myntra.ui.SelectBy;
import io.qameta.allure.Step;

import java.util.Calendar;
import java.util.HashMap;

public class MobileWebPaymentPage extends PaymentPage {

    @Step
    @Override
    public boolean isPayNowButtonEnabledForCOD() {
        if (isCODPaymentOptionEnabled()) {
            utils.click(getLocator("tabCOD"),true);
            utils.findElement(getLocator("btnPayOnDelivery"))
                 .isEnabled();
            return true;
        } else {
            LOG.info("COD payment option is not Enabled");
            return false;
        }

    }

    @Step
    @Override
    public boolean isPayNowButtonEnabledForNetbanking() {
        if (isNetbankingPaymentOptionEnabled()) {
            utils.click(getLocator("tabNetBanking"),true);
            utils.click(getLocator("icoAvenuesTest"), true);
            utils.findElement(getLocator("btnNetbankingPayNow"))
                 .isEnabled();
            utils.click(getLocator("lnkChangePaymentMode"), true);
            return true;
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
            utils.findElement(getLocator("btnPayNowForCreditCardPayment"))
                 .isEnabled();
            utils.click(getLocator("lnkChangePaymentMode"), true);
            return true;
        } else {
            LOG.info("Credit/Debit card payment option is not displayed");
            return false;
        }
    }

    @Step
    private void fillCreditCardDetails() {
        utils.click(getLocator("tabCreditCard"), true);
        utils.waitForElementToBeVisible(getLocator("txtCreditCardNumber"));
        utils.findElement(getLocator("txtCreditCardNumber"))
             .sendKeys((String) getTestData().getAdditionalProperties()
                                             .get("CreditCardNumber"));
        utils.findElement(getLocator("txtNameOnCreditCard"))
             .sendKeys((String) getTestData().getAdditionalProperties()
                                             .get("NameOnCreditCard"));
        utils.select(getLocator("ddlExpiryMonthField"), SelectBy.VALUE, String.format("%02d", ((Calendar.getInstance()
                                                                                                        .get(Calendar.MONTH) +
                1) % 12)));
        utils.select(getLocator("ddlExpiryYearField"), SelectBy.VISIBLE_TEXT, String.format("%s",
                (Calendar.getInstance()
                         .get(Calendar.YEAR) + 2)));
        utils.findElement(getLocator("txtCVV"))
             .sendKeys((CharSequence) getTestData().getAdditionalProperties()
                                                   .get("CVV"));
    }

    @Step
    @Override
    public PaymentPage getPaymentPagePriceDetails() {
        viewDetailsFromPayment();
        String bagTotal = utils.getText(getLocator("lblBagTotal")).split(" ")[1].replace(",", "");
        String delivery = utils.getText(getLocator("lblDelivery")).split(" ")[0].replace(",", "");
        String orderTotal = utils.getText(getLocator("lblOrderTotal")).split(" ")[1].replace(",", "");
        HashMap<String, String> paymentPagePriceDetails = new HashMap<String, String>();
        paymentPagePriceDetails.put("bagTotal", bagTotal);
        paymentPagePriceDetails.put("delivery", delivery);
        paymentPagePriceDetails.put("orderTotal", orderTotal);
        if (utils.isElementPresent(getLocator("lblCouponDiscount"), 2)) {
            String couponDiscount = utils.getText(getLocator("lblCouponDiscount")).split(" ")[2].replace(",", "");
            paymentPagePriceDetails.put("couponDiscount", couponDiscount);
        }
        if (utils.isElementPresent(getLocator("lblBagDiscount"), 2)) {
            String bagDiscount = utils.getText(getLocator("lblBagDiscount")).split(" ")[2].replace(",", "");
            paymentPagePriceDetails.put("bagDiscount", bagDiscount);
        }
        if (utils.isElementPresent(getLocator("lblEstimatedTax"), 2)) {
            String estimatedTax = utils.getText(getLocator("lblEstimatedTax")).split(" ")[1].replace(",", "");
            paymentPagePriceDetails.put("estimatedTax", estimatedTax);
        }
        testExecutionContext.addTestState("paymentPagePriceDetails", paymentPagePriceDetails);
        return this;
    }
}
