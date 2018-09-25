package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.AddressPage;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DesktopAddressPage extends AddressPage {

    @Step
    @Override
    public AddressPage removeAddress() {
        utils.wait((ExpectedConditions.invisibilityOfElementLocated(getLocator("btnSaveAddress"))));
        utils.click(getLocator("btnRemove"), true);
        return AddressPage.createInstance();
    }
}
