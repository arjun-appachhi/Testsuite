package com.myntra.core.business;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.HomePage;
import com.myntra.core.pages.LoginPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class Home extends BusinessFlow {

    public static Home getInstance() {
        return (Home) getInstance(Of.HOME);
    }

    @Step
    public Product searchProductUsingStyleID() {
        return Search.getInstance()
                     .searchProductUsingStyleID();
    }

    @Step
    public Product searchProductUsingName() {
        return Search.getInstance()
                     .searchProductUsingName();
    }

    @Step
    public Product searchProductUsingHovering() {
        return Search.getInstance()
                     .searchProductUsingHovering();
    }

    @Step
    public Product searchProductUsingBanner() {
        return Search.getInstance()
                     .searchProductUsingBanner();
    }

    @Step
    public Home cleanState() {
        HomePage.createInstance()
                .reset();
        return this;
    }

    @Step
    public User loginSuccessfully() {
        return User.getInstance()
                   .loginSuccessfully();
    }

    @Step
    public Home verifyContactUsHasDetails() {
        HomePage.createInstance()
                .openContactUsPage()
                .closeContactUsPage();
        return this;
    }

    @Step
    public ShoppingBag goToBagPage() {
        HomePage.createInstance()
                .navigateToBag();
        return ShoppingBag.getInstance();
    }

    @Step
    public MyMyntra goToSavedCardsPage() {
        HomePage.createInstance()
                .navigateToSavedCards();
        return MyMyntra.getInstance();
    }

    @Step
    public MyMyntra editProfile() {
        HomePage.createInstance()
                .navigateToEditProfile()
                .editUserAccountDetails();
        return MyMyntra.getInstance();
    }

    @Step
    public MyMyntra addUserInformationFromProfilePage() {
        HomePage.createInstance()
                .goToProfilePage()
                .editUserAccountDetails();
        return MyMyntra.getInstance();
    }

    @Step
    public MyMyntra cancelEditUserProfile() {
        HomePage.createInstance()
                .goToProfilePage()
                .cancelEdit();
        return MyMyntra.getInstance();
    }

    @Step
    public Home navigateToHomePage() {
        HomePage.createInstance()
                .navigateToHomePage();
        return this;
    }

    @Step
    public Home signUpSuccessfully() {
        boolean isUserSignedUpSuccessfully = LoginPage.createInstance()
                                                      .signUp()
                                                      .isUserLoggedIn();
        Assert.assertTrue(isUserSignedUpSuccessfully, "User did not signed in successfully");
        return this;
    }

    @Step
    public Home logout() {
        HomePage homePage = HomePage.createInstance();
        Assert.assertTrue(homePage.logout()
                                   .isUserLogOutSuccessful(), "User is not logged out");
        return this;
    }

    @Step
    public MyMyntra cancelEditUserFromProfilePage() {
        Home.getInstance()
            .cancelEditUserProfile();
        return MyMyntra.getInstance();
    }
}
