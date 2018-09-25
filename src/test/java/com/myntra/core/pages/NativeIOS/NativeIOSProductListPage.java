package com.myntra.core.pages.NativeIOS;

import com.myntra.core.pages.ProductDescPage;
import com.myntra.core.pages.ProductListPage;
import io.qameta.allure.Step;

import java.util.HashMap;

public class NativeIOSProductListPage extends ProductListPage {

    @Step
    @Override
    public ProductDescPage selectFirstProductFromListPage() {
        //TODO, ONCE LOCATORS FOR PLP IS AVAILABLE MYNTRA TEAM NEED TO IMPLEMENT THIS METHOD
        // TODO, IMPLEMENT IT AS PER THE PLP FLOW (SEARCHING USING GOLDEN SET DATA,INSTEAD OF STYLEID);
        return ProductDescPage.createInstance();
    }

    @Step
    @Override
    public HashMap<String, String> getProductDetails() {
        HashMap<String, String> productDetails = new HashMap<>();
        //TODO, ONCE LOCATORS FOR PLP IS AVAILABLE MYNTRA TEAM NEED TO IMPLEMENT THIS METHOD
        // TODO, IMPLEMENT IT AS PER THE PLP FLOW (SEARCHING USING GOLDEN SET DATA,INSTEAD OF STYLEID);
        return productDetails;
    }

    @Step
    @Override
    public boolean isProductListDisplayed() {
        //TODO, ONCE LOCATORS FOR PLP IS AVAILABLE MYNTRA TEAM NEED TO IMPLEMENT THIS METHOD
        // TODO, IMPLEMENT IT AS PER THE PLP FLOW (SEARCHING USING GOLDEN SET DATA,INSTEAD OF STYLEID);
        return true;
    }
}