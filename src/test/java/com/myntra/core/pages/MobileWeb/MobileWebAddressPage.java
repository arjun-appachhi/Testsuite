package com.myntra.core.pages.MobileWeb;

import com.myntra.core.pages.AddressPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class MobileWebAddressPage extends AddressPage {

    @Override
    @Step
    public AddressPage addAddress() {
        utils.waitForElementToBeVisible(getLocator("txtPincode"));
        utils.click(getLocator("txtPincode"), true);
        utils.sendKeys(getLocator("txtPincode"), getAddressTestData().getPincode());
        utils.sendKeys(getLocator("txtName"), getAddressTestData().getName());
        utils.sendKeys(getLocator("txtAddress"), getAddressTestData().getAddress());
        scrollTillElementVisible(getLocator("rdoHome"));
        selectAddressType();
        WebElement txtLocality = utils.findElement(getLocator("txtLocality"));
        txtLocality.sendKeys(getAddressTestData().getLocality());
        txtLocality.sendKeys(Keys.ENTER);
        utils.sendKeys(getLocator("txtMobileNumber"), getUserTestData().getPhoneDetails()
                                                                       .get(0)
                                                                       .getPhone());
        utils.waitForElementToBeVisible(getLocator("btnSaveAddress"));
        utils.click(getLocator("btnSaveAddress"), true);
        return this;
    }

    @Step
    @Override
    public AddressPage viewDetails() {
        utils.click(getLocator("lnkViewDetails"), true);
        return this;
    }

    @Step
    @Override
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
}
