package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopUserProfilePage;
import com.myntra.core.pages.MobileWeb.MobileWebUserProfilePage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidUserProfilePage;
import com.myntra.core.pages.NativeIOS.NativeIOSUserProfilePage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.ui.SelectBy;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfilePage extends Page {

    public static UserProfilePage createInstance() {
        UserProfilePage derivedEditUserProfilePage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedEditUserProfilePage = (UserProfilePage) DynamicEnhancer.create(NativeAndroidUserProfilePage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedEditUserProfilePage = (UserProfilePage) DynamicEnhancer.create(NativeIOSUserProfilePage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedEditUserProfilePage = (UserProfilePage) DynamicEnhancer.create(DesktopUserProfilePage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedEditUserProfilePage = (UserProfilePage) DynamicEnhancer.create(MobileWebUserProfilePage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedEditUserProfilePage;
    }

    @Override
    public String pageName() {
        return UserProfilePage.class.getSimpleName();
    }

    @Step
    public UserProfilePage editUserAccountDetails() {
        utils.findElement(getLocator("txaFirstName"))
             .clear();
        utils.sendKeys(getLocator("txaFirstName"), (String) getTestData().getAdditionalProperties()
                                                                         .get("firstname"));
        utils.findElement(getLocator("txaLastName"))
             .clear();
        utils.sendKeys(getLocator("txaLastName"), (String) getTestData().getAdditionalProperties()
                                                                        .get("lastname"));
        utils.select(getLocator("drpDobDate"), SelectBy.INDEX, "1");
        utils.select(getLocator("drpDobMonth"), SelectBy.INDEX, "2");
        utils.select(getLocator("drpDobYear"), SelectBy.INDEX, "5");
        utils.findElement(getLocator("txaMobileNumber"))
             .clear();
        utils.sendKeys(getLocator("txaMobileNumber"), getUserTestData().getPhoneDetails()
                                                                       .get(0)
                                                                       .getPhone());
        utils.findElement(getLocator("txaYourBio"))
             .clear();
        utils.sendKeys(getLocator("txaYourBio"), (String) getTestData().getAdditionalProperties()
                                                                       .get("yourbio"));
        utils.findElement(getLocator("txaLocation"))
             .clear();
        utils.sendKeys(getLocator("txaLocation"), getAddressTestData().getLocality());
        utils.click(getLocator("rdoGenderFemale"));
        utils.click(getLocator("btnSave"), true);
        utils.wait(ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSave")), 5);
        return this;
    }

    @Step
    public UserProfilePage changePassword() {
        utils.click(getLocator("btnEdit"), true);
        utils.click(getLocator("lnkChangePassword"), true);
        utils.sendKeys(getLocator("txaCurrentPassword"), getUserTestData().getPassword());
        utils.sendKeys(getLocator("txaNewPassword"), getUserTestData().getPassword());
        utils.sendKeys(getLocator("txaConfirmPassword"), getUserTestData().getPassword());
        utils.click(getLocator("btnChangePassword"), true);
        return this;
    }

    @Step
    public boolean isPasswordChangedSuccessfully() {
        String successMessage = "Password updated successfully";
        utils.wait(ExpectedConditions.textToBePresentInElementLocated(getLocator("msgPasswordchange"), successMessage));
        boolean isSuccessMessageAvailabe = utils.findElement(getLocator("msgPasswordchange"))
                                                .getText().equals(successMessage);
        return isSuccessMessageAvailabe;
    }

    @Step
    public UserProfilePage edit() {
        utils.click(getLocator("btnEdit"), true);
        return this;
    }

    @Step
    public UserProfilePage cancelEdit() {
        utils.click(getLocator("btnCancel"), true);
        return this;
    }
}
