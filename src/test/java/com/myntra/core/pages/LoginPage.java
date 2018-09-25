package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopLoginPage;
import com.myntra.core.pages.MobileWeb.MobileWebLoginPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidLoginPage;
import com.myntra.core.pages.NativeIOS.NativeIOSLoginPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import com.myntra.testdata.models.pojo.response.User;
import com.myntra.testdata.models.pojo.response.userobjects.EmailDetail;
import com.myntra.testdata.models.pojo.response.userobjects.PhoneDetail;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static com.myntra.core.utils.JavaUtils.generateUserNameForTest;

public abstract class LoginPage extends Page {

    public static LoginPage createInstance() {
        LoginPage derivedLoginPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedLoginPage = (LoginPage) DynamicEnhancer.create(NativeAndroidLoginPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedLoginPage = (LoginPage) DynamicEnhancer.create(NativeIOSLoginPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedLoginPage = (LoginPage) DynamicEnhancer.create(DesktopLoginPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedLoginPage = (LoginPage) DynamicEnhancer.create(MobileWebLoginPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedLoginPage;
    }

    @Override
    public String pageName() {
        return LoginPage.class.getSimpleName();
    }

    @Step
    public abstract HomePage login();

    @Step
    public abstract LoginPage navigateToLogin();

    @Step
    public abstract boolean isLoginOptionDisplayed();

    @Step
    public abstract AddressPage signUpAfterPlacingOrder();

    public abstract HomePage signUp();

    @Step
    protected void createNewUserTestData() {
        LOG.info("Creating new user data object");
        User newUser = new User();
        String userNameForTest = generateUserNameForTest(testExecutionContext.getTestName());
        String firstName = "firstName";
        String lastName = "lastName";

        newUser.setEmail(userNameForTest + "@myntra.com");
        newUser.setEmailDetails(getEmailDetails(newUser));
        newUser.setPassword("password");
        newUser.setGender("MALE");

        newUser.setPhoneDetails(getPhoneDetails());
        newUser.setLocation("Bangalore");
        getTestData().setUser(newUser);
        getTestData().setAdditionalProperty("firstname", firstName);
        getTestData().setAdditionalProperty("lastname", lastName);
        getTestData().setAdditionalProperty("yourbio", String.format("Bio of %s - %s", firstName, lastName));
        getTestData().getAddress().setLocality("Begur");

        LOG.info(String.format("Signing up for new user using %s::%s", newUser.getEmail(), newUser.getPassword()));
    }

    private List<PhoneDetail> getPhoneDetails() {
        List<PhoneDetail> phoneDetailList = new ArrayList<>();
        PhoneDetail phoneDetail = new PhoneDetail();
        phoneDetail.setPhone("9089908990");
        phoneDetailList.add(phoneDetail);
        return phoneDetailList;
    }

    private List<EmailDetail> getEmailDetails(User newUser) {
        List<EmailDetail> emailDetailList = new ArrayList<>();
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setEmail(newUser.getEmail());
        emailDetailList.add(emailDetail);
        return emailDetailList;
    }
}
