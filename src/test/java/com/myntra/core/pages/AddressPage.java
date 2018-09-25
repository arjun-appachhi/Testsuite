package com.myntra.core.pages;

import com.myntra.core.enums.ChannelUtils;
import com.myntra.core.pages.Desktop.DesktopAddressPage;
import com.myntra.core.pages.MobileWeb.MobileWebAddressPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidAddressPage;
import com.myntra.core.pages.NativeIOS.NativeIOSAddressPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.ui.Direction;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.HashMap;

public abstract class AddressPage extends Page {

    public static AddressPage createInstance() {
        AddressPage derivedAddressPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedAddressPage = (AddressPage) DynamicEnhancer.create(NativeAndroidAddressPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedAddressPage = (AddressPage) DynamicEnhancer.create(NativeIOSAddressPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedAddressPage = (AddressPage) DynamicEnhancer.create(DesktopAddressPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedAddressPage = (AddressPage) DynamicEnhancer.create(MobileWebAddressPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedAddressPage;
    }

    @Override
    public String pageName() {
        return AddressPage.class.getSimpleName();
    }

    @Step
    public PaymentPage addressContinue() {
        utils.wait((ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveAddress"))));
        utils.click(getLocator("btnContinue"), true);
        return PaymentPage.createInstance();
    }

    @Step
    public AddressPage removeAddress() {
        utils.click(getLocator("btnEditChangeAfterPlacingOrder"), true);
        utils.click(getLocator("btnRemove"), true);
        return this;
    }

    @Step
    public AddressPage emptyAddress() {
        if (isEmptyAddressMsgPresent()) {
            utils.waitForElementToBeVisible(getLocator("tlbBack"));
            utils.click(getLocator("tlbBack"), true);
        } else {
            removeAllAddresses();
            utils.click(getLocator("tlbBack"), true);
        }
        return this;
    }

    @Step
    public AddressPage addAddress() {
        utils.waitForElementToBeVisible(getLocator("txtPincode"));
        utils.click(getLocator("txtPincode"), true);
        utils.sendKeys(getLocator("txtPincode"), getAddressTestData().getPincode());
        utils.sendKeys(getLocator("txtName"), getAddressTestData().getName());
        utils.sendKeys(getLocator("txtAddress"), getAddressTestData().getAddress());
        utils.scroll(Direction.DOWN);
        selectAddressType();
        if (getChannelUtils() == ChannelUtils.DESKTOP) {
            utils.waitForElementToBeVisible(getLocator("txtLocality"));
            utils.click(getLocator("txtLocality"), true);
            utils.sendKeys(getLocator("txtLocality"), getAddressTestData().getLocality());
        } else {
            utils.click(getLocator("lnkChoose"), true);
            WebElement txtLocality = utils.findElement(getLocator("txtLocality"));
            txtLocality.sendKeys(getAddressTestData().getLocality());
            txtLocality.sendKeys(Keys.ENTER);
        }
        utils.sendKeys(getLocator("txtMobileNumber"), getUserTestData().getPhoneDetails()
                                                                       .get(0)
                                                                       .getPhone());
        utils.waitForElementToBeVisible(getLocator("btnSaveAddress"));
        utils.click(getLocator("btnSaveAddress"), true);
        return this;
    }

    @Step
    public boolean isAddressRemovedSuccessfully() {
        return utils.isElementPresent(getLocator("txaAddNewAddress"), 5);
    }

    @Step
    public boolean isEditAddressSuccessful() {
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveAddress")));
        utils.scroll(Direction.DOWN, 1);
        return (utils.findElement(getLocator("txaEditedName"))
                     .getText()
                     .equalsIgnoreCase((String) getAddressTestData().getAdditionalProperties()
                                                                    .get("editedName")));
    }

    @Step
    public boolean isAddressAddedSuccessfullyAfterPlacingOrder() {
        return (utils.isElementPresent(getLocator("btnEditChangeAfterPlacingOrder"), 5));
    }

    @Step
    public boolean isAddressAddedSuccessfully() {
        return (utils.isElementPresent(getLocator("btnEditChange"), 5));
    }

    @Step
    protected void selectAddressType() {
        String rdoType = getAddressTestData().getAddressType()
                                             .toLowerCase();
        if (rdoType.contains("home")) {
            utils.click(getLocator("rdoHome"), true);
        } else if (rdoType.contains("office")) {
            utils.click(getLocator("rdoOffice"), true);
        }
    }

    @Step
    public AddressPage editAddress() {
        utils.click(getLocator("btnEditChangeAfterPlacingOrder"), true);
        if (utils.isElementPresent(getLocator("btnEdit"), 3)) {
            utils.click(getLocator("btnEdit"), true);
            utils.findElement(getLocator("txtName"))
                 .clear();
            utils.sendKeys(getLocator("txtName"), (String) getAddressTestData().getAdditionalProperties()
                                                                               .get("editedName"));
        }
        utils.click(getLocator("btnSaveAddress"), true);
        return AddressPage.createInstance();
    }

    @Step
    protected boolean isEmptyAddressMsgPresent() {
        return utils.isElementPresent(getLocator("lblEmptyAddress"), 3);
    }

    @Step
    public AddressPage removeAllAddresses() {
        int addressCount = 0;
        int maxAddressCount = 10;
        while (!isEmptyAddressMsgPresent() && (addressCount < maxAddressCount)) {
            utils.click(getLocator("btnRemove"), true);
            utils.click(getLocator("btnDeleteConfirmation"),true);
            addressCount++;
        }
        Assert.assertTrue((addressCount < maxAddressCount),
                String.format("Address page is not functional/More number of addresses found - Made %d attempts to empty address", maxAddressCount));
        return this;
    }

    @Step
    public AddressPage viewDetails() {
        utils.wait((ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveAddress"))));
        utils.waitForElementToBeVisible(getLocator("lnkViewDetails"));
        utils.click(getLocator("lnkViewDetails"), true);
        return this;
    }

    @Step
    public boolean isAddressPageDisplayed() {
        return (utils.isElementPresent(getLocator("lnkDeliveryTitle"), 2));
    }

    @Step
    public boolean isPriceDetailsDisplayed() {
        return (utils.isElementPresent(getLocator("lnkHideDetails"), 2));
    }

    @Step
    public AddressPage clickOnAddButton() {
        utils.click(getLocator("btnAddInSavedAddress"), true);
        return this;
    }

    @Step
    public boolean isProductDeliverable() {
        boolean isProductNotDeliverableMessage = utils.isElementPresent(By.className("serviceability-error"), 4);
        if (isProductNotDeliverableMessage) {
            return !utils.findElement(By.className("serviceability-error"))
                         .isDisplayed();
        }
        return true;
    }

    @Step
    public AddressPage getAddressPagePriceDetails() {
        viewDetails();
        String bagTotal = utils.getText(getLocator("lblBagTotal")).split(" ")[1].replace(",", "");
        String delivery = utils.getText(getLocator("lblDelivery")).split(" ")[0].replace(",", "");
        String orderTotal = utils.getText(getLocator("lblOrderTotal")).split(" ")[1].replace(",", "");
        HashMap<String, String> addressPagePriceDetails = new HashMap<String, String>();
        addressPagePriceDetails.put("bagTotal", bagTotal);
        addressPagePriceDetails.put("delivery", delivery);
        addressPagePriceDetails.put("orderTotal", orderTotal);
        if (utils.isElementPresent(getLocator("lblCouponDiscount"), 2)) {
            String couponDiscount = utils.getText(getLocator("lblCouponDiscount")).split(" ")[2].replace(",", "");
            addressPagePriceDetails.put("couponDiscount", couponDiscount);
        }
        if (utils.isElementPresent(getLocator("lblBagDiscount"), 2)) {
            String bagDiscount = utils.getText(getLocator("lblBagDiscount")).split(" ")[2].replace(",", "");
            addressPagePriceDetails.put("bagDiscount", bagDiscount);
        }
        if (utils.isElementPresent(getLocator("lblEstimatedTax"), 2)) {
            String estimatedTax = utils.getText(getLocator("lblEstimatedTax")).split(" ")[1].replace(",", "");
            addressPagePriceDetails.put("estimatedTax", estimatedTax);
        }
        testExecutionContext.addTestState("addressPagePriceDetails", addressPagePriceDetails);
        return this;
    }

    @Step
    public boolean isAddressPageDetailsSameAsBag() {
        HashMap<String, String> bagPriceDetails = (HashMap<String, String>) testExecutionContext.getTestState("bagPriceDetails");
        HashMap<String, String> addressPagePriceDetails = (HashMap<String, String>) testExecutionContext.getTestState("addressPagePriceDetails");
        boolean isPriceDetailsSame = false;
        for (String y : bagPriceDetails.keySet()) {
            if (addressPagePriceDetails.containsKey(y)) {
                isPriceDetailsSame = bagPriceDetails.get(y).equals(addressPagePriceDetails.get(y));
            }
        }
        return isPriceDetailsSame;
    }
}
