package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.HomePage;
import com.myntra.core.pages.LoginPage;
import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;


public class NativeAndroidLoginPage extends LoginPage {

    @Step
    @Override
    protected void isLoaded() {
        nativeView();
        Assert.assertTrue(utils.isElementPresent(getLocator("btnOBSLogin"), 10), String.format("Page - %s - NOT Loaded", getClass().getSimpleName()));
    }

    @Step
    @Override
    protected void load() {
        if (!utils.isElementPresent(getLocator("btnOBSLogin"), 10)) {
            goBack();
        }
    }

    @Step
    @Override
    public HomePage login() {
        utils.findElement(getLocator("txtEmailId"))
             .sendKeys(getUserTestData().getEmail());
        utils.findElement(getLocator("txtPassword"))
             .sendKeys(getUserTestData().getPassword());
        utils.click(getLocator("btnLogin"), true);
        return HomePage.createInstance();
    }

    @Step
    @Override
    public LoginPage navigateToLogin() {
        utils.click(getLocator("lnkLogIn"), true);
        return this;
    }

    @Step
    @Override
    public boolean isLoginOptionDisplayed() {
        return utils.isElementPresent(getLocator("btnOBSLogin"), 5);
    }

    @Step
    @Override
    public AddressPage signUpAfterPlacingOrder() {
        utils.click(getLocator("lnkSignUp"));
        addSignUpUserDetails();
        ShoppingBagPage.createInstance().placeOrder();
        return AddressPage.createInstance();
    }

    @Step
    private void addSignUpUserDetails() {
        createNewUserTestData();
        utils.findElement(getLocator("txtSignUpEmail")).sendKeys(getUserTestData().getEmail());
        utils.findElement(getLocator("txtSignUpPassword")).sendKeys(getUserTestData().getPassword());
        utils.findElement(getLocator("txtSignUpFname")).sendKeys((String) getTestData().getAdditionalProperties().get("firstname"));
        utils.findElement(getLocator("txtSignUpMobile")).sendKeys(getUserTestData().getPhoneDetails()
                                                                                   .get(0)
                                                                                   .getPhone());
        utils.click(getLocator("btnGenderM"), true);
        hideKeyboard();
        utils.click(getLocator("btnCreateAccount"), true);
    }

    @Step
    @Override
    public HomePage signUp() {
        utils.click(getLocator("lnkSignUp"));
        utils.click(getLocator("lblEMAILADDRESS"));
        addSignUpUserDetails();
        return HomePage.createInstance();
    }
}
