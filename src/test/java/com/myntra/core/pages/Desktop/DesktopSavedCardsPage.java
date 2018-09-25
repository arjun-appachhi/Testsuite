package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.SavedCardsPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class DesktopSavedCardsPage extends SavedCardsPage {

    @Step("checking whether page is loaded fine")
    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
        if (utils.isElementPresent(getLocator("txaNoSavedCardMessage"), 2)) {
            Assert.assertTrue(utils.isElementPresent(getLocator("btnAddCardForNoSavedCard"), 5), "Saved Cards page is not loaded");
        } else {
            Assert.assertTrue(utils.isElementPresent(getLocator("btnAddCardIfSavedCardsAvailable"), 5), "Saved Cards page is not loaded");
        }
    }
}
