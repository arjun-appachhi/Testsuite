package com.myntra.core.pages;

import com.myntra.core.pages.Desktop.DesktopTopMenuPage;
import com.myntra.core.pages.MobileWeb.MobileWebTopMenuPage;
import com.myntra.core.pages.NativeAndroid.NativeAndroidTopMenuPage;
import com.myntra.core.pages.NativeIOS.NativeIOSTopMenuPage;
import com.myntra.core.utils.DynamicEnhancer;
import com.myntra.core.utils.DynamicLogger;
import org.apache.commons.lang3.NotImplementedException;

public abstract class TopMenuPage extends Page {

    public static TopMenuPage createInstance() {
        TopMenuPage derivedTopMenuPage;
        switch (CHANNEL) {
            case NATIVE_ANDROID:
                derivedTopMenuPage = (TopMenuPage) DynamicEnhancer.create(NativeAndroidTopMenuPage.class, new DynamicLogger());
                break;

            case NATIVE_IOS:
                derivedTopMenuPage = (TopMenuPage) DynamicEnhancer.create(NativeIOSTopMenuPage.class, new DynamicLogger());
                break;

            case DESKTOP_WEB:
                derivedTopMenuPage = (TopMenuPage) DynamicEnhancer.create(DesktopTopMenuPage.class, new DynamicLogger());
                break;

            case MOBILE_WEB:
                derivedTopMenuPage = (TopMenuPage) DynamicEnhancer.create(MobileWebTopMenuPage.class, new DynamicLogger());
                break;

            default:
                throw new NotImplementedException("Invalid Channel");
        }
        return derivedTopMenuPage;
    }

    @Override
    public String pageName() {
        return TopMenuPage.class.getSimpleName();
    }
}
