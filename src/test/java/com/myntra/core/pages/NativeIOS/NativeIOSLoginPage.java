package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.HomePage;
import com.myntra.core.pages.LoginPage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

import java.time.Duration;

public class NativeIOSLoginPage extends LoginPage {

    @Step
    @Override
    public HomePage login() {
        utils.sendKeys(getLocator("txtEmailId"), getUserTestData().getEmail());
        utils.sendKeys(getLocator("txtPassword"), getUserTestData().getPassword());
        utils.waitForElementToBeVisible(getLocator("btnLogin"));
        utils.click(getLocator("btnLogin"));
        handleNotificationsAlert();
        return HomePage.createInstance();
    }

    @Step
    private void handleNotificationsAlert() {
        //Moving App in background state to handle the Notifications Alert applied only for Native iOS channel
        ((IOSDriver) getDriver()).runAppInBackground(Duration.ofSeconds(10));
        utils.waitForElementToBeVisible(MobileBy.AccessibilityId("Allow"));
        utils.click(MobileBy.AccessibilityId("Allow"));
    }

    @Step
    @Override
    public LoginPage navigateToLogin() {
        handleOnBaordingScreen();
        LOG.debug(String.format("(%s) Logging in...", getChannelUtils()));
        utils.click(getLocator("icoProfile"));
        utils.findElement(getLocator("lnkLogin"))
             .click();
        utils.waitForElementToBeVisible(getLocator("txtEmailId"));
        return this;
    }

    @Step
    private void handleOnBaordingScreen() {
        String OBSCloseIcon = "//XCUIElementTypeOther[@name=\"WELCOME!\"]/XCUIElementTypeOther";
        boolean isOBSAvailable = isOBSAavailable(OBSCloseIcon);
        if (isOBSAvailable) {
            LOG.info("On Boarding screen is displayed");
            skipOBS();
            LOG.info("Dismissed On-Boarding-screen");
        } else {
            LOG.info("On Boarding screen is not displayed");
        }
    }

    @Step
    private boolean isOBSAavailable(String OBSCloseIcon) {
        return utils.isElementPresent(MobileBy.xpath(OBSCloseIcon), 5);
    }

    @Step
    @Override
    public boolean isLoginOptionDisplayed() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    @Override
    public AddressPage signUpAfterPlacingOrder() {
        createNewUserTestData();

        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    @Override
    public HomePage signUp() {
        signUpAfterPlacingOrder();

        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }

    @Step
    private void skipOBS() {
        utils.findElement(MobileBy.AccessibilityId("HAVE AN ACCOUNT? LOG IN")).click();
        utils.findElement(MobileBy.AccessibilityId("SKIP")).click();
    }
}