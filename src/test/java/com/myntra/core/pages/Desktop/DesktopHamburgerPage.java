package com.myntra.core.pages.Desktop;

import com.myntra.core.pages.HamburgerPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Date;

public class DesktopHamburgerPage extends HamburgerPage {

    @Step
    @Override
    public void isLoaded() {
        super.isLoaded();
        boolean isInternalServerError = utils.isElementPresent(By.xpath("//*[contains(text(),'Internal Server Error')]"), 1);
        if (isInternalServerError) {
            String internalServerErrorFileName = "InternalServerError-" + this.getClass().getSimpleName() + "-" + new Date().getTime();
            utils.takeScreenshot(internalServerErrorFileName);
            LOG.info(String.format("Internal Server Error found on page - %s - Refresh Page and try", this.getClass().getSimpleName()));
            LOG.info(String.format("Screenshot saved as - %s", internalServerErrorFileName));
            utils.refreshPage();
            LOG.info(String.format("Check if page - %s - is loaded", this.getClass().getSimpleName()));
            super.isLoaded();
        }
    }
}
