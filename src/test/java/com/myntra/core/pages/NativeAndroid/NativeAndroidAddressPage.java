package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.PaymentPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NativeAndroidAddressPage extends AddressPage {

    @Step
    @Override
    public AddressPage addAddress() {
        utils.isElementPresent(getLocator("btnContinue"), 3);
        utils.sendKeys(getLocator("txtPincode"), getAddressTestData().getPincode());
        utils.findElement(getLocator("txtLocality"))
             .sendKeys(getAddressTestData().getLocality());
        utils.sendKeys(getLocator("txtName"), getAddressTestData().getName());
        utils.sendKeys(getLocator("txtAddress"), getAddressTestData().getAddress());
        utils.sendKeys(getLocator("txtMobileNumber"), getUserTestData().getPhoneDetails()
                                                                       .get(0)
                                                                       .getPhone());
        selectAddressType();
        utils.isElementPresent(getLocator("btnRemove"), 2);
        hideKeyboard();
        utils.click(getLocator("btnSaveAddress"), true);
        return this;
    }

    @Step
    @Override
    public AddressPage removeAddress() {
        utils.waitForElementToBeVisible(getLocator("btnEditChangeAfterPlacingOrder"));
        utils.click(getLocator("btnEditChangeAfterPlacingOrder"), true);
        utils.click(getLocator("btnRemove"), true);
        utils.waitForElementToBeVisible(getLocator("txtPincode"));
        return this;
    }

    @Step
    @Override
    public PaymentPage addressContinue() {
        utils.waitForElementToBeVisible(getLocator("btnContinue"));
        utils.click(getLocator("btnContinue"));
        return PaymentPage.createInstance();
    }

    @Step
    @Override
    protected boolean isEmptyAddressMsgPresent() {
        webView();
        return utils.isElementPresent(getLocator("lblEmptyAddress"), 3);
    }

    @Step
    @Override
    public AddressPage emptyAddress() {
        if (isEmptyAddressMsgPresent()) {
            nativeView();
            goBack();
            goBack();
        } else {
            removeAllAddresses();
            nativeView();
            goBack();
            goBack();
        }
        return this;
    }
    @Step
    @Override
    public AddressPage removeAllAddresses() {
        int addressCount = 0;
        int maxAddressCount = 10;
        while (!isEmptyAddressMsgPresent() && (addressCount < maxAddressCount)) {
            utils.click(getLocator("btnRemove"), true);
            webView();
            utils.click(getLocator("btnDeleteConfirmation"));
            nativeView();
            addressCount++;
        }
        Assert.assertTrue((addressCount < maxAddressCount),
                String.format("Address page is not functional/More number of addresses found - Made %d attempts to empty address", maxAddressCount));
        return this;
    }

    @Step
    @Override
    public boolean isEditAddressSuccessful() {
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveAddress")));
        return (utils.findElement(getLocator("txaEditedName"))
                     .getText()
                     .equalsIgnoreCase((String) getAddressTestData().getAdditionalProperties()
                                                                    .get("editedName")));
    }

    @Step
    @Override
    public boolean isAddressPageDisplayed() {
        return (utils.isElementPresent(getLocator("btnEditChangeAfterPlacingOrder"), 2));
    }

    @Step
    @Override
    public AddressPage viewDetails() {
        scrollTillElementVisible(getLocator("lnkViewDetails"));
        utils.waitForElementToBeVisible(getLocator("lnkViewDetails"));
        utils.click(getLocator("lnkViewDetails"), true);
        return this;
    }

    @Step
    @Override
    public boolean isProductDeliverable() {
        // TODO Myntra team please remove this override once taking screenshot in web view is fixed;
        boolean isProductDeliverable = super.isProductDeliverable();
        if (!isProductDeliverable) {
            nativeView();
        }
        return isProductDeliverable;
    }

    @Step
    @Override
    public AddressPage editAddress() {
        webView();
        scrollTillElementVisible(getLocator("btnEditChangeAfterPlacingOrder"));
        utils.click(getLocator("btnEditChangeAfterPlacingOrder"), true);
        utils.click(getLocator("btnEdit"), true);
        utils.findElement(getLocator("txtName"))
             .clear();
        utils.sendKeys(getLocator("txtName"), (String) getAddressTestData().getAdditionalProperties()
                                                                           .get("editedName"));
        utils.isElementPresent(getLocator("btnEditChangeAfterPlacingOrder"), 5);
        selectAddressType();
        hideKeyboard();
        utils.wait(ExpectedConditions.elementToBeClickable(getLocator("btnSaveAddress")), 5);
        utils.click(getLocator("btnSaveAddress"), true);
        return AddressPage.createInstance();
    }

    @Step
    @Override
    public AddressPage clickOnAddButton() {
        webView();
        return super.clickOnAddButton();
    }

    @Step
    @Override
    protected void selectAddressType() {
        String rdoType = getAddressTestData().getAddressType()
                                             .toLowerCase();
        if (rdoType.contains("home")) {
            utils.click(getLocator("rdoHome"), true);
            checkAddressTypeSelected("rdoHome");
        } else if (rdoType.contains("office")) {
            utils.click(getLocator("rdoOffice"), true);
            checkAddressTypeSelected("rdoOffice");
        }
    }

    @Step
    private void checkAddressTypeSelected(String locatorValue) {
        if (!utils.isElementPresent(getLocator("lblAddressTypeSelected"), 3)) {
            utils.click(getLocator(locatorValue), true);
        } else {
            LOG.info(String.format("Address type is selected for %s value", locatorValue));
        }
    }
}
