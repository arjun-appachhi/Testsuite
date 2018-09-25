package com.myntra.core.business;

import com.myntra.core.pages.AddressPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class Address extends BusinessFlow {

    public static Address getInstance() {
        return (Address) getInstance(Of.ADDRESS);
    }

    @Step
    public Payment reEnterAddressAndProceedToPayment() {
        AddressPage addressPage = AddressPage.createInstance();
        Assert.assertTrue(addressPage.isAddressPageDisplayed(), "Address page is not dispalyed");
        addressPage.removeAddress()
                   .addAddress();
        Assert.assertTrue(addressPage.isAddressAddedSuccessfullyAfterPlacingOrder(), "Address is not added successfully");
        addressPage.addressContinue();
        return Payment.getInstance();
    }

    @Step
    public Payment editAddressAndProceedToPayment() {
        AddressPage addressPage = AddressPage.createInstance();
        Assert.assertTrue(addressPage.isAddressPageDisplayed(), "Address page is not dispalyed");
        addressPage.editAddress();
        Assert.assertTrue(addressPage.isEditAddressSuccessful(), "Edit Address is not successful");
        addressPage.addressContinue();
        return Payment.getInstance();
    }

    @Step
    public Address removeExistingAddress() {
        AddressPage addressPage = AddressPage.createInstance();
        Assert.assertTrue(addressPage.isAddressPageDisplayed(), "Address page is not dispalyed");
        addressPage.removeAddress();
        Assert.assertTrue(addressPage.isAddressRemovedSuccessfully(), "Address is not Removed successfully");
        return this;
    }

    @Step
    public Payment viewPriceDetailsAndProceedToPayment() {
        AddressPage addressPage = AddressPage.createInstance();
        addressPage.viewDetails();
        Assert.assertTrue(addressPage.isPriceDetailsDisplayed(), "Price Details are not displayed upon clicking 'View Details' link");
        addressPage.addressContinue();
        return Payment.getInstance();
    }

    @Step
    public Payment addAddressAfterSignUp() {
        AddressPage addressPage = AddressPage.createInstance()
                                             .addAddress();
        Assert.assertTrue(addressPage.isAddressAddedSuccessfullyAfterPlacingOrder(), "Address is not added successfully");
        addressPage.addressContinue();
        return new Payment();
    }

    @Step
    public Address removeSavedAddressAndVerify() {
        AddressPage addressPage = AddressPage.createInstance()
                                             .removeAllAddresses();
        Assert.assertTrue(addressPage.isAddressRemovedSuccessfully(), "Address is not Removed successfully");
        return this;
    }

    @Step
    public Payment addAddressGetPriceDetailsCompaireWithBag() {
        AddressPage addressPage = AddressPage.createInstance();
        boolean isAddressAddedSuccessfully = addressPage.addAddress().isAddressAddedSuccessfullyAfterPlacingOrder();
        Assert.assertTrue(isAddressAddedSuccessfully, "Address is not added successfully");
        boolean isAddressPageDetailsSameAsBag = addressPage.getAddressPagePriceDetails().isAddressPageDetailsSameAsBag();
        Assert.assertTrue(isAddressPageDetailsSameAsBag, "AddressPrice details are not same with bag price details");
        addressPage.addressContinue();
        return Payment.getInstance();
    }
}
