package com.myntra.core.business;

import com.myntra.core.pages.LoginPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class User extends BusinessFlow {

    public static User getInstance() {
        return (User) getInstance(Of.USER);
    }

    @Step
    public User loginSuccessfully() {
        boolean isUserLoggedIn = LoginPage.createInstance()
                                          .navigateToLogin()
                                          .login()
                                          .isUserLoggedIn();
        Assert.assertTrue(isUserLoggedIn, "User should have been logged-in");
        return this;
    }

    @Step
    public Address signUpSuccessfully() {
         LoginPage.createInstance().signUpAfterPlacingOrder();
        return new Address();
    }


}
