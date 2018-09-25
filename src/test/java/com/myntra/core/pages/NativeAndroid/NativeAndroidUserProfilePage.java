package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.UserProfilePage;
import com.myntra.ui.Direction;
import com.myntra.ui.SelectBy;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.myntra.core.utils.JavaUtils.generateUserNameForTest;

public class NativeAndroidUserProfilePage extends UserProfilePage {

    @Step
    @Override
    public UserProfilePage editUserAccountDetails() {
        if (!utils.isElementPresent(getLocator("txaFirstName"),5)){
            scrollTillElementVisible(getLocator("lblEditProfile"),Direction.DOWN);
            utils.click(getLocator("lblEditProfile"));
        }
        utils.findElement(getLocator("txaFirstName"))
             .clear();
        utils.sendKeys(getLocator("txaFirstName"),(String) getTestData().getAdditionalProperties()
                                                                        .get("firstname"));
        utils.click(getLocator("btnGender"));
        utils.click(getLocator("selectGenderMale"));
        utils.findElement(getLocator("txaMobileNumber"))
             .clear();
        utils.sendKeys(getLocator("txaMobileNumber"), getUserTestData().getPhoneDetails()
                                                                       .get(0)
                                                                       .getPhone());
        scrollTillElementVisible(getLocator("lblBirthday"),Direction.DOWN);
        utils.click(getLocator("lblBirthday"));
        utils.click(getLocator("drpDobMonth"));
        utils.click(getLocator("drpDobDate"));
        utils.click(getLocator("drpDobYear"));
        utils.click(getLocator("saveBirthday"));
        scrollTillElementVisible(getLocator("txaLocation"),Direction.DOWN);
        utils.click(getLocator("txaLocation"));
        utils.click(getLocator("selectLocation"));
        utils.click(getLocator("btnSave"), true);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSave")), 5);
        return this;
    }
    @Step
    @Override
    public UserProfilePage edit() {
        scrollTillElementVisible(getLocator("lblEditProfile"), Direction.DOWN);
        utils.click(getLocator("lblEditProfile"));
        return this;
    }

    @Step
    @Override
    public UserProfilePage changePassword() {
        utils.click(getLocator("lblEditProfile"), true);
        scrollTillElementVisible(getLocator("lnkChangePassword"),Direction.DOWN);
        if (utils.isElementPresent(getLocator("lnkChangePassword"),5)){
            utils.swipeDown_m(2);
        }
        utils.click(getLocator("lnkChangePassword"), true);
        webView();
        utils.waitForElementToBeVisible(getLocator("txaCurrentPassword"));
        utils.findElement(getLocator("txaCurrentPassword")).sendKeys(getUserTestData().getPassword());
        utils.findElement(getLocator("txaNewPassword")).sendKeys("new"+getUserTestData().getPassword());
        utils.findElement(getLocator("txaConfirmPassword")).sendKeys("new"+getUserTestData().getPassword());
        hideKeyboard();
        utils.click(getLocator("btnChangePassword"), true);
        return this;
    }

    @Step
    @Override
    public boolean isPasswordChangedSuccessfully() {
        String successMessage = "Password has been successfully changed";
        utils.wait(ExpectedConditions.textToBePresentInElementLocated(getLocator("msgPasswordchange"), successMessage));
        boolean isSuccessMessageAvailabe = utils.findElement(getLocator("msgPasswordchange"))
                                                .getText().equals(successMessage);
        return isSuccessMessageAvailabe;
    }
}
