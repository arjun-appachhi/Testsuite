package com.myntra.core.business;

import com.myntra.core.pages.OrderConfirmationPage;
import com.myntra.core.pages.ShoppingBagPage;
import com.myntra.utils.test_utils.Assert;
import io.qameta.allure.Step;

public class Order extends BusinessFlow {

    public static Order getInstance() {
        return (Order) getInstance(Of.ORDER);
    }

    @Step
    public Order verifyOrderIsConfirmed() {
        if (shouldExecuteIfNotInProd(getClass().getSimpleName(), new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName())) {
            boolean isOrderConfirmed = OrderConfirmationPage.createInstance()
                                                            .isOrderConfirmedWithOrderNo();
            Assert.assertTrue(isOrderConfirmed, "Order is not confirmed");
        }
        return this;
    }

    @Step
    public Home goToHomePageAfterOrderConfirmation() {
        OrderConfirmationPage orderConfirmationPage = OrderConfirmationPage.createInstance();
        Assert.assertTrue(orderConfirmationPage.isOrderConfirmedWithOrderNo(), "Order is not confirmed");
        orderConfirmationPage.navigateToHomePage();
        return Home.getInstance();
    }
}
