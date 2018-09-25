package com.myntra.core.pages.MobileWeb;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.Desktop.DesktopHomePage;
import com.myntra.core.pages.HamburgerPage;
import com.myntra.core.pages.OrderConfirmationPage;
import com.myntra.core.pages.UserProfilePage;
import com.myntra.ui.Direction;
import io.qameta.allure.Step;

public class MobileWebHamburgerPage extends HamburgerPage {

    @Step
    @Override
    public OrderConfirmationPage goToMyOrdersPage() {
        utils.waitForElementToBeVisible(getLocator("mnuMyOrders"));
        utils.click(getLocator("mnuMyOrders"), true);
        return OrderConfirmationPage.createInstance();
    }

    @Step
    @Override
    public UserProfilePage goToEditProfile() {
        utils.click(getLocator("mnuAccount"), true);
        utils.waitForElementToBeVisible(getLocator("btnLogOut"));
        utils.click(getLocator("lblEditProfile"), true);
        return UserProfilePage.createInstance();
    }

    @Step
    @Override
    public AddressPage goToAddress() {
        utils.click(getLocator("mnuAccount"),true);
        return super.goToAddress();
    }
}
