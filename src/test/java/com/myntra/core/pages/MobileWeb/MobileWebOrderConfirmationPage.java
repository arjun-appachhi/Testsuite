package com.myntra.core.pages.MobileWeb;


import com.myntra.core.pages.HamburgerPage;
import com.myntra.core.pages.HomePage;
import com.myntra.core.pages.OrderConfirmationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MobileWebOrderConfirmationPage extends OrderConfirmationPage {


    @Step
    @Override
    protected OrderConfirmationPage navigateToOrdersPage() {
        utils.wait(ExpectedConditions.visibilityOfElementLocated(getLocator("imgMyntraLogo")));
        utils.click(getLocator("imgMyntraLogo"), true);
        HomePage.createInstance()
                .openMenu();
        HamburgerPage.createInstance()
                     .goToMyOrdersPage();
        return this;
    }

    @Step
    @Override
    public boolean isOrderNumberAvailableInMyOrders() {
        utils.click(getLocator("lnkViewOrder"), true);
        return super.isOrderNumberAvailableInMyOrders();
    }

    @Step
    @Override
    public HomePage navigateToHomePage() {
        utils.click(getLocator("lnkViewOrder"), true);
        utils.click(getLocator("imgMyntraLogo"), true);
        return HomePage.createInstance();
    }

    @Step
    @Override
    public boolean isOrderConfirmedWithOrderNo() {
        return (utils.isElementPresent(getLocator("txaOrderConfirmed"), 2));
    }
}
