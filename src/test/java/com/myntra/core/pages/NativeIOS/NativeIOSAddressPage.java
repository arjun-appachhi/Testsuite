package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.PaymentPage;
import com.myntra.ui.Direction;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NativeIOSAddressPage extends AddressPage {

    @Step
    @Override
    public AddressPage removeAddress() {
        utils.click(getLocator("btnEditChange"));
        utils.click(getLocator("btnRemove"));
        return this;
    }

    @Step
    @Override
    public AddressPage addAddress() {
        nativeView();
        utils.waitForElementToBeVisible(getLocator("addressHeader"));
        webView();
        utils.waitForElementToBeVisible(getLocator("txtPincode"));
        utils.sendKeys(getLocator("txtPincode"), getAddressTestData().getPincode());
        utils.findElement(getLocator("lnkChoose"))
             .click();
        utils.sendKeys(getLocator("txtLocality"), getAddressTestData().getLocality());
        utils.findElement(getLocator("btnDoneKeypad"))
             .click();
        utils.swipeDown_m(1);
        utils.sendKeys(getLocator("txtName"), getAddressTestData().getName());
        utils.sendKeys(getLocator("txtAddress"), getAddressTestData().getAddress());
        utils.sendKeys(getLocator("txtMobileNumber"), getUserTestData().getPhoneDetails()
                                                                       .get(0)
                                                                       .getPhone());
        selectAddressType();
        utils.findElement(getLocator("btnSaveAddress"))
             .click();
        return this;
    }

    @Step
    @Override
    protected void selectAddressType() {
        String rdoType = getAddressTestData().getAddressType()
                                             .toLowerCase();
        if (rdoType.contains("home")) {
            utils.findElement(getLocator("rdoHome"))
                 .click();
        } else if (rdoType.contains("rdoOffice")) {
            utils.findElement(getLocator(rdoType))
                 .click();
        }
    }

    @Step
    @Override
    public PaymentPage addressContinue() {
        utils.waitForElementToBeVisible(getLocator("btnContinue"));
        utils.click(getLocator("btnContinue"), true);
        return PaymentPage.createInstance();
    }

    @Step
    @Override
    protected boolean isEmptyAddressMsgPresent() {
        return utils.isElementPresent(getLocator("lblEmptyAddress"), 3);
    }

    @Step
    @Override
    public AddressPage emptyAddress() {
        if (!isEmptyAddressMsgPresent()) {
            removeAllAddresses();
        }
        utils.waitForElementToBeVisible(getLocator("tlbBack"));
        utils.click(getLocator("tlbBack"), true);
        return this;
    }

    @Step
    @Override
    public AddressPage editAddress() {
        utils.click(getLocator("btnEditChange"), true);
        if (utils.isElementPresent(getLocator("btnEdit"), 3)) {
            utils.click(getLocator("btnEdit"), true);
            utils.findElement(getLocator("txtName"), 3)
                 .clear();
            utils.sendKeys(getLocator("txtName"), (String) getAddressTestData().getAdditionalProperties()
                                                                               .get("editedName"));
        }
        utils.swipeDown_m(2);
        utils.click(getLocator("btnSaveAddress"), true);

        return AddressPage.createInstance();
    }

    @Step
    @Override
    public AddressPage removeAllAddresses() {
        while (!isEmptyAddressMsgPresent()) {
            utils.waitForElementToBeVisible(getLocator("btnRemove"));
            utils.click(getLocator("btnRemove"), true);
            utils.waitForElementToBeVisible(getLocator("btnDelete"));
            utils.click(getLocator("btnDelete"), true);
        }
        return this;
    }

    @Step
    @Override
    public boolean isAddressPageDisplayed() {
        return (utils.isElementPresent(getLocator("btnEditChange"), 5));
    }

    @Step
    @Override
    public boolean isEditAddressSuccessful() {
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveAddress")));
        utils.scroll(Direction.DOWN, 1);
        return (utils.findElement(getLocator("txaEditedName"))
                     .getText()
                     .equalsIgnoreCase((String) getAddressTestData().getAdditionalProperties()
                                                                    .get("editedName")));
    }

    @Step
    @Override
    public boolean isProductDeliverable() {
        return !utils.findElement(By.className("serviceability-error"))
                     .isDisplayed();
    }

    @Step
    @Override
    public boolean isPriceDetailsDisplayed() {
        utils.swipeDown_m(1);
        return (utils.isElementPresent(getLocator("lnkHideDetails"), 2));
    }
}