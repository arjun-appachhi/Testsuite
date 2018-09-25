package com.myntra.core.pages.NativeAndroid;

import com.myntra.core.pages.ContactUsPage;
import com.myntra.core.pages.HomePage;
import io.qameta.allure.Step;

public class NativeAndroidContactUsPage extends ContactUsPage {

    @Step
    @Override
    public HomePage closeContactUsPage() {
        nativeView();
        goBack();
        return HomePage.createInstance();
    }
}
