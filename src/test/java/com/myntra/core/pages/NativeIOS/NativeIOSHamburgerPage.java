package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.AddressPage;
import com.myntra.core.pages.HamburgerPage;
import io.qameta.allure.Step;

public class NativeIOSHamburgerPage extends HamburgerPage {

    @Step
    @Override
    public AddressPage goToAddress() {
        utils.swipeDown_m(5);
        utils.click(getLocator("mnuAddress"));
        return AddressPage.createInstance();
    }

}
