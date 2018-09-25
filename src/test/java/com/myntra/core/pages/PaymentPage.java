package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopPaymentPage;
import com.myntra.core.pages.MobileWeb.MobileWebPaymentPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidPaymentPage;
import com.myntra.core.pages.NativeIOS.NativeIOSPaymentPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.ui.SelectBy;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Calendar;
import java.util.HashMap;

public abstract class PaymentPage extends Page {

    public static PaymentPage createInstance() {
        PaymentPage derivedPaymentPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedPaymentPage = (PaymentPage) DynamicEnhancer.create(NativeAndroidPaymentPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedPaymentPage = (PaymentPage) DynamicEnhancer.create(NativeIOSPaymentPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedPaymentPage = (PaymentPage) DynamicEnhancer.create(DesktopPaymentPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedPaymentPage = (PaymentPage) DynamicEnhancer.create(MobileWebPaymentPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedPaymentPage;
    }

    @Override
    public String pageName() {
        return PaymentPage.class.getSimpleName();
    }

    @Step
    public PaymentPage bankOffersFromPayment() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public PaymentPage selectForEligibilityFromPayment() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public PaymentPage payUsingSavedCard() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public PaymentPage payUsingCreditAndDebitCard() {
        utils.click(getLocator("tabCreditCard"), true);
        utils.sendKeys(getLocator("txtCreditCardNumber"), (String) getTestData().getAdditionalProperties()
                                                                                .get("CreditCardNumber"));
        utils.sendKeys(getLocator("txtNameOnCreditCard"), (String) getTestData().getAdditionalProperties()
                                                                                .get("NameOnCreditCard"));
        utils.select(getLocator("ddlExpiryMonthField"), SelectBy.VALUE, String.format("%02d", ((Calendar.getInstance()
                                                                                                        .get(Calendar.MONTH) + 1) % 12)));
        utils.select(getLocator("ddlExpiryYearField"), SelectBy.VISIBLE_TEXT, String.format("%s", (Calendar.getInstance()
                                                                                                           .get(Calendar.YEAR) + 2)));
        utils.sendKeys(getLocator("txtCVV"), (String) getTestData().getAdditionalProperties()
                                                                   .get("CVV"));
        utils.click(getLocator("btnPayNowForCreditCardPayment"), true);
        try
        {
            getDriver().switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
            LOG.info("No alert available in payments screen");
        }
        return this;
    }

    @Step
    public PaymentPage payUsingNetBankingAvenuesTestAccount() {
        utils.click(getLocator("tabNetBanking"), true);
        utils.click(getLocator("icoAvenuesTest"), true);
        utils.click(getLocator("btnNetbankingPayNow"), true);
        try
        {
            getDriver().switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
            LOG.info("No alert available in payments screen");
        }
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnNetbankingPayNow")));

        return this;
    }

    @Step
    public OrderConfirmationPage payUsingCashOnDelivery() {
        utils.click(getLocator("tabCOD"), true);
        if (!utils.findElement(getLocator("btnPayOnDelivery"))
                  .isEnabled()) {
            throw new ElementClickInterceptedException("Confirm button is not clickable :: Disabled");
        }
        utils.click(getLocator("btnPayOnDelivery"), true);
        try
        {
            getDriver().switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
            LOG.info("No alert available in payments screen");
        }
        return OrderConfirmationPage.createInstance();
    }

    @Step
    public OrderConfirmationPage payUsingPhonePe() {
        utils.click(getLocator("tabPhonePe"), true);
        try
        {
            getDriver().switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
            LOG.info("No alert available in payments screen");
        }
        return OrderConfirmationPage.createInstance();
    }

    @Step
    public PaymentPage payUsingGiftCard() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public PaymentPage payUsingWallets() {
        // TODO - Wallet implementation needs to be done.test data required
        utils.click(getLocator("tabWallets"), true);
        try
        {
            getDriver().switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
            LOG.info("No alert available in payments screen");
        }
        return this;
    }

    @Step
    public PaymentPage payUsingEMI() {
        // TODO - EMI implementation needs to be done.test data required
        utils.click(getLocator("tabWallets"), true);
        try
        {
            getDriver().switchTo().alert().accept();
       }
        catch (NoAlertPresentException Ex)
        {
            LOG.info("No alert available in payments screen");
        }
        return this;
    }

    @Step
    public PaymentPage viewDetailsFromPayment() {
        utils.waitForElementToBeVisible(getLocator("lnkViewDetails"));
        utils.click(getLocator("lnkViewDetails"), true);
        return this;
    }

    @Step
    public PaymentPage changeAddressFromPayment() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    public boolean isPaymentPageDisplayed() {
        return (utils.isElementPresent(getLocator("txtPaymentHeader"), 5));
    }

    @Step
    public boolean isPaymentOptionEnabled(String locatorName) {
        boolean isPaymentOptionDisplayed = false;
        switch (locatorName.toLowerCase()) {
            case "cod":
                isPaymentOptionDisplayed = isCODPaymentOptionEnabled();
                break;
            case "phonepe":
                isPaymentOptionDisplayed = isPhonePePaymentOptionDisplayed();
                break;
            case "creditcard":
                isPaymentOptionDisplayed = isCCDCPaymentOptionEnabled();
                break;
            case "netbanking":
                isPaymentOptionDisplayed = isNetbankingPaymentOptionEnabled();
                break;
            default:
                throw new NotImplementedException("Invalid payment option");
        }
        return isPaymentOptionDisplayed;
    }

    @Step
    public PaymentPage payUsingMyntraPoint() {
        utils.waitForElementToBeVisible(getLocator("chkMyntraPoint"));
        utils.click(getLocator("chkMyntraPoint"), true);
        try
        {
            getDriver().switchTo().alert().accept();
       }
        catch (NoAlertPresentException Ex)
        {
            LOG.info("No alert available in payments screen");
        }
        return this;
    }

    @Step
    public boolean isPhonePePageDisplayed() {
        return (utils.isElementPresent(getLocator("txtPayByPhonePe"), 10));
    }

    @Step
    protected boolean isCODPaymentOptionEnabled() {
        return utils.isElementPresent(getLocator("tabCOD"), 10);
    }

    @Step
    protected boolean isNetbankingPaymentOptionEnabled() {
        return utils.isElementPresent(getLocator("tabNetBanking"), 10);
    }

    @Step
    protected boolean isCCDCPaymentOptionEnabled() {
        return utils.isElementPresent(getLocator("tabCreditCard"), 10);
    }

    @Step
    private boolean isPhonePePaymentOptionDisplayed() {
        return utils.isElementPresent(getLocator("tabPhonePe"), 5);
    }

    @Step
    public boolean isPayNowButtonEnabledForCOD() {
        if (isCODPaymentOptionEnabled()) {
            utils.click(getLocator("tabCOD"));
            return (utils.findElement(getLocator("btnPayOnDelivery"))
                         .isEnabled());
        } else {
            LOG.info("COD payment option is not Enabled");
            return false;
        }

    }

    @Step
    public boolean isPayNowButtonEnabledForNetbanking() {
        if (isNetbankingPaymentOptionEnabled()) {
            utils.click(getLocator("tabNetBanking"));
            return (utils.findElement(getLocator("btnNetbankingPayNow"))
                         .isEnabled());
        } else {
            LOG.info("Netbanking payment option is not displayed");
            return false;
        }
    }

    @Step
    public boolean isPayNowButtonEnabledForCCDC() {
        if (isCCDCPaymentOptionEnabled()) {
            utils.click(getLocator("tabCreditCard"));
            return (utils.findElement(getLocator("btnPayNowForCreditCardPayment"))
                         .isEnabled());
        } else {
            LOG.info("Credit/Debit card payment option is not displayed");
            return false;
        }
    }

    @Step
    protected void changePaymentType() {
        if (utils.findElement(getLocator("lnkChangePayment"))
                 .isDisplayed()) {
            utils.click(getLocator("lnkChangePayment"));
        } else {
            LOG.info("change Payment Type is not displayed");
        }
    }

    @Step
    public PaymentPage getPaymentPagePriceDetails() {
        viewDetailsFromPayment();
        utils.waitForElementToBeVisible(getLocator("lblBagTotal"));
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

    @Step
    public boolean isPaymentPageDetailsSameAsBag() {
        HashMap<String, String> addressPagePriceDetails = (HashMap<String, String>) testExecutionContext.getTestState("addressPagePriceDetails");
        HashMap<String, String> paymentPagePriceDetails = (HashMap<String, String>) testExecutionContext.getTestState("paymentPagePriceDetails");
        boolean isPriceDetailsSame = false;
        for (String y : addressPagePriceDetails.keySet()) {
            if (paymentPagePriceDetails.containsKey(y)) {
                isPriceDetailsSame = addressPagePriceDetails.get(y).equals(paymentPagePriceDetails.get(y));
            }
        }
        return isPriceDetailsSame;
    }
}
