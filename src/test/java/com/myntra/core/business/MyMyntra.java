package com.myntra.core.business;

import com.myntra.core.pages.SavedCardsPage;
import com.myntra.core.pages.UserProfilePage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class MyMyntra extends BusinessFlow {
    public static MyMyntra getInstance() {
        return (MyMyntra) getInstance(Of.MYMYNTRA);
    }

    @Step
    public MyMyntra addNewCard() {
        Assert.assertTrue(SavedCardsPage.createInstance()
                                        .addNewCardInSavedCards()
                                        .isAddNewCardSuccessful(), "Card is not added in Saved Cards Page");
        return this;
    }

    @Step
    public MyMyntra removeAllSavedCard() {
        Assert.assertTrue(SavedCardsPage.createInstance()
                                        .removeAllCardsInSavedCardsPage()
                                        .isRemoveAllCardsSuccessful(), "Remove saved cards is not successful");
        return this;
    }

    @Step
    public MyMyntra editSavedCard() {
        Assert.assertTrue(SavedCardsPage.createInstance()
                                        .editSavedCard()
                                        .isEditSavedCardSuccessful(), "Edit the saved Card details is not successful");
        return this;
    }

    @Step
    public MyMyntra changeAccountPassword() {
        boolean isPasswordUpdated = UserProfilePage.createInstance()
                                                             .changePassword()
                                                             .isPasswordChangedSuccessfully();
        Assert.assertTrue(isPasswordUpdated,"Password did not change Successfully");
        return this;
    }
}
