package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.OrderConfirmationPage;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

public class NativeIOSOrderConfirmationPage extends OrderConfirmationPage {

    @Step
    @Override
    protected OrderConfirmationPage navigateToOrdersPage() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }
}
