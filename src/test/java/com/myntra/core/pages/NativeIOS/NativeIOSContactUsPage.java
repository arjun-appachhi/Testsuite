package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.ContactUsPage;
import com.myntra.core.pages.HomePage;
import io.qameta.allure.Step;
import org.apache.commons.lang3.NotImplementedException;

public class NativeIOSContactUsPage extends ContactUsPage {

    @Step
    @Override
    public HomePage closeContactUsPage() {
        throw new NotImplementedException(getClass().getSimpleName() + "-" + new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName() + " - NOT YET IMPLEMENTED");
    }
}
